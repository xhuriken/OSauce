# OSauce - Launcher Android Sobre & Modding Communautaire

OSauce est un launcher Android alternatif concu pour rompre avec l'economie de l'attention et le bombardement de notifications, developpe nativement en **Kotlin** et **Jetpack Compose**.

Il remplace le bureau classique d'Android par une approche fluide par **Groupes d'Action** (unifiant alertes, todos et outils) et integre un **Marketplace de mods communautaires** pour developper et partager des extensions ouvertes.

Pas de trucs lourds, pas de publicite, pas d'emojis, juste KISS.

---

## 1. Pourquoi ce Pivot vers un Launcher Android ?

1. **Accessibilite Maximale** : Une installation en 30 secondes depuis un fichier `.apk`, sans avoir a flasher son bootloader ou risquer de bricker son smartphone.
2. **Compatibilite Totale Immediate** : Toutes les applications bancaires, de transport et de messagerie fonctionnent nativement sans couche de conteneurisation lourde.
3. **Tri Intelligent des Notifications** : Grâce a l'API native `NotificationListenerService`, OSauce intercepte les alertes et les classe sans encombrement visuel.
4. **Shaders et Animations Modernes** : Rendu fluide a 120 FPS et shaders AGSL sur-mesure (cadran solaire, transitions organiques).

---

## 2. Les 4 Écrans du Prototype

1. **Écran 1 : Le Silence** : Horloge geante epuree, jauge d'autonomie textuelle fine, intention unique du moment et dock d'urgence minimaliste.
2. **Écran 2 : Le Hub d'Action** : 4 groupes de vie thematiques (Humain & Proches, Travail & Etudes, Quotidien & Vital, Temps Libre).
3. **Écran 2-B : Le Groupe Déplié** : Vue interieure combinant « Ce qui attend » (alertes avec bouton de conversion en Todo), « Mes tâches du groupe » et « Outils du groupe ».
4. **Écran 3 : La Boîte à Outils** : Liste alphabetique sobre et monochrome sans logos criards avec recherche instantanee.
5. **Écran 4 : Marketplace Communautaire** : Catalogue integre de mods, shaders et widgets verifies sans traceur publicitaire.

---

## 3. Le Nouvel Axe : Modding Communautaire & Marketplace

OSauce ne fige pas l'utilisateur dans une boite fermee :
* Les developpeurs et passionnes peuvent creer leurs propres widgets, shaders AGSL ou passerelles (ex: pont Obsidian, minuteur Pomodoro, widget velo) via l'API ouverte.
* Les mods sont publies et audites sur la plateforme communautaire `osauce.io`.
* L'utilisateur les installe et les active en un clic directement depuis le Marketplace integre dans l'application.
* Consultez [docs/MODDING_AND_MARKETPLACE.md](file:///c:/Users/celestin/OS/docs/MODDING_AND_MARKETPLACE.md) pour les specifications du SDK.

---

## 4. Structure du Projet

```
OS/
├── app/                                 <- Code source de l'application Android
│   ├── src/main/
│   │   ├── AndroidManifest.xml          <- Declaration du Launcher et du service de notifications
│   │   └── kotlin/com/osauce/launcher/
│   │       ├── MainActivity.kt          <- Point d'entree et machine a etats
│   │       ├── data/model/              <- Modeles ActionGroup et PluginManifest
│   │       ├── plugin/                  <- PluginManager et gestion des mods
│   │       ├── service/                 <- NotificationService (Listener)
│   │       └── ui/
│   │           ├── screens/             <- Silence, Hub, Detail, Tools, Marketplace
│   │           └── theme/               <- Tokens OLED (#000000) et typographie
│   └── build.gradle.kts
├── docs/
│   ├── ARCHITECTURE.md                  <- Architecture technique globale Mermaid
│   ├── MODDING_AND_MARKETPLACE.md       <- Cahier des charges du Store de plugins
│   └── PROTOTYPE_SPEC_FIGMA.md          <- Master prompt et specifications UI Figma
├── entrepreneuriat/                     <- Dossier complet CDA (BMCs, previsionnels, etude)
├── build.gradle.kts
└── settings.gradle.kts
```

---

## 5. Comment compiler et tester

### Ouvrir dans Android Studio
1. Ouvrez **Android Studio** (Koala / Ladybug ou superieur).
2. Cliquez sur `Open Project` et selectionnez le dossier `c:\Users\celestin\OS`.
3. Laissez Gradle synchroniser les dependances.
4. Lancez le projet sur un émulateur ou sur votre smartphone Android relie en USB avec le debogage USB active.

### En ligne de commande (Gradle)
```bash
./gradlew assembleDebug
```
L'APK genere se trouvera dans `app/build/outputs/apk/debug/app-debug.apk`.
