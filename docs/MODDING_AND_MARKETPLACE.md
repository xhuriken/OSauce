# Modding Communautaire, Écosystème & Marketplace de Plugins — OpenSauce (O.Sauce)

Ce document constitue la spécification officielle de référence pour l'architecture de **Modding**, de l'**Écosystème Cloud/Web** et du **Marketplace OpenSauce**. Il formalise le fonctionnement complet : du créateur concevant son add-on à l'utilisateur final installant un mod en un clic depuis l'application ou le site web.

---

## 1. Vision : Le Modding Éthique, Souverain et "Plug & Play"

Sur les systèmes mobiles conventionnels (surcouches Android lourdes ou iOS) :
* L'utilisateur subit un environnement fermé, des widgets prédateurs d'énergie et des trackers publicitaires.
* La personnalisation se résume à des packs d'icônes payants ou des surcouches instables.

Dans **OpenSauce** :
* **Accessibilité Totale (No-Code à Pro)** : Créer un mod doit être aussi simple que modifier un fichier JSON ou coller un shader, sans obliger à maîtriser le code source interne du launcher.
* **100% Éco-conçu & Sandboxé** : Chaque mod tourne dans un bac à sable strict. Zéro fuite de données, interdiction des boucles CPU énergivores, certification de sobriété thermique et batterie.
* **Expérience Unifiée Web & In-App** : Un compte unique OpenSauce (optionnel, respectueux de la vie privée) permet de retrouver ses mods et réglages favoris partout.

---

## 2. Les 3 Niveaux de Modding ("Plug & Play")

Pour ouvrir la création au plus grand nombre (graphistes, étudiants, développeurs confirmés) :

```
┌────────────────────────────────────────────────────────────────────────┐
│ NIVEAU 1 : Thèmes & Palettes Déclaratifs (100% No-Code / JSON)         │
│ -> Édition d'un fichier theme.json, palettes OLED, polices, rayon     │
├────────────────────────────────────────────────────────────────────────┤
│ NIVEAU 2 : Shaders Visuels & Motifs Dynamiques (AGSL / Canvas)         │
│ -> Shaders graphiques natifs Android 13+ compilés à chaud à 120 FPS    │
├────────────────────────────────────────────────────────────────────────┤
│ NIVEAU 3 : Tuiles & Add-ons Intelligents (Kotlin Compose Plugin API)   │
│ -> Tuiles fonctionnelles, passerelles (Obsidian, Todoist, météo sobre) │
└────────────────────────────────────────────────────────────────────────┘
```

### Niveau 1 — Thèmes & Palettes Déclaratifs (`theme.json`)
Accessible à tous sans coder. Définition déclarative d'une identité visuelle :
```json
{
  "id": "osauce-monochrome-amber",
  "name": "Ambre OLED Minimal",
  "version": "1.0.0",
  "author": "Céleste",
  "license": "MIT",
  "type": "THEME",
  "colors": {
    "background": "#000000",
    "surface": "#101010",
    "accent": "#FFB300",
    "textPrimary": "#FAFAFA",
    "textSecondary": "#808080"
  },
  "typography": {
    "clockFont": "syne_bold",
    "bodyFont": "space_grotesk"
  },
  "matrix": {
    "shape": "ROUNDED_SQUARE",
    "vibrancy": "CALM",
    "particlesEnabled": true
  }
}
```

### Niveau 2 — Shaders Graphiques & Fonds Réactifs (AGSL)
Pour les créateurs d'effets visuels et motion designers.
* Fichier `shader.agsl` interprété nativement par le moteur `FluidBackground` via `RuntimeShader` (Android 13+).
* Calibré pour un rendu fluide à 120 Hz avec delta-time indépendant et consommation batterie ultra-basse.
* *Exemples* : Ondes harmoniques, automates cellulaires (Game of Life interactif), gradients organiques lents, brume de calme.

### Niveau 3 — Tuiles & Add-ons Fonctionnels (Kotlin Compose API)
Pour les développeurs souhaitant enrichir l'Action Hub ou l'Écran Silence :
```kotlin
interface OSauceTilePlugin {
    val id: String
    val displayName: String
    val author: String
    val targetSphere: LifeSphere // HUMAIN, TRAVAIL, VITAL, TEMPS_LIBRE
    
    @Composable
    fun Content(context: PluginContext)
}
```
* **Sécurité & Permissions** : Bac à sable limitant les accès système. Déclaration explicite des permissions requises (`LOCAL_STORAGE`, `READ_CALENDAR_SOBRE`, etc.).

---

## 3. Architecture de l'Écosystème (Web ↔ Cloud ↔ App)

```
┌────────────────────────────────────────────────────────────────────────┐
│                        Site Web OpenSauce (osauce.io)                  │
│  - Création / Connexion de compte (GitHub, Google, Email Magic Link)   │
│  - Hub des créateurs : documentation de l'API, soumission de mods      │
│  - Galerie / Marketplace Web avec prévisualisation en direct           │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │ REST / GraphQL API
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                        Backend Cloud OpenSauce                         │
│  - Service d'Authentification & Gestion des profils                    │
│  - Registry des plugins (métadonnées, versions, stats de téléchargement)│
│  - CDN de distribution des packages (.osmod, .json, .agsl)             │
│  - Pipeline d'analyse statique de sécurité & éco-index                 │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │ Synchronisation & Téléchargement
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                      Application Mobile OSauce                         │
│  - Module Auth / Compte (Gestion du profil in-app ou mode invité)      │
│  - Store In-App (Navigation, recherche par catégorie, filtres)         │
│  - PluginManager & ThemeEngine (Chargement à chaud, activation 1-clic) │
└────────────────────────────────────────────────────────────────────────┘
```

### 1. Le Compte Utilisateur OpenSauce
* **Dualité Web & In-App** : L'utilisateur peut créer son compte indifféremment sur le site web ou directement au sein de l'application mobile.
* **Respect du Libre & Mode Invité** : Aucun compte n'est obligatoire pour utiliser le launcher ou les mods préinstallés. Le compte sert à synchroniser ses mods favoris, noter les extensions et publier ses propres créations.
* **Export / Backup Souverain** : Sauvegarde JSON exportable localement à tout moment (zéro verrouillage propriétaire).

### 2. Le Store de Mods Intégré (In-App)
* Interface sobre et sans bannières publicitaires intégrée aux paramètres d'OpenSauce.
* **Sections** :
  - *Essentiels vérifiés* (mods par défaut pré-inclus).
  - *Thèmes & Palettes*.
  - *Fonds vivants & Shaders*.
  - *Tuiles & Intégrations*.
* **Installation 1-Click** : Téléchargement du package, vérification d'intégrité SHA256, activation à chaud sans relancer l'application.

---

## 4. Outils Développeur & Accessibilité Plug & Play

Pour encourager une communauté vibrante, nous mettons à disposition :
1. **CLI `create-osauce-mod`** : Générateur de templates de mods (Thème, Shader ou Add-on Kotlin) en une seule commande terminal.
2. **Simulateur Web de Thèmes** : Interface web interactive sur `osauce.io/studio` pour concevoir et tester visuellement son `theme.json` avant export.
3. **Documentation Vivante** : Exemples documentés, guides d'optimisation 120 FPS et règles d'éco-conception.

---

## 5. Feuille de Route d'Implémentation Technique

1. **Sprint 1 (Architecture & Clean Core)** :
   - Refactorisation de `MainActivity` vers le pattern MVI (`HomeViewModel`, `HomeUiState` immutable).
   - Découpage modulaire du projet (`:core:designsystem`, `:core:plugin`, `:feature:home`).
2. **Sprint 2 (Moteur de Thèmes & Plugins Local)** :
   - Implémentation de `OSauceThemeEngine` pour appliquer des palettes dynamiques à chaud.
   - Extension de `PluginManager` avec registre local de manifests.
3. **Sprint 3 (Backend & Synchronisation)** :
   - Mise en place de l'API cloud (Auth + Registry des mods).
   - Intégration du client réseau dans l'application mobile.
4. **Sprint 4 (Store In-App & Plateforme Web)** :
   - Écran `ModStoreScreen` dans l'application.
   - Plateforme web `osauce.io` avec catalogue communautaire.
