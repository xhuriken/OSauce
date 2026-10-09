# Cahier des Charges & Master Prompt Figma AI : Prototype Mobile OSauce

Ce document constitue le prompt d'ingénierie de prompt complet, exhaustif et directement exploitable par une intelligence artificielle de génération d'interfaces (Figma AI, Galileo, v0, Claude Artifacts) pour concevoir l'intégralité du prototype du système d'exploitation mobile **OSauce**.

---

## 1. Méta-Instructions & Contexte Fondamental

* **Nom du Produit** : OSauce (Système d'exploitation mobile sobre, durable et éco-conçu en Rust).
* **Format d'Écran (Viewport)** : 390 x 844 px (Format smartphone moderne standard, coins arrondis 44px, zone dynamique d'îlot / encoche respectée).
* **Philosophie Graphique** : Anti-distraction, zéro dopamine industrielle, sobriété numérique radicale, typographie éditoriale épurée, fond noir OLED absolu (#000000).
* **Règle Zéro Emoji (Absolue)** : Aucun émoji sur aucun écran. Utiliser exclusivement des icônes vectorielles en trait fin (outline SVG de 1.5px de graisse), des repères géométriques minimaux ou des micro-labels textuels en capitales.

---

## 2. Design System & Tokens Visuels

### Palette de Couleurs (Mode Sombre OLED Natif)
* **Background Root** : `#000000` (Noir OLED pur, extinction physique des pixels pour économie de batterie).
* **Surface Card Level 1** : `#121212` (Gris très sombre profond pour les conteneurs et cartes).
* **Surface Card Level 2** : `#1C1C1E` (Gris légèrement contrasté pour les sous-blocs et champs d'action).
* **Bordures & Séparateurs** : `#2C2C2E` (Filet ultra-fin de 1px, pas d'ombres portées agressives).
* **Texte Primaire** : `#F5F5F7` (Blanc cassé haute lisibilité, non éblouissant).
* **Texte Secondaire** : `#8E8E93` (Gris neutre intermédiaire pour métadonnées, dates et sous-titres).
* **Texte Muted** : `#636366` (Gris assourdi pour indications tertiaires et labels).
* **Accents Sémantiques Pastels (Zéro néon agressif)** :
  * *Accent Calme / Validation* : `#88C0D0` (Vert sauge / menthe douce, pour autonomie et validation).
  * *Accent Attention / Temps* : `#EBCB8B` (Ambre doux atténué, pour horaires et alertes modérées).
  * *Accent Action / Liens* : `#81A1C1` (Bleu ardoise froid, pour boutons d'action).

### Typographie
* **Police Principale** : Sans-Serif géométrique sobre (Inter, SF Pro, ou Roboto).
* **Police Chiffres / Télémétrie** : Monospace moderne (JetBrains Mono, SF Mono).
* **Hiérarchie des Tailles** :
  * Horloge Géante (Hero Clock) : 68px, graisse Light (300), letter-spacing -2px.
  * Titres de Groupes (H1) : 20px, graisse Semi-Bold (600).
  * Sous-titres & Sections (H2) : 12px, graisse Bold (700), texte en MAJUSCULES, letter-spacing +1.2px.
  * Corps de Texte (Body) : 15px, graisse Regular (400), line-height 20px.
  * Micro-labels (Pill badges) : 11px, graisse Medium (500).

### Formes & Géométrie
* **Cartes principales** : Rayon de courbure 20px (border-radius: 20px).
* **Boutons & Pastilles rapides** : Format pilule intégrale (border-radius: 999px).
* **Marges extérieures (Screen Padding)** : 20px gauche et droite, 14px haut et bas.
* **Espacement vertical standard (Spacing)** : 12px entre éléments liés, 24px entre sections.

---

## 3. Architecture de Navigation Globale (3 Écrans Clés)

La navigation ne reproduit PAS la grille d'icônes d'Android/iOS. Elle s'organise en trois niveaux d'intention :

```
[ ÉCRAN 1 : LE SILENCE ]
      |
      | Swipe vers le Haut
      v
[ ÉCRAN 2 : LE HUB D'ACTION ]  <--->  [ ÉCRAN 2-B : GROUPE DÉPLIÉ ]
      |
      | Swipe vers la Gauche (ou bouton Tiroir)
      v
[ ÉCRAN 3 : LA BOÎTE À OUTILS ]
```

---

## 4. Spécifications Détaillées Écran par Écran

---

### ÉCRAN 1 : « Le Silence » (Accueil & Veille Active)
*Rôle : Permet de consulter l'heure, l'autonomie et l'information vitale immédiate sans aucune tentation de distraction.*

#### 1. Barre d'État Supérieure (Status Bar Minimaliste - 32px de haut)
* À gauche : Horloge discrète en vue intérieure ou indicateur réseau sobre. (Aucun texte "Mode nuit", le thème sombre OLED étant permanent et unique).
* À droite : Jauge d'autonomie fine vectorielle avec texte `84% - Reste 1j 18h` (chiffre vert sauge `#88C0D0`).
* Zéro icône de wifi clignotante ou de bluetooth inutile.

#### 2. Bloc Central : Horloge & Intention (Hero Section)
* **Horloge Géante** : `22:48` en blanc pur, graisse fine (300), taille 68px.
* **Badge Secondes** : Juste à droite des minutes, un petit rectangle arrondi contenant `36 SEC` en police monospace verte.
* **Date complète** : En-dessous, centré : `Lundi 5 octobre 2026` (gris `#8E8E93`, taille 14px).
* **L'Intention Unique du Moment (Dynamic Context Bar)** :
  * Un conteneur pilule horizontal centré de 40px de haut, fond `#121212`, bordure 1px `#2C2C2E`.
  * Contenu dynamique :
    * *Cas 1 (Événement proche)* : `Prochain train a 14h15` + bouton flèche `[ Billet ]`.
    * *Cas 2 (Focus programmé)* : `Session Focus Cours jusqu'a 12h00`.
    * *Cas 3 (Aucune urgence)* : `Aucune urgence. Respire.`
  * Zéro alerte agressive.

#### 3. Indicateur de File d'Attente Discret
* Situé au milieu inférieur :
  * Texte sobre centré : `3 elements en attente dans 2 groupes`.
  * Pas de pastille rouge, pas de compteur clignotant.
  * Micro-indicateur vectoriel (chevron pointant vers le haut) invitant au glissement tactile.

#### 4. Dock d'Urgence Minimal (Bas d'Écran)
* Une rangée de 3 boutons pilules épurés (hauteur 44px, fond `#121212`, texte blanc 13px) :
  * `[ TEL ]` : Ouvre le clavier d'appel d'urgence.
  * `[ ACTION ]` : Bouton central plus large invitant à déployer le Hub d'Action.
  * `[ PHOTO ]` : Déclenchement instantané de l'appareil photo.

---

### ÉCRAN 2 : « Le Hub d'Action » (La Matrice des Groupes)
*Rôle : Rassemble en un lieu unique les notifications, les tâches et les applications au sein de groupes de vie cohérents.*

#### 1. En-tête de Navigation (Header)
* Titre principal en haut à gauche : `Mes Espaces` (24px, bold).
* À droite : Bouton discret `[ + Groupe ]` pour ajouter une catégorie sur-mesure.
* Sous-titre : `4 groupes actifs - Mode Silencieux actif`.

#### 2. Liste des Cartes de Groupes (Cards Feed)
Chaque groupe est une carte rectangulaire compacte (fond `#121212`, bordure 1px `#2C2C2E`, rayon 20px, marge interne 18px).

##### Carte 1 : « Humain & Proches »
* **En-tête de carte** :
  * Titre : `Humain & Proches` (16px, Semi-Bold).
  * Statut d'attention : `2 nouveaux messages` (pastille bleue discrète `#81A1C1`).
* **Aperçu des éléments** :
  * Ligne 1 : `Maman : Tu passes ce soir ?` (extrait de texte sur 1 ligne).
  * Ligne 2 (Tâche Todo) : `[ ] Rappeler Lucas avant 20h`.
* **Mini-Dock d'applications du groupe** (4 icônes textuelles compactes 32x32px) :
  * `[ TEL ]` `[ SMS ]` `[ SIGNAL ]` `[ CONTACTS ]`.

##### Carte 2 : « Travail & Études »
* **En-tête de carte** :
  * Titre : `Travail & Études`.
  * Statut : `En veille jusqu'a demain 08:00` (mode horaire programmé activé).
* **Aperçu des éléments** :
  * Ligne 1 (Tâche Todo) : `[ ] Deposer le rapport final (PDF)`.
* **Mini-Dock d'applications du groupe** :
  * `[ MAIL ]` `[ AGENDA ]` `[ NOTES ]` `[ FICHIERS ]`.

##### Carte 3 : « Quotidien & Vital »
* **En-tête de carte** :
  * Titre : `Quotidien & Vital`.
  * Statut : `1 alerte traitee`.
* **Aperçu des éléments** :
  * Ligne 1 : `SNCF : Billet TGV 8421 confirme`.
  * Ligne 2 (Tâche Todo) : `[ ] Composter billet avant depart`.
* **Mini-Dock d'applications du groupe** :
  * `[ BANQUE ]` `[ SNCF ]` `[ PLANS ]` `[ HORLOGE ]`.

##### Carte 4 : « Temps Libre & Découverte »
* **En-tête de carte** :
  * Titre : `Temps Libre`.
  * Statut : `Silence total`.
* **Aperçu des éléments** :
  * Ligne 1 (Tâche Todo) : `[ ] Ecouter podcast tech`.
* **Mini-Dock d'applications du groupe** :
  * `[ MUSIQUE ]` `[ PODCAST ]` `[ LIVRE ]` `[ RADIO ]`.

---

### ÉCRAN 2-B : « Le Groupe Déplié » (Vue Intérieure d'un Groupe)
*Rôle : S'affiche en plein écran quand l'utilisateur clique sur une carte de groupe (ex: « Travail & Études »).*

#### 1. Barre Supérieure de Retour
* Bouton `<- Retour` à gauche.
* Titre central : `Travail & Études`.
* Bouton d'options à droite : `[ Regler ]` (pour modifier les filtres du groupe).

#### 2. Section 1 : « CE QUI ATTEND » (Alertes & Notifications filtrées)
* Titre de section en gris clair : `CE QUI ATTEND (1 ELEMENT)`.
* Item Notification (Conteneur fond `#1C1C1E`, bordure 1px) :
  * Expéditeur & Heure : `M. Dupont - Professeur | 11:34`.
  * Message : `Veuillez verifier la section 2 du rapport avant envoi.`
  * Deux boutons d'action rapide intégrés sous le message :
    * `[ Repondre ]` (ouvre la rédaction de mail).
    * `[ Convertir en Todo ]` (transforme l'alerte en case à cocher sans rien retaper).

#### 3. Section 2 : « CE QUE J'AI PRÉVU » (Tâches & Todos du groupe)
* Titre de section : `MES TACHES DU GROUPE`.
* Items Checklist :
  * `[x] Relire la bibliographie` (texte barré, estompé à 40%).
  * `[ ] Deposer le rapport final avant 17h00` :
    * Bouton d'action contextuel lié à droite : `[ Ouvrir Fichiers ]`.
* Champ rapide d'ajout : `[ + Ajouter une tâche rapide... ]`.

#### 4. Section 3 : « OUTILS DU GROUPE » (Applications associées)
* Titre de section : `APPLICATIONS ASSOCIEES`.
* 4 grandes touches horizontales ergonomiques (50px de hauteur) :
  * `[ Icone Mail ]  Messagerie Academique`
  * `[ Icone Agenda ]  Calendrier des Cours`
  * `[ Icone Fichiers ]  Gestionnaire de Documents`
  * `[ Icone Notes ]  Bloc-notes Minimal`

#### 5. Bouton d'Action Terminal (En bas)
* Bouton pleine largeur (fond `#2C2C2E`, texte blanc `#F5F5F7`, hauteur 52px, border-radius 16px) :
  * `[ TOUT CLOTURER ET RANGER LE GROUPE ]`
  * Effet : efface les alertes lues, replie l'écran et renvoie vers le Hub.

---

### ÉCRAN 3 : « La Boîte à Outils » (Tiroir d'Applications Global)
*Rôle : Accéder à un outil rare de manière sobre, sans jamais encourager le doomscrolling.*

#### 1. Barre de Recherche Instantanée (Top Bar)
* Champ de saisie proéminent (hauteur 48px, fond `#121212`, bordure 1px `#2C2C2E`, texte blanc).
* Placeholder : `Rechercher une application ou un outil...`.
* Icône loupe vectorielle ultra-fine à gauche.

#### 2. Liste Alphabétique Textuelle Épurée
* Zéro grille d'icônes rondes colorées.
* Liste verticale à défilement fluide, groupée par lettre :
  * `A`
    * `Agenda` | Outil natif
    * `Appareil photo` | Outil natif
  * `B`
    * `Banque Populaire` | Sandbox Waydroid (Badge discret gris `Securise`)
  * `C`
    * `Calculatrice` | Outil natif
    * `Contacts` | Outil natif
  * `S`
    * `SNCF Connect` | Sandbox Waydroid
    * `Signal` | Outil natif

---

### ÉCRAN 4 (Modal / Overlay) : « Personnaliser un Groupe »
*Rôle : Permet à l'utilisateur de configurer ses propres règles et catégories sans dépendre de réglages obscurs.*

* **Titre du modal** : `Parametrer : Travail & Etudes`.
* **Champ 1 (Nom du groupe)** : Champ de texte éditable `Travail & Etudes`.
* **Champ 2 (Comportement d'attention)** : 3 options radio-boutons :
  * `Immediat` : Vibre et signale dès réception.
  * `Differe` : S'accumule en silence, aucune sonnerie.
  * `Plage horaire` : Actif de `08:00` a `18:00`, mis en veille le week-end.
* **Champ 3 (Applications autorisees dans ce groupe)** : Liste de cases à cocher avec recherche.
* **Boutons de validation** :
  * `[ Enregistrer les modifications ]` (Bouton d'accent `#88C0D0`, texte noir).
  * `[ Supprimer ce groupe ]` (Texte rouge discret).

---

## 5. Interactions & Gestes Tactiles Spécifiés

1. **De l'Écran 1 vers l'Écran 2** : Glissement du doigt vers le haut (Swipe Up) fluide.
2. **De l'Écran 1 vers l'Écran 3** : Glissement vers la gauche (Swipe Left) ou clic sur un bouton tiroir.
3. **Sur une Notification (dans un groupe)** :
   * *Swipe court vers la droite* : Transforme instantanément la notification en tâche Todo dans le groupe.
   * *Swipe vers la gauche* : Efface et classe la notification comme lue.
   * *Appui long (Long Press)* : Ouvre le menu contextuel rapide `[ Déplacer vers un autre groupe ]`.
4. **Sur une Tâche Todo** : Clic sur la case à cocher -> micro-animation de barré avec temporisation de 500 ms avant rangement.

---

## 6. Prompt de Synthèse à Copier Directement dans Figma AI

> *"Design a mobile OS interface for a minimalist, anti-distraction smartphone OS called OSauce. Viewport: 390x844px. Theme: Pure OLED black (#000000), dark grey cards (#121212, #1C1C1E), 1px border (#2C2C2E), white text (#F5F5F7), sage green accent (#88C0D0). Absolutely ZERO emojis. Use vector outline icons only. Screen 1 is a Calm Home with a 68px light hero clock, battery pill ('84% - 1d 18h left'), and a single dynamic context line ('Next train at 14:15 [Ticket]'). Screen 2 is an Action Matrix Hub with 4 thematic life groups (Human & Family, Work & Study, Daily & Vital, Leisure & Quiet), each card combining incoming notifications, active todos, and 4 app shortcuts. Screen 2B is the Expanded Group View showing 3 distinct sections: Incoming Alerts with 'Convert to Todo' button, Active Todos checklist with direct app trigger, and Group Tools. Screen 3 is an All Apps Drawer with a search bar and a clean alphabetical typographic list. Modern, serene, editorial, ultra-fast UX."*
