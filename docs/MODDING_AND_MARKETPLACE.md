# Modding Communautaire & Marketplace de Plugins - OSauce

Ce document formalise l'axe strategique du **Modding Communautaire** pour OSauce Launcher. Il definit comment les developpeurs tiers creent, partagent et installent des extensions ouvertes via la plateforme `osauce.io` et la page Marketplace integree dans l'application.

---

## 1. Vision du Modding : La Personnalisation Ethique

Sur les launchers Android classiques ou iOS :
* L'utilisateur est contraint par des fonctions figees ou des packs d'icones payants et fermes.
* Les widgets tiers sont souvent des gouffres a batterie, bourres de publicite ou de traceurs analytiques.

Dans OSauce :
* **Un SDK ouvert et sobre** : N'importe quel developpeur ou etudiant peut concevoir une extension en quelques heures via notre API Kotlin / Compose.
* **Le Marketplace Integre** : Accessible directement depuis l'Ecran 4 du launcher, il permet de decouvrir et d'installer des mods en un clic.
* **Label Verifie FOSS** : Toutes les extensions sont certifiees 100% open source, sans publicite, sans appel reseau masque et sans gaspillage de batterie.

---

## 2. Les 4 Categories de Mods Supportees

### 1. Shaders & Rendu Graphique (AGSL)
* *Description* : Shaders visuels utilisant le langage AGSL d'Android pour enrichir l'Ecran 1 (Silence) ou les transitions de groupes.
* *Exemples* :
  * **Cadran Solaire 24H** : Animation subtile de la course solaire autour de l'horloge hero.
  * **Respiration Visuelle** : Pulsation organique imperceptible calquee sur un rythme de 6 respirations par minute (coherence cardiaque).

### 2. Outils & Passerelles de Productivite
* *Description* : Extensions connectant les Groupes d'Action a des logiciels tiers souverains.
* *Exemples* :
  * **Passerelle Obsidian / Logseq** : Ecrit et synchronise les Todos coches directement dans un coffre Markdown local.
  * **Chrono Focus Pomodoro** : Minuteur calme affichant le temps restant sur l'Intention unique de l'Ecran 1.

### 3. Mini-Widgets & Controles Sobres
* *Description* : Cartes de controle compactes pour piloter un usage sans ouvrir l'application parente.
* *Exemples* :
  * **Lecteur Audio Minimal** : Boutons texte Play/Pause/Next pour VLC ou Spotify.
  * **Suivi Velo / Transports** : Affichage d'une station Velib ou horaire de bus favori en temps reel.

### 4. Packs de Groupes d'Action Pre-configures
* *Description* : Templates de groupes adaptes a des profils particuliers (Medical, Developpeur, Artiste, Parent).
* *Exemples* :
  * Pack « Etudiant Prep » : Groupes automatiquement parametres pour bloquer les distractions pendant les horaires d'etude.

---

## 3. Structure d'un Plugin OSauce (`manifest.json`)

Chaque mod partage sur `osauce.io` comporte un fichier descriptif standardise :

```json
{
  "id": "org.osauce.pomodoro",
  "name": "Chrono Focus Pomodoro",
  "version": "1.0.2",
  "author": "alex_dev",
  "license": "MIT",
  "category": "PRODUCTIVITE",
  "description": "Minuteur de concentration sobre sans alarme stridente, integre a l'Ecran 1.",
  "target_screen": "SILENCE_HERO",
  "permissions": ["LOCAL_STORAGE"],
  "repo_url": "https://github.com/alex_dev/osauce-pomodoro"
}
```

---

## 4. Parcours Utilisateur & Developpeur

```
[ DEVELOPPEUR ]                                         [ UTILISATEUR ]
       |                                                       |
Code son mod en Kotlin                                   Ouvre OSauce Launcher
       |                                                       |
Publie sur osauce.io/developers                         Clique sur "Marketplace"
       |                                                       |
Audit automatique de securite                            Consulte les mods verifies
(Zero pub, zero fuite de data)                                 |
       |                                                       |
Apparaît dans le Marketplace  ----------------------->   Clique sur "Installer" en 1 clic
                                                               |
                                                         Le mod s'active immediatement
```

---

## 5. Modele Economique du Marketplace

1. **Gratuite totale du catalogue de base** : Les outils d'utilite publique restent 100% libres et gratuits.
2. **Pourboires et Mecenat direct aux createurs (Donationware)** : Possibilite pour les utilisateurs de remunerer directement un developpeur tiers sans commission predatrice.
3. **Mise en avant et Certification institutionnelle** : Option pour des ecoles ou tiers de commander des packs d'entreprises certifies.
