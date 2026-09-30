# OSauce

Système d'exploitation mobile compact, esthétique, ultra-fluide et KISS (Keep It Simple, Stupid).

---

## Vision & Architecture

OSauce sépare rigoureusement la couche hôte minimale du conteneur d'applications Android :

```
+-------------------------------------------------------------+
|                     OSauce Shell (Rust)                     |
|  - Interface utilisateur réactive & épurée (Slint UI)       |
|  - Barre d'état, Lanceur d'applications, Centre de contrôle |
+------------------------------+------------------------------+
|   Navigateur Web Embarqué    |    Conteneur Waydroid        |
|   (WebKit / Wry réactif)     |    (Runtime AOSP minimal)    |
|                              |    - F-Droid / Aurora Store  |
|                              |    - Compatibilité APK       |
+------------------------------+------------------------------+
|               Serveur d'Affichage Wayland                   |
|               (Smithay / Rendu GPU Direct)                  |
+-------------------------------------------------------------+
|               postmarketOS (Noyau Linux Mobile)             |
|               Pilotes Matériels & Modules Binder IPC        |
+-------------------------------------------------------------+
```

---

## Piles Technologiques

* **Interface Utilisateur (Shell)** : Rust + [Slint](https://slint.dev/) (Rendu matériel 60 FPS, mémoire minimale).
* **Affichage & Compositeur** : Wayland via [Smithay](https://smithay.github.io/).
* **Exécution Android (APK)** : [Waydroid](https://waydro.id/) (Conteneur LXC natif sans émulation processeur).
* **Système Hôte** : [postmarketOS](https://postmarketos.org/) (Alpine Linux mobile, boot en < 3s, ~150 Mo RAM).
* **Applications Android** : F-Droid / Aurora Store.

---

## Développement Local & Simulation

### 1. Simulateur Desktop (Interface Shell)
L'interface graphique est exécutable directement sur PC en simulant une résolution de smartphone (ex: 390x844 px) :
```bash
cargo run --bin osauce-shell
```

###  Émulateur Système Complet (QEMU)
Pour tester l'intégration système avec postmarketOS et Waydroid :
```bash
pmbootstrap init
pmbootstrap qemu --display=sdl --resolution=720x1440
```
