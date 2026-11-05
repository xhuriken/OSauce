# OpenSauce (OSauce)

> **Launcher Android sobre, anti-dopamine et sensoriel (The Sauce Engine).**  
> Développé nativement en **Kotlin 2.0** et **Jetpack Compose**. 100% Hors-ligne, zéro pub, zéro tracking.

---

## 1. Vision & Principes
Nos smartphones sont devenus des machines à capturer l'attention : badges rouges, icônes bariolées agressives et notifications permanentes.
OSauce redéfinit le smartphone comme un outil au service de l'utilisateur :
1. **Silence d'abord** : Accueil noir OLED pur, horloge géante lisible, zéro badge anxiogène, physique fluide 120Hz.
2. **Sphères de Vie étanches** : Isolement de ses contextes mentaux (Humains, Travail, Vital, Loisirs).
3. **Recherche intentionnelle** : Tiroir d'apps A-Z monochrome au clavier. Fini le scroll réflexe.
4. **100% Local & Hors-ligne** : Données persistées en SQLite local. Aucun compte obligatoire, aucune dépendance cloud.

---

## 2. Les Écrans Natifs
* **Écran 1 : SILENCE (Accueil)** : Horloge Space Grotesk, matrice fluide réactive au toucher (The Sauce), devise de calme, 4 raccourcis vitaux.
* **Écran 2 : ACTION HUB (Sphères de Vie)** : Cartes modulaires Bauhaus par domaine, capture rapide de tâches, gestes de swipe tactile (droite = terminer, gauche = supprimer).
* **Écran 3 : APP DRAWER (Tiroir d'applications)** : Recherche instantanée, icônes monochromes anti-stimulus, badges de sphères.
* **Écran 4 : RÉGLAGES & THE SAUCE** : Configuration de la matrice (vibrance, densité, particules), sélecteur de thèmes OLED, définition du launcher par défaut.

---

## 3. Structure du Code

```
app/src/main/kotlin/com/osauce/launcher/
├── MainActivity.kt                      <- Hôte du HorizontalPager 1:1 et gestionnaire de retour
├── core/designsystem/                   <- Moteur graphique The Sauce, thèmes OLED, icônes vectorielles
│   ├── FluidBackground.kt              <- Matrice fluide 120Hz & physique particulaire
│   ├── OSauceIcons.kt                  <- Tracés vectoriels géométriques 1.5dp (zéro emoji)
│   ├── OSauceThemeEngine.kt            <- Injection dynamique des couleurs et thèmes
│   └── ThemeMod.kt                     <- Presets graphiques (OLED Dark, Phosphore, Cyber, etc.)
├── data/
│   ├── local/                          <- Base SQLite locale (OSauceDatabaseHelper, Sources)
│   ├── model/                          <- Modèles stricts (Sphere, TodoItem, AppItem)
│   ├── repository/                     <- Repositories réactifs (Flows)
│   └── settings/                       <- Préférences SharedPreferences synchrones
├── feature/
│   ├── silence/                        <- Écran 1 (HomeScreen, LiveClock, BatteryGauge, Dock)
│   ├── hub/                            <- Écran 2 (ActionHubScreen, SphereCard, QuickCapture, TodoRow)
│   ├── drawer/                         <- Écran 3 (AppDrawerScreen, filtrage monochrome A-Z)
│   └── settings/                       <- Écran 4 (SettingsScreen, contrôles matrice & thèmes)
└── ui/
    ├── LauncherViewModel.kt            <- State holder central MVI
    └── LauncherUiState.kt              <- États et intentions immuables
```

---

## 4. Compilation & Lancement
* **JDK Requis** : JDK 21 LTS (`JAVA_HOME`).
* **Android SDK** : API 35 (Android 15), compatible Android 9+.
* **Ligne de commande** :
  ```bash
  # Compiler et assembler l'APK debug
  ./gradlew assembleDebug

  # Installer sur appareil / émulateur
  adb install -r app/build/outputs/apk/debug/app-debug.apk
  ```
