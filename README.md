# OSauce

Mon projet d'OS mobile minimaliste, rapide et propre.
L'idee de base : un systeme hyper leger base sur Linux (postmarketOS), une interface maison en Rust avec Slint qui tourne a 60/120 FPS sur GPU, et la possibilite de faire tourner des applis Android grace a Waydroid quand c'est necessaire.

Pas de trucs lourds, pas d'emojis, juste KISS

---

## Comment lancer le projet

Le developpement et les tests complets se font sous **WSL2 (Ubuntu)** et dans l'emulateur mobile **QEMU postmarketOS**.

---

### Methode 1 : L'Emulateur Mobile Complet (QEMU + postmarketOS + Waydroid)

C'est l'environnement reel du smartphone qui fait tourner le noyau Linux mobile, le compositeur Wayland et le conteneur Android Waydroid.

#### 1. Ouvrir l'environnement WSL
Depuis PowerShell ou CMD :
```bash
wsl --cd c:\Users\celestin\OS
```

#### 2. Demarrer le telephone virtuel dans QEMU
Dans ton premier terminal WSL :
```bash
pmbootstrap qemu --no-kvm --cpu max --image-size 8G --display sdl
```
* Explication des options :
  * `--cpu max` : Active les jeux d'instructions recents (SSSE3) requis par Android AOSP.
  * `--image-size 8G` : Alloue l'espace suffisant pour LineageOS et les applications.
  * `--display sdl` : Ouvre la fenetre graphique directement sous Windows via WSLg.

#### 3. Se connecter en SSH (terminal de commande)
Ouvre un **second terminal WSL** (ou un onglet) pour piloter la VM avec ton clavier AZERTY et le copier-coller :
```bash
ssh -p 2222 user@localhost
```
* Identifiants : utilisateur `user`, mot de passe `1234`.
* Si tu utilises PowerShell directement : `wsl -e ssh -p 2222 user@localhost`.

#### 4. Demarrer le serveur graphique (Weston) et Android (Waydroid)
1. Dans la **fenetre QEMU** physique, tape :
   ```bash
   weston
   ```
   *(Le bureau graphique Wayland s'ouvre sur l'ecran du telephone).*
2. Dans ton **terminal SSH**, lance la session Android :
   ```bash
   export WAYLAND_DISPLAY=wayland-1
   waydroid session start &
   waydroid show-full-ui
   ```
   *(L'interface Android s'affiche a l'ecran dans la fenetre QEMU).*

#### 5. Installer des applications Android (APK)
Dans le terminal SSH :
```bash
# 1. Telecharger l'APK (exemple : F-Droid Store d'apps open-source)
wget -O F-Droid.apk https://f-droid.org/F-Droid.apk

# 2. Installer le paquet dans le conteneur Waydroid
waydroid app install F-Droid.apk
```

#### 6. Compiler et deployer le Shell OSauce (Rust) dans la VM
Pour executer l'interface OSauce dans la machine virtuelle :
```bash
# Depuis ton terminal WSL principal (dans le dossier du projet) :
cargo build --release --bin osauce-shell
scp -P 2222 target/release/osauce-shell user@localhost:~

# Dans le terminal SSH :
export WAYLAND_DISPLAY=wayland-1
./osauce-shell
```

---

### Methode 2 : Le Simulateur Rapide sur PC (Dev UI & Hot Reload)

Pour modifier le design et iterer sur l'interface Slint sans lancer l'emulateur complet :

1. **Hot Reload instantane Slint** (rafraichissement a chaque sauvegarde) :
   ```powershell
   slint-viewer --auto-reload ui/shell.slint
   ```
2. **Simulateur natif Rust** :
   ```bash
   cargo run --bin osauce-shell
   ```

---

### Commandes utiles et depannage

* **Clavier AZERTY dans la fenetre QEMU directe** : `sudo loadkeys fr`
* **Eteindre proprement la VM** : `sudo poweroff` (dans la console ou par SSH).
* **Verifier l'espace disque dans la VM** : `df -h /`
* **Verifier le conteneur Waydroid** : `waydroid status` et `systemctl status waydroid-container`

---

## Ce que j'ai appris et comment le projet est decoupe

### 1. Le decoupage des fichiers
* `ui/shell.slint` : Layout racine, navigation entre l'accueil et les vues de signaux.
* `ui/views/` : Vues de l'application (accueil zen, intentions, liens humains, moments).
* `ui/components/` : Composants reutilisables (carte calme, dock vectoriel, status bar).
* `ui/styles/theme.slint` : Constantes graphiques, palette pastel et tokens d'animation.
* `src/main.rs` : Moteur Rust, telemetrie materielle (batterie sysfs, horloge), gestion des callbacks et passerelle Waydroid.

### 2. Architecture et Compatibilite Android
* Le systeme hote est un Linux epure (postmarketOS sous Alpine).
* La compatibilite applicative Android est assuree par Waydroid sans passer par un emulateur lourd : partage direct du noyau via `/dev/binder` et rendu GPU direct sur Wayland.
* L'installation de paquets se fait via les APK dumpes depuis F-Droid ou l'API Google Play (Aurora Store) sans dependance aux composants privateurs Google Services.

---

## Documentation et Architecture

- Architecture logicielle detaillee : [`docs/ARCHITECTURE.md`](file:///c:/Users/celestin/OS/docs/ARCHITECTURE.md)
- Guide de developpement Rust : [`docs/RUST_GUIDE.md`](file:///c:/Users/celestin/OS/docs/RUST_GUIDE.md)
- Guide de developpement Slint UI : [`docs/SLINT_GUIDE.md`](file:///c:/Users/celestin/OS/docs/SLINT_GUIDE.md)

