# Architecture Technique OSauce Launcher (Android & Jetpack Compose)

Ce document formalise l'architecture logicielle du Launcher mobile **OSauce**, developpe en **Kotlin** moderne avec **Jetpack Compose**, intégrant la gestion des Groupes d'Action, l'interception des notifications et le Marketplace de modding communautaire.

---

## 1. Diagramme Global des Composants

```mermaid
flowchart TD

    subgraph UI_Layer["1. Couche Interface (Jetpack Compose)"]
        MainActivity["MainActivity (Host)"]
        SilenceScreen["Ecran 1 : Le Silence"]
        HubScreen["Ecran 2 : Hub d'Action"]
        GroupDetailScreen["Ecran 2-B : Groupe Deplie"]
        ToolsScreen["Ecran 3 : Boîte a Outils"]
        MarketplaceScreen["Ecran 4 : Marketplace Mods"]
        Theme["OSauceTheme (OLED Black)"]
    end

    subgraph Service_Layer["2. Couche Services Android"]
        NotifService["NotificationService (Listener)"]
        PkgManager["PackageManager (Lanceur d'apps)"]
        Shaders["Moteur Shaders AGSL"]
    end

    subgraph Data_Layer["3. Couche Donnees & Plugins"]
        GroupModel["ActionGroup Repository"]
        PluginMgr["PluginManager (Gestionnaire de Mods)"]
        LocalStore[("Base Locale SQLite")]
    end

    subgraph External_Layer["4. Ecosysteme Externe"]
        OsauceHub["Plateforme osauce.io (Mods Verifies)"]
        AndroidOS["Systeme Android Natif"]
    end

    %% Interactions
    MainActivity --> SilenceScreen
    MainActivity --> HubScreen
    MainActivity --> GroupDetailScreen
    MainActivity --> ToolsScreen
    MainActivity --> MarketplaceScreen

    SilenceScreen --> Theme
    HubScreen --> GroupModel
    GroupDetailScreen --> GroupModel
    MarketplaceScreen --> PluginMgr

    NotifService -->|"Route et classe les alertes"| GroupModel
    ToolsScreen -->|"Lance l'application"| PkgManager
    MainActivity -->|"Appels & Photo natifs"| AndroidOS

    PluginMgr <-->|"Telecharge les manifests"| OsauceHub
    GroupModel <--> LocalStore

    classDef toneNavy fill:#0f172a,stroke:#38bdf8,stroke-width:2px,color:#f8fafc;
    classDef toneCard fill:#1e293b,stroke:#64748b,stroke-width:1.5px,color:#f8fafc;
    classDef toneAccent fill:#134e4a,stroke:#2dd4bf,stroke-width:2px,color:#f0fdf4;
    classDef toneOrange fill:#431407,stroke:#fb923c,stroke-width:2px,color:#fff7ed;

    class UI_Layer toneNavy;
    class Service_Layer toneCard;
    class Data_Layer toneAccent;
    class External_Layer toneOrange;
```

---

## 2. Description des Couches Logicielles

### 2.1 Couche Présentation (`app/src/main/kotlin/com/osauce/launcher/ui/`)
* **`MainActivity.kt`** : Point d'entrée de l'application déclaré comme `category.HOME` dans le manifest. Gère la machine à états de navigation entre les 4 écrans sans pile de retour encombrée.
* **`SilenceScreen.kt`** : Écran d'accueil épuré avec l'horloge géante, la jauge de batterie sobre et la ligne d'intention unique.
* **`HubScreen.kt`** : Flux des cartes de groupes de vie unifiant alertes, tâches et raccourcis d'outils.
* **`GroupDetailScreen.kt`** : Vue plein écran intérieure d'un groupe avec conversion rapide d'une notification en tâche todo.
* **`ToolsScreen.kt`** : Liste alphabétique monochrome des applications installées avec recherche instantanée.
* **`MarketplaceScreen.kt`** : Vitrine des extensions et mods communautaires certifiés sans pistage ni publicité.

### 2.2 Couche Services Android (`app/src/main/kotlin/com/osauce/launcher/service/`)
* **`NotificationService.kt`** : Hérite de `NotificationListenerService`. Intercepte les notifications du système, élimine les interruptions visuelles et les redirige de manière déterministe vers le bon groupe d'action (`CATEGORY_MESSAGE`, `CATEGORY_TRANSPORT`, `CATEGORY_EMAIL`).
* **Intégration PackageManager** : Interroge les paquets du système sans surcoût mémoire pour lancer les applications d'un geste.

### 2.3 Couche Modding & Données (`app/src/main/kotlin/com/osauce/launcher/plugin/`)
* **`PluginManager.kt`** : Orchestrateur du cycle de vie des mods communautaires. Charge les manifests, active/désactive les extensions et synchronise le catalogue vérifié avec `osauce.io`.
* **Modèles d'Action** : Structures `ActionGroup`, `ActionNotification` et `ActionTodo` découplées assurant une synchronisation fluide avec l'interface.
