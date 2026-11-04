# Guide des Skills pour le Vibecoding & OSauce

Ce document récapitule l'ensemble des skills installés dans le projet (`.agents/skills/`) et au niveau global de l'IDE (`~/.gemini/config/skills/`), leur utilité, ainsi que leurs commandes d'activation.

---

## Sommaire des Skills

1. [Caveman & Ultracave (Token Compression)](#1-caveman--ultracave)
2. [Graphify (Cartographie & Contexte Codebase)](#2-graphify)
3. [Apple Design (Physique & Fluidité Apple)](#3-apple-design)
4. [Animate (Construction de Motion Emil Kowalski)](#4-animate)
5. [Emil Design Engineering (Polish & Craft UI)](#5-emil-design-engineering)
6. [Android & Jetpack Compose Architecture (Architecture Propre)](#6-android--jetpack-compose-architecture)
7. [Compose Performance & Stability (Fluidité 120 FPS)](#7-compose-performance--stability)
8. [B-Board Integration (Kanban & Devlogs)](#8-b-board-integration)

---

## 1. Caveman & Ultracave

> **Rôle :** Élimine le bavardage et les politesses de l'IA pour préserver la fenêtre de contexte et accélérer les réponses tout en gardant 100% de la rigueur technique.

- **Fichiers :** [`.agents/skills/caveman/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/caveman/SKILL.md), [`.agents/skills/ultracave/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/ultracave/SKILL.md)
- **Déclenchement :**
  - `/caveman` ou *"caveman mode"* : style concis, direct au but, code intact.
  - `/ultracave` ou `/caveman ultra` : compression maximale, style télégraphique.
  - *"stop caveman"* ou *"normal mode"* : retour au langage habituel.

---

## 2. Graphify

> **Rôle :** Analyse statique par AST (Abstract Syntax Tree) pour transformer le projet en graphe de connaissances navigable sans passer par une base vectorielle lourde.

- **Fichier :** [`.agents/skills/graphify/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/graphify/SKILL.md)
- **Ce qu'il fait :**
  - Identifie les classes centrales ("God Nodes") et les couplages inattendus.
  - Génère une visualisation interactive HTML (`graphify-out/graph.html`).
  - Permet à l'agent de naviguer dans les dépendances d'un gros projet sans relire tous les fichiers.
- **Déclenchement :**
  - `/graphify` : analyse le dossier courant.
  - `/graphify path "HomeScreen" "LauncherSettings"` : trouve le chemin le plus court entre deux composants.
  - `/graphify explain "EssentialTilesGrid"` : détaille les dépendances du composant.

---

## 3. Apple Design

> **Rôle :** Traduit la philosophie d'interface Apple (WWDC *Designing Fluid Interfaces*) pour des applications tactiles et fluides.

- **Fichier :** [`.agents/skills/apple-design/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/apple-design/SKILL.md)
- **Principes clés :**
  - **Latence zéro :** le retour visuel commence dès l'appui (`pointer-down`), jamais au relâchement.
  - **Manipulation directe :** suivi 1:1 du doigt sans recadrage arbitraire.
  - **Interruptibilité :** toute animation en cours doit pouvoir être attrapée et inversée au vol.
  - **Ressorts (Springs) :** utilisation de physiques de ressorts plutôt que de courbes temporelles fixes.
  - **Résistance élastique (Rubber-banding) :** les butées ne s'arrêtent pas brutalement.
- **Déclenchement :**
  - Demander : *"Applique les principes Apple Design à notre gesture de swipe"* ou *"Revois les transitions des tuiles selon Apple Design"*.

---

## 4. Animate

> **Rôle :** Guide de décision méthodique pour concevoir des animations basées sur la philosophie d'Emil Kowalski (créateur de Sonner, ex-Vercel/Linear).

- **Fichier :** [`.agents/skills/animate/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/animate/SKILL.md)
- **Règles d'or :**
  - **Filtre de fréquence :** une action répétée 100+ fois par jour (recherche launcher, appui clavier) **ne doit pas avoir d'animation**.
  - **Durées :** toujours inférieures à 300ms (100-150ms pour le feedback tactile, 200-250ms pour les bottom sheets).
  - **Interdictions :** jamais de `scale(0)` à l'apparition (partir de `0.95` avec `alpha 0`), jamais d'`ease-in` à l'entrée d'un composant UI.
- **Déclenchement :**
  - *"Anime l'apparition de cette tuile"*, *"Crée une transition pour le tiroir d'applications"*.

---

## 5. Emil Design Engineering

> **Rôle :** Sensibilité craft et revue de code frontend/UI pour déceler les détails invisibles qui font l'excellence d'un produit.

- **Fichier :** [`.agents/skills/emil-design-eng/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/emil-design-eng/SKILL.md)
- **Format de revue :** Génère un tableau comparatif `Before` / `After` / `Why` lors des audits d'écrans ou de composants.
- **Déclenchement :**
  - *"Revois le design de notre écran d'accueil avec le skill emil-design-eng"*.

---

## 6. Android & Jetpack Compose Architecture

> **Rôle :** Garantit une Clean Architecture stricte et le pattern MVI pour un launcher Android robuste et testable.

- **Fichier :** [`.agents/skills/android-compose-architecture/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/android-compose-architecture/SKILL.md)
- **Structure imposée :**
  - `core:domain` : pur Kotlin, zéro import Android/Compose, UseCases unitaires.
  - `core:data` : repositories, DataStore, Room.
  - `core:designsystem` : tokens de couleur, typographie, composants réutilisables.
  - `feature:*` : écrans Compose et ViewModels suivant le cycle `UiState` (StateFlow immuable) + `UiIntent` + `UiEffect`.
- **Déclenchement :**
  - *"Conçois l'architecture de la fonctionnalité X"*, *"Refactorise ce ViewModel en MVI pur"*.

---

## 7. Compose Performance & Stability

> **Rôle :** Techniques d'optimisation pour éliminer les saccades, les recompositions inutiles et garantir du 120 FPS constant.

- **Fichier :** [`.agents/skills/compose-performance/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/compose-performance/SKILL.md)
- **Points de vigilance :**
  - Utilisation d'`ImmutableList` au lieu de `List` standard.
  - Décalage de la lecture d'état dans la phase de dessin (`Modifier.graphicsLayer` ou `Modifier.offset { ... }`).
  - Clés stables obligatoires dans les listes (`key = { it.id }`).
- **Déclenchement :**
  - *"Optimise les recompositions de la grille de tuiles"*, *"Dépanne les lags de scroll"*.

---

## 8. B-Board Integration

> **Rôle :** Connexion MCP native au tableau Kanban du projet OSauce pour synchroniser les tickets, déplacer les cartes et consigner les devlogs de développement.

- **Fichier :** [`.agents/skills/b-board/SKILL.md`](file:///c:/Users/celes/Projects/OSauce/.agents/skills/b-board/SKILL.md)
- **Déclenchement :**
  - *"Consigne un devlog sur la tâche en cours"*, *"Montre les tickets en cours sur le kanban"*.

