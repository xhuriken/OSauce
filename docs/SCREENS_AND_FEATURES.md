# OSauce — Spécification des Écrans & Navigation

Guide de référence produit d'OpenSauce par défaut (sans plugin externe).

---

## 1. L'Elevator Pitch (Matrice des 6 Blocs)

Cadre de positionnement officiel d'OpenSauce, formulé du point de vue d'un développeur passionné qui résout un problème vécu :

| Bloc | Description | Contenu |
|---|---|---|
| **POUR ?** | *La cible visée* | Les personnes (étudiants, créatifs, jeunes actifs) qui veulent un téléphone qui redevient un outil utile et apaisant, sans couper les ponts avec le monde moderne. |
| **QUI ?** | *Le problème vécu* | En ont marre de passer 5h par jour sur leur smartphone sans s'en rendre compte, distraits par des flux mélangés et des interfaces conçues pour capter leur temps. |
| **NOTRE PRODUIT** | *La nature du projet* | **OpenSauce (OSauce)** est un launcher Android libre, éco-conçu et modulaire développé nativement en Kotlin et Jetpack Compose. |
| **QUI PERMET ?** | *Les capacités concrètes* | D'avoir un accueil calme avec l'heure en grand et 4 essentiels, de séparer sa vie en Sphères étanches (*Humains, Travail, Vital, Loisirs*), et de lancer n'importe quelle app au clavier en 2 secondes. |
| **CONTRAIREMENT** | *Aux alternatives existantes* | Aux surcouches constructeurs lourdes et opaques, ou aux « dumbphones » rétrogrades qui bloquent les banques, le GPS et les billets de train. |
| **NOUS** | *Le différenciateur unique* | Proposons un système 100% transparent, fluide sur n'importe quel téléphone, avec un fond interactif sensoriel et une architecture ouverte aux mods de la communauté. |

---

## 2. Trame Vidéo de Présentation (5 Minutes — Ton Chill)

| Timing | Séquence | Ce qu'on montre & dit simplement |
|---|---|---|
| **0:00 - 1:00** | **Le Constat** | Montrer un téléphone encombré (tout est mélangé : messages persos, mails du travail, alertes inutiles). L'objectif d'OSauce : poser les choses, retrouver un téléphone calme, sans bloatware. |
| **1:00 - 2:00** | **Écran 1 : Silence** | Déverrouillage : grande horloge Space Grotesk, fond interactif discret qui réagit au doigt (*The Sauce*), 4 raccourcis essentiels. Fluide sur n'importe quel téléphone Android (récent ou ancien). |
| **2:00 - 3:15** | **Écran 2 : Action Hub & Sphères** | Glisser à droite. Présentation des Sphères réelles (*Humains, Travail, Vital, Loisirs*). Tap sur *Travail* : n'affiche que les outils pro et les tâches du domaine. Saisie rapide en bas et swipe sur les tâches (strikethrough progressif). |
| **3:15 - 4:00** | **Écran 3 : Tiroir d'apps** | Glisser vers le haut : recherche immédiate au clavier, liste claire et rail A-Z. Trouver une app en 2 secondes au lieu de chercher parmi 50 icônes. |
| **4:00 - 4:45** | **Écran 4 : Réglages, Compte & Mods** | Personnalisation du fond et des couleurs. Rappel éthique : mode local par défaut (base SQLite sur l'appareil), et compte OpenSauce pour les créateurs qui souhaitent publier leurs mods ou synchroniser leurs favoris. |
| **4:45 - 5:00** | **Conclusion** | Projet gratuit, FOSS et disponible sur GitHub. |

---

## 3. Modèle Spatial & Navigation 2D

La navigation repose sur un repère orthogonal simple, sans empilement complexe de fenêtres :

```mermaid
flowchart TD
    Silence["Écran 1 : SILENCE (Accueil)"]
    ActionHub["Écran 2 : ACTION HUB (Sphères de Vie)"]
    SphereFocus["Écran 2-B : FOCUS SPHÈRE (Détail isolé)"]
    AppDrawer["Écran 3 : APP DRAWER (Tiroir d'apps)"]
    Settings["Écran 4 : RÉGLAGES"]
    EditModal["Modal 5 : ÉDITEUR DE SPHÈRE"]

    Silence -- "Glisser vers la droite (Pager 1:1)" --> ActionHub
    ActionHub -- "Glisser vers la gauche / Retour" --> Silence
    ActionHub -- "Tap sur une carte Sphère" --> SphereFocus
    SphereFocus -- "Bouton Retour / Tap Toutes" --> ActionHub
    Silence -- "Glisser vers le haut" --> AppDrawer
    AppDrawer -- "Glisser vers le bas / Retour" --> Silence
    Silence -- "Appui long 500ms sur le fond" --> Settings
    Settings -- "Fermer / Retour" --> Silence
    ActionHub -- "Bouton [+ Sphère] ou Options" --> EditModal
    SphereFocus -- "Bouton Options Sphère" --> EditModal
```

---

## 4. Spécification Détaillée des Écrans

### Écran 1 : SILENCE (Accueil)
* **Rôle** : Accueil zen, zéro distraction.
* **Composants** :
  1. **Horloge Space Grotesk** : Chiffres géants (96sp). Un clic dessus affiche ou masque les secondes en fondu.
  2. **Jauge d'autonomie Canvas** : Trait vectoriel discret sous l'horloge montrant le niveau de batterie sans chiffre anxiogène.
  3. **Matrice « The Sauce »** : Fond dynamique réactif au toucher (vagues de couleur et traînée particulaire fluide au glisser). Physique delta-time qui s'adapte automatiquement au taux de rafraîchissement de l'écran (60Hz ou plus).
  4. **Phrase personnalisée** : Texte court sous l'horloge (ex: *"Un seul pas à la fois"*).
  5. **Dock 4 essentiels** : Raccourcis directs (Téléphone, Messages, Caméra, Applications) avec micro-scale tactile (0.94x).

---

### Écran 2 : ACTION HUB (Dashboard des Sphères)
* **Rôle** : Vue d'ensemble de ses domaines de vie et capture d'intentions.
* **Composants** :
  1. **En-tête** : Titre `ACTION HUB` + `Sphères & Intentions` avec bouton visible **`[ + Sphère ]`** en haut à droite.
  2. **Cartes de Sphères (`SphereCard` en 2 colonnes)** :
     - 4 sphères par défaut : **Humains** (`#4ADE80`), **Travail** (`#60A5FA`), **Vital** (`#FBBF24`), **Loisirs** (`#A78BFA`), plus les sphères créées par l'utilisateur.
     - Chaque carte affiche :
       * Symbole géométrique vectoriel (1.5dp, sans emoji).
       * Nom de la sphère et halo de couleur.
       * Badge du nombre de tâches en attente (ex: `2 tâches`).
       * Mini-icônes des applications assignées (ex: Slack/Mail pour Travail).
       * Bouton d'édition sur la carte.
     - Carte **`[ + Créer une sphère ]`** en pointillé à la fin de la grille.
  3. **Barre de Capture Rapide** : Champ texte en bas avec bouton `+`. Taper Entrée enregistre immédiatement la tâche en SQLite local.
  4. **Tâches en cours** :
     - Clic case circulaire : rebond tactile et traçage progressif de la ligne barrée de gauche à droite.
     - Glisser vers la droite (Swipe right) : valide la tâche.
     - Glisser vers la gauche (Swipe left) : supprime la tâche.

---

### Écran 2-B : FOCUS SPHÈRE (Détail étanche)
* **Rôle** : Isoler un contexte mental pour travailler ou se détendre sans distraction externe.
* **Composants** :
  1. **Bandeau de navigation** : Bouton `← Toutes les sphères`, nom et couleur de la sphère, bouton paramètres.
  2. **Dock d'applications de la sphère** : Uniquement les applications affectées à cette sphère (ex: Slack, Gmail, Notion pour Travail). Lancement direct en 1 tap.
  3. **Capture ciblée** : Toute nouvelle tâche saisie ici est pré-assignée à cette sphère.
  4. **Tâches du domaine** : Liste filtrée exclusive des tâches en cours et terminées de ce domaine.

---

### Écran 3 : APP DRAWER (Tiroir d'applications)
* **Rôle** : Recherche intentionnelle rapide sans se perdre dans les icônes.
* **Composants** :
  1. **Champ de recherche** : Clavier ouvert automatiquement dès le swipe pour filtrer au texte.
  2. **Rail alphabétique A-Z** : Tranche tactile sur le bord droit pour sauter directement à une lettre.
  3. **Liste d'applications monochromes** : Icônes traitées en noir & blanc pour calmer les stimuli visuels.
  4. **Pastille de sphère** : Indicateur de couleur discret montrant le domaine de l'application.
  5. **Menu contextuel (Appui long)** : Assigner à une sphère, ouvrir les informations système, désinstaller.

---

### Écran 4 : RÉGLAGES & THE SAUCE
* **Rôle** : Configurer le comportement et l'esthétique du lanceur.
* **Composants** :
  1. **Lanceur par défaut** : Déclencheur direct du sélecteur Android (`ACTION_HOME_SETTINGS`).
  2. **Matrice « The Sauce »** :
     - Vibrance : *Subtile*, *Équilibrée*, *Éclatante*.
     - Forme des cellules : *Carrés arrondis*, *Cellules organiques*, *Croix matrice (+)*.
     - Densité : *Aéré (10x21)*, *Normal (15x32)*, *Dense (20x42)*.
     - Effets : Particules au toucher, traînée au glisser (*Drag Trail*).
  3. **Thèmes OLED** : Presets de couleurs d'accent.
  4. **Compte & Synchronisation** : Statut du compte OpenSauce (Invité local ou Connecté créateur).
  5. **Personnalisation** : Modification de la phrase de calme et toggle des secondes de l'horloge.

---

### Modal 5 : ÉDITEUR / CRÉATEUR DE SPHÈRE (`EditSphereDialog`)
* **Rôle** : Créer ou modifier une sphère en quelques secondes.
* **Composants** :
  1. **Nom** : Champ texte (ex: *Finances, Famille, Studio*).
  2. **Palette 8 couleurs** : Teintes sobres accordées au design OSauce.
  3. **Symboles géométriques (8 icônes)** : Utilisateurs, Mallette, Onde vitale, Feuille, Étoile, Bouclier, Terminal, Boussole (zéro emoji).
  4. **Sélection des applications** : Liste des applications du téléphone avec cases à cocher pour définir les membres de la sphère.
  5. **Suppression** : Bouton supprimer actif pour les sphères personnalisées (protège les 4 sphères par défaut).
