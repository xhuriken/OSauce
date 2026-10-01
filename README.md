# OSauce

Mon projet d'OS mobile minimaliste, rapide et propre.
L'idee de base : un systeme hyper leger base sur Linux (postmarketOS), une interface maison en Rust avec Slint qui tourne a 60/120 FPS sur GPU, et la possibilite de faire tourner des applis Android grace a Waydroid quand c'est necessaire.

Pas de trucs lourds, pas d'emojis, juste KISS

---

## Comment lancer le projet

Le developpement se fait sous WSL2 (Ubuntu). Pour y acceder depuis l'invite de commandes Windows (cmd ou PowerShell) :

```cmd
wsl --cd c:\Users\celestin\OS
```

Ensuite, selon ce sur quoi tu bosses, il y a plusieurs facons de lancer :

### 1. Le Hot Reload instantane (pour bosser sur l'UI)
C'est le mode le plus rapide pour taffer sur le design sans attendre que Rust recompile a chaque fois.
Comme les fichiers sont edites sous Windows, le binaire natif Windows `slint-viewer` est installe dans `.cargo/bin` pour assurer un rafraichissement immediat (< 10ms) a chaque sauvegarde (`Ctrl+S`) sans limitation WSL :

Dans un terminal Windows PowerShell ou CMD :
```powershell
slint-viewer --auto-reload ui/shell.slint
```

*Note : Tu peux aussi installer l'extension officielle **Slint** dans l'editeur et cliquer sur le bouton "Show Preview" en haut a droite du fichier `ui/shell.slint` pour avoir le rendu interactif directement dans l'editeur.*

### 2. Lancer l'application Rust complete
Pour compiler et tester le simulateur complet avec la logique Rust :

```bash
cargo run --bin osauce-shell
```

### 3. Recompilation automatique (Rust + Slint)
Si tu touches a la fois au code Rust (`src/main.rs`) et a l'interface :

```bash
cargo watch -x "run --bin osauce-shell"
```

---

## Ce que j'ai appris et comment le projet est decoupe

### 1. Le decoupage des fichiers
Au debut, tout etait inline dans un seul fichier `src/main.rs`. C'etait bien pour tester, mais vite bordelique. Du coup, on a separe proprement :

* `ui/shell.slint` : Tout ce qui concerne l'affichage (mise en page, couleurs, animations, boutons). C'est du code declaratif, simple a lire et a modifier.
* `build.rs` : Un petit script de build qui dit a Cargo de compiler `ui/shell.slint` a l'avance lors du `cargo build`.
* `src/main.rs` : Le code Rust de l'OS. Il importe le module genere avec `slint::include_modules!()`, configure l'affichage et demarre la boucle d'evenements.

A terme, le dossier `ui/` sera decoupe en composants (`components/status_bar.slint`, `components/dock.slint`, `views/home.slint`), exactement comme sur un projet React ou Vue, pour ne jamais avoir un fichier de 1000 lignes.

### 2. Comment Rust et Slint communiquent
* **Slint (la vue)** : Gere le layout et les animations materielles directes sur le GPU. Il expose des proprietes (par exemple l'heure ou le niveau de batterie) et des callbacks (par exemple quand on clique sur une appli).
* **Rust (le cerveau)** : S'occupe du vrai systeme d'exploitation. Il va lire l'etat de la batterie via les fichiers systeme Linux, gerer l'horloge, et quand Slint lui dit "l'utilisateur a clique sur Web", c'est Rust qui lance le process ou le conteneur Waydroid en arriere-plan.

### 3. Ce qui a coince au debut et comment ca a ete regle (WSLg & Windows 10)
Faire tourner une fenetre graphique Linux sous Windows 10 via WSL2 a pose quelques pieges au demarrage :
* **Les dependances manquantes** : Slint a besoin de bibliotheques systeme comme `libfontconfig1-dev` et `libxkbcommon-x11-0` pour initialiser le clavier et les polices sous Linux.
* **Le bug de la fenetre blanche / COPY MODE** : Sur Windows 10, le serveur graphique WSLg (Weston) a besoin d'un point de montage en memoire partagee (`/mnt/shared_memory`). Sans ca, il se mettait en mode degrade et le rendu logiciel n'envoyait pas les frames. On a fixe ca en montant `/mnt/shared_memory` en tmpfs dans `/etc/fstab` et en basculant sur le moteur GPU Wayland natif via le pilote Mesa D3D12 (qui tourne a plus de 1500 FPS).
* **Les logs** : Si on ne met pas de `println!` explicite dans `src/main.rs`, le simulateur se lance sans rien afficher dans le terminal, ce qui donnait l'impression que c'etait bloque alors que la fenetre etait simplement en arriere-plan.
