# OpenSauce (Aka O.Sauce)

> **Le Launcher Android sobre, anti-dopamine et éco-responsable.**  
> Développé nativement en **Kotlin** et **Jetpack Compose**. 100% Libre & Gratuit, zéro publicité, zéro pistage.

[![Plateforme](https://img.shields.io/badge/Plateforme-Android%209%2B-3DDC84?logo=android&logoColor=white)](#)
[![Langage](https://img.shields.io/badge/Langage-Kotlin%20%7C%20Jetpack%20Compose-7F52FF?logo=kotlin&logoColor=white)](#)
[![Licence](https://img.shields.io/badge/Licence-Open%20Source%20(FOSS)-blue)](#)
[![ODD](https://img.shields.io/badge/Impact-ODD%2012%20%26%20ODD%203-brightgreen)](#)
[![Modèle](https://img.shields.io/badge/Modèle-100%25%20Gratuit%20%7C%20Dons%20Libres-orange)](#)

---

## 1. La Vision OpenSauce

Nos smartphones sont devenus des casinos de poche : **4h37** passées chaque jour devant l'écran en moyenne, **80 interruptions cognitives** quotidiennes par des pastilles rouges anxiogènes, et des surcouches constructeurs obèses (20 Go et 3 Go de RAM à vide) qui ralentissent artificiellement les appareils après 2 ans.

Face à cela, le retour aux *dumbphones* (vieux téléphones à touches) est une impasse : en 2026, un téléphone à touches ne permet ni de valider un paiement bancaire 3D Secure, ni de badger un billet SNCF ou Navigo, ni d'utiliser un GPS. Quant aux téléphones minimalistes neufs (ex: Light Phone III à 800 $), ils forcent encore à racheter du neuf à des prix délirants.

**OpenSauce réconcilie sobriété et modernité utile :**
* **Zéro friction** : S'installe en **30 secondes** depuis un fichier APK ou F-Droid. Pas de PC requis, pas de bootloader à déverrouiller, zéro risque de bricker son téléphone.
* **100% de compatibilité native** : Vos applications bancaires, vos titres de transport et vos messageries (WhatsApp, Signal) fonctionnent nativement sans aucune perte de service.
* **Sécurité matérielle préservée** : Contrairement aux ROMs alternatives qui cassent les enclaves de sécurité (Knox / Titan M), OpenSauce tourne comme un Launcher applicatif : le chiffrement matériel d'Android reste intact.
* **Anti-Obsolescence (ODD 12)** : Allège radicalement l'interface et redonne une fluidité à 60/120 FPS aux smartphones de 3 à 5 ans, évitant l'extraction de 82 kg de matières premières brutes par appareil préservé.

---

## 2. L'Expérience Utilisateur : Les 4 Écrans

1. **Écran 1 : Le Silence**
   * Fond noir OLED pur (`#000000`) qui éteint physiquement les pixels de l'écran pour reposer les yeux et préserver la batterie (-30% à -40% de consommation écran).
   * Horloge géante vectorielle avec indicateur solaire AGSL.
   * Jauge textuelle fine d'autonomie et intention unique du moment.
   * Dock d'urgence minimaliste (Appels, SMS, Caméra).
2. **Écran 2 : Le Hub d'Action**
   * 4 sphères de vie thématiques : *Humain & Proches*, *Travail & Études*, *Quotidien & Vital*, *Temps Libre*.
   * Grâce à l'API système `NotificationListenerService`, les alertes sont interceptées localement et regroupées sans pastilles rouges agressives.
   * Chaque notification peut être convertie en un clic en tâche à faire plus tard.
3. **Écran 3 : La Boîte à Outils**
   * Liste alphabétique sobre, épurée et monochrome de toutes vos applications.
   * Recherche instantanée au clavier pour lancer ce dont vous avez besoin sans vous perdre dans un catalogue d'icônes bariolées.
4. **Écran 4 : Marketplace Ouvert & Mods Communautaires**
   * Catalogue intégré d'extensions ouvertes vérifiées, gratuites et auditées sans traqueurs.
   * Shaders AGSL légers (cadran solaire 24h, respiration guidée), widgets discrets (lecteur audio, chrono Pomodoro) et passerelles Markdown locales (Obsidian, Logseq).

---

## 3. Philosophie & Modèle Éthique

OpenSauce est guidé par le principe du **bien commun numérique** :
* **100% Libre & Gratuit (Zéro Freemium)** : Aucune fonctionnalité bloquée, aucun abonnement obligatoire, aucune publicité, zéro profilage ni revente de données personnelles.
* **Soutien par Dons Libres & Mécénat** : Le projet vit grâce au soutien de sa communauté via [GitHub Sponsors](https://github.com/sponsors) et Open Collective.
* **Option Verte Clé en Main (Packs Reconditionnés)** : Pour ceux qui veulent un smartphone éco-responsable prêt à l'emploi sans manipulation, partenariat avec des reconditionneurs solidaires (YesYes, Emmaüs Connect) pour proposer des smartphones reconditionnés éthiques (120 à 150 €) avec OpenSauce déjà préconfiguré et garanti.

---

## 4. Structure du Projet

```
OSauce/
├── app/                                 <- Code source du Launcher Android
│   ├── src/main/
│   │   ├── AndroidManifest.xml          <- Déclaration du Launcher (HOME) et NotificationService
│   │   └── kotlin/com/osauce/launcher/
│   │       ├── MainActivity.kt          <- Point d'entrée, state machine et navigation
│   │       ├── data/model/              <- Modèles ActionGroup, PluginManifest, Notifications
│   │       ├── plugin/                  <- PluginManager et gestion des mods
│   │       ├── service/                 <- NotificationListenerService
│   │       └── ui/
│   │           ├── screens/             <- Silence, Hub, Detail, Tools, Marketplace
│   │           └── theme/               <- Tokens OLED (#000000), couleurs et typographie
│   └── build.gradle.kts
├── docs/                                <- Spécifications techniques & UI
│   ├── ARCHITECTURE.md                  <- Architecture logicielle complète & diagrammes Mermaid
│   ├── MODDING_AND_MARKETPLACE.md       <- Cahier des charges du Store et SDK de plugins
│   └── PROTOTYPE_SPEC_FIGMA.md          <- Spécifications complètes UI et tokens Figma
├── entrepreneuriat/                     <- Dossier d'impact, entrepreneuriat et Titre CDA
│   ├── BUSINESS_MODEL_CANVAS.md         <- BMC 9 blocs complet
│   ├── MATRICE_OFFRE.md                 <- Grille stratégique de l'offre (Basique, Solidaire, Pro)
│   ├── NUMERIQUE_RESPONSABLE.md         <- 10 pastilles vertes d'évaluation écoconception & RSE
│   ├── PREVISIONNELS_100_CLIENTS.md     <- Prévisionnels économiques, carbone et équilibre de vie
│   ├── CIBLE_ET_PROPOSITION_VALEUR.md   <- Persona Marc et Value Proposition Canvas
│   ├── DOSSIER_PROJET.md                <- Étude de marché approfondie et compétences CDA
│   ├── ENVIRONMENTAL_BUSINESS_MODEL_CANVAS.md <- Planche 2 (ACV, cycle de vie)
│   ├── SOCIAL_BUSINESS_MODEL_CANVAS.md  <- Planche 3 (Impact sociétal, santé mentale)
│   └── test.md                          <- Trame d'oral de pitch 3 minutes
├── build.gradle.kts                     <- Configuration racine Gradle
└── settings.gradle.kts
```

---

## 5. Compiler et Tester le Projet

### Prérequis
* **JDK 17 ou 21** configuré (`JAVA_HOME`).
* **Android SDK** (API 34 / Android 14 recommandé).
* **Android Studio** (Koala / Ladybug ou supérieur).

### Ouvrir dans Android Studio
1. Lancez **Android Studio**.
2. Choisissez `Open Project` et sélectionnez le dossier `OSauce`.
3. Laissez Gradle synchroniser les dépendances.
4. Branchez votre smartphone Android en USB avec le **Débogage USB activé** (ou lancez un émulateur) et cliquez sur `Run (Shift + F10)`.

### En ligne de commande (Gradle Wrapper)

* **Sous Linux / macOS** :
  ```bash
  ./gradlew assembleDebug
  ```
* **Sous Windows (PowerShell)** :
  ```powershell
  .\gradlew.bat assembleDebug
  ```

L'APK généré sera disponible dans :  
`app/build/outputs/apk/debug/app-debug.apk`

---

## 6. Documentation & Liens Utiles

* [Architecture Technique](docs/ARCHITECTURE.md) : Diagramme global de composants, couches Presentation, Service et Data.
* [Spécifications UI & Figma](docs/PROTOTYPE_SPEC_FIGMA.md) : Tokens de couleurs OLED, typographies, hiérarchie visuelle.
* [Spécifications du Modding](docs/MODDING_AND_MARKETPLACE.md) : Format `manifest.json`, catégories de plugins et API d'extension.
* [Dossier Entrepreneuriat & CDA](entrepreneuriat/DOSSIER_PROJET.md) : Étude de marché, cadre réglementaire DMA, et couverture des 11 compétences du Titre CDA (Niveau 6).

---

## 7. Licence & Communauté

OpenSauce est un logiciel libre distribué sous licence open source.  
Les contributions, retours d'expérience et créations de mods sont les bienvenus via les [Issues GitHub](https://github.com/xhuriken/OSauce/issues) et les discussions communautaires.
