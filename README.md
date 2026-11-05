# OpenSauce (OSauce)

Launcher Android sobre, léger et open-source en Kotlin et Jetpack Compose.  
100% Hors-ligne, sans pub, sans compte obligatoire, sans tracking.

---

## Ce que fait l'application
Nos téléphones mélangent souvent tout : messages persos, mails du travail, alertes d'applications inutiles et dizaines d'icônes dispersées.

OSauce remet de l'ordre simplement :
* **Un accueil calme** : Une grande horloge lisible, un fond réactif au toucher, et vos 4 applications principales.
* **Les Sphères de Vie** : Séparer vos outils par contexte réel (*Humains*, *Travail*, *Vital*, *Loisirs*, ou vos propres catégories). Chaque sphère regroupe ses applications et ses tâches dédiées.
* **Une recherche rapide d'apps** : Glissez vers le haut, tapez 2 lettres au clavier et lancez votre appli.
* **100% local sur le téléphone** : Vos tâches et réglages restent stockés dans une base SQLite locale. Rien ne part sur des serveurs externes.

---

## Les Écrans
1. **Silence (Accueil)** : Horloge Space Grotesk, indicateur d'autonomie discret, fond interactif au doigt, 4 raccourcis du bas.
2. **Action Hub (Sphères de Vie)** : Cartes pour chaque domaine de vie avec leurs applications rattachées, leurs tâches en cours, et une barre d'ajout rapide.
3. **App Drawer (Tiroir d'applications)** : Recherche instantanée au clavier, navigation A-Z, attribution d'apps aux sphères.
4. **Réglages** : Personnalisation du fond et des couleurs, définition du launcher par défaut.

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
