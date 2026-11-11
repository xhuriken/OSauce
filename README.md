# OpenSauce (OSauce)

Launcher Android sobre, léger et open-source en Kotlin et Jetpack Compose.  
100% Hors-ligne, sans pub, sans compte obligatoire, sans tracking.

---

## Ce que fait l'application
Nos téléphones nous harcèlent de notifications et mélangent souvent tout : messages persos, mails du travail, alertes d'applications inutiles et dizaines d'icônes dispersées.

OSauce remet de l'ordre simplement :
* **Un accueil calme** : Une grande horloge lisible, un fond réactif au toucher, et vos 4 applications principales.
* **Vos Espaces** : Séparer vos outils par contexte réel (*Humains*, *Travail*, *Vital*, *Loisirs*, ou vos propres espaces personnalisés). Chaque espace regroupe ses applications et ses intentions dédiées.
* **Une recherche rapide d'apps** : Glissez vers le haut, tapez 2 lettres au clavier et lancez votre appli.
* **100% local sur le téléphone** : Vos tâches et réglages restent stockés dans une base SQLite locale. Rien ne part sur des serveurs externes.

---

## Les Écrans
1. **Silence (Accueil)** : Horloge Space Grotesk, indicateur d'autonomie discret, fond interactif au doigt (*The Sauce* par `@OpenSauce`), 4 raccourcis du bas.
2. **Vos Espaces** : Cartes pour chaque domaine de vie avec leurs applications rattachées, leurs intentions en cours avec jauge de progression, et une barre d'ajout rapide.
3. **App Drawer (Tiroir d'applications)** : Recherche instantanée au clavier, navigation A-Z, attribution d'apps aux espaces.
4. **Paramètres** : Hub en 8 lignes pour piloter le launcher, les thèmes, le moteur sensoriel et les extensions.

---

| Cible / Problème | Proposition OpenSauce |
| :--- | :--- |
| **POUR ?**<br>Les personnes (étudiants, créatifs, actifs) qui veulent un téléphone qui redevient un outil utile et apaisant, sans couper les ponts avec le monde moderne. | **QUI ?**<br>En ont marre de passer 5h par jour sur leur smartphone sans s'en rendre compte, distraits par des flux mélangés et des interfaces conçues pour capter leur temps. |
| **NOTRE PRODUIT**<br>OpenSauce (OSauce) est un launcher Android libre, éco-conçu et modulaire développé en Kotlin et Jetpack Compose. | **QUI PERMET ?**<br>D'avoir un accueil calme avec l'heure en grand et 4 essentiels, de séparer sa vie en Espaces étanches (Humains, Travail, Vital, Loisirs), et de lancer n'importe quelle app au clavier en 2 secondes. |
| **CONTRAIREMENT**<br>Aux surcouches constructeurs lourdes et opaques, ou aux « dumbphones » rétrogrades qui bloquent les banques et les billets de train. | **NOUS**<br>Proposons un système 100% transparent, fluide sur n'importe quel téléphone, avec le moteur sensoriel The Sauce et une architecture ouverte aux mods de la communauté. |

---

## Compilation
* **Prérequis** : JDK 21 LTS (`JAVA_HOME`) et Android SDK (Android 9 à 15).
* **Commandes** :
  ```bash
  # Compiler l'APK debug
  ./gradlew assembleDebug

  # Installer sur appareil
  adb install -r app/build/outputs/apk/debug/app-debug.apk
  ```
