# Business Model Canvas (BMC) - Projet OSauce

Ce document formalise l'integralite du Business Model Canvas d'OSauce selon le template officiel de l'ecole, structure en 9 blocs strategiques et relie a l'ODD 12 (Consommation et production responsables).

---

## 1. Vue d'Ensemble Structurée (Les 9 Blocs)

```
+----------------------------------------------------------------------------------------------------+
| 1. PARTENAIRES CLES   | 2. ACTIVITES CLES     | 4. PROPOSITION DE VALEUR | 5. RELATION CLIENT  | 7. CIBLE (SEGMENTS)  |
|                       |                       |                          |                     |                      |
| - Reconditionneurs    | - Developpement OS    | - OS mobile ultra-leger  | - Support Discord   | - Etudiants tech &   |
|   (BackMarket, YesYes)|   (Rust & Slint UI)   |   (< 150 Mo RAM, 120 FPS)|   communautaire     |   Gen Z (18-25 ans)  |
| - Constructeurs       | - WebUSB Installer    | - Anti-obsolescence      | - Centre d'aide     | - Passionnes FOSS    |
|   ethiques (Fairphone)| - Integration sandbox |   (vie smartphone +3 ans)|   & FAQ sur le site |   & Linux mobile     |
| - Communaute FOSS     |   Waydroid isolee     | - Zero bloatware, zero   | - Auto-assistance   | - Acheteurs de       |
|   (postmarketOS)      | - API & BDD PostgreSQL|   tracage, zero pub      |   flashage WebUSB   |   reconditionne      |
| - Hebergeurs souverains| - Audits memoire/secu| - Sandbox Waydroid pour  | - Co-conception     | - Utilisateurs saturés|
|   (Scaleway, OVHcloud)| - Animation communaute|   apps vitales (banque)  |   beta-testeurs     |   par les ecrans     |
|                       +-----------------------+ - WebUSB en 1 clic       +---------------------+ - Flottes pro /      |
|                       | 3. RESSOURCES CLES    | - Economie de 82 kg de   | 6. CANAUX           |   collectivites      |
|                       |                       |   matieres (ADEME)       |                     |                      |
|                       | - Equipe dev Rust/OS  |                          | - Site osauce.io    |                      |
|                       | - CI/CD GitHub Actions|   [ ODD 12 : Consommation| - WebUSB Flasher    |                      |
|                       | - Dépôt SauceHub      |     responsable ]        | - Depots GitHub     |                      |
|                       | - BDD PostgreSQL      |                          | - Reconditionneurs  |                      |
|                       | - Communaute Discord  |                          | - Salons open source|                      |
+-----------------------------------------------+--------------------------+---------------------+----------------------+
| 8. STRUCTURE DE COUTS                                                    | 9. FLUX DE REVENUS                         |
|                                                                          |                                            |
| - Hebergement serveurs d'images systeme, API et base de donnees          | - OSauce Core : 100% gratuit & open source |
| - Bande passante reseau pour le telechargement des images d'installation | - SauceSync Cloud : 2 euros / mois         |
| - Noms de domaine, certificats SSL et infrastructure technique           | - Marge sur smartphones reconditionnes     |
| - Materiel de banc d'essai (smartphones de test Pixel, Fairphone)        |   preinstalles (40 a 60 euros / unite)     |
| - Frais de salons et communication communautaire                         | - Offres Flottes B2B (support entreprise)  |
| - Salaires et developpement de l'equipe a terme                          | - Mecenat open source (GitHub Sponsors)    |
+--------------------------------------------------------------------------+--------------------------------------------+
```

---

## 2. Contenu Détaillé à Reporter par Case

### Case 1 : Partenaires Clés
- **Reconditionneurs francais et europeens** : Ateliers de reconditionnement et plateformes de seconde main (BackMarket, YesYes, Recommerce) pour integrer l'OS sur leurs appareils.
- **Constructeurs de materiels durables** : Fairphone, Pine64, Shiftphone pour optimiser la compatibilite directe sur smartphones reparables.
- **Ecosysteme Linux Mobile & Open Source** : Equipes de postmarketOS (noyau/drivers), Slint UI (moteur graphique), Alpine Linux et Waydroid.
- **Fournisseurs d'infrastructure souveraine** : Hebergeurs francais/europeens a faible empreinte carbone (Scaleway, OVHcloud, Hetzner) pour le stockage des images de build et l'API.
- **Associations et communautes d'interet general** : HOP (Halte a l'Obsolescence Programmee), Framasoft et associations etudiantes pour relayer la sobriete numerique.

### Case 2 : Activités Clés
- **Conception du shell mobile** : Developpement de l'interface graphique en Slint (views, components, tokens) et du moteur systeme natif en Rust.
- **Developpement du WebUSB Installer** : Maintenance de l'outil web permettant de flasher l'OS directement depuis le navigateur via l'API WebUSB / WebADB.
- **Integration et isolation Waydroid** : Configuration de la sandbox conteneurisee pour executer les applications Android obligatoires (banque, transport) de facon etanche.
- **Gestion de l'API & Registry SauceHub** : Developpement du backend securise et de la base PostgreSQL gerant les paquets audites et non intrusifs.
- **Controle qualite et securite** : Verification d'absence de fuites memoire, tests unitaires (`cargo test`) et benchmarks d'autonomie batterie.
- **Animation de la communaute** : Support utilisateur sur Discord, redaction de la documentation technique et traitement des tickets.

### Case 3 : Ressources Clés
- **Ressources humaines** : Equipe technique possedant l'expertise en Rust, Slint UI, developpement systeme Linux embarque et standards WebUSB.
- **Infrastructure technique** : Pipeline d'integration continue GitHub Actions, conteneurisation Docker noVNC et serveurs de distribution d'images.
- **Base de donnees relationnelle** : Instance PostgreSQL robuste garantissant la tracabilite cryptographique des applications et la gestion des comptes SauceSync.
- **Parc materiel de validation** : Smartphones de reference (Google Pixel 3a/4a/6/7, Fairphone 4/5) servant de bancs d'essai pour les releases.
- **Communaute de testeurs** : Base de 200 a 500 etudiants et developpeurs pilotes remontant les bugs et suggestions d'usage.

### Case 4 : Proposition de Valeur (Au Centre - ODD 12)
- **Smartphone allie du calme mental** : Interface epuree sans mur d'applications, sans pastilles rouges et sans bannières anxiogenes (philosophie "Respirer").
- **Performances et autonomie maximales** : Moins de 150 Mo de RAM consommes (au lieu de 3 Go sous Android), rendu fluide a 60/120 FPS sur GPU et autonomie batterie doublee.
- **Anti-obsolescence reelle** : Redonne une seconde jeunesse a des smartphones de plus de 4 ans, prolongeant leur utilisation de 3 a 5 ans supplementaires.
- **Zero concession sur le quotidien** : Waydroid permet d'ouvrir l'application bancaire 3D Secure ou le billet SNCF en conteneur isole sans subir l'ecosysteme publicitaire Android.
- **Installation accessible a tous (WebUSB)** : Flashage en 1 clic via le cable USB dans Chrome ou Edge, sans aucune invite de commande complexe.
- **Impact ecologique mesure (ADEME)** : Chaque terminal revalorise evite 87% de gaz a effet de serre et 82 kg de matieres premieres vierges.
- **Souverainete et confidentialite** : Zero traceur, zero publicite imposee, donnees conservees 100% en local.

### Case 5 : Relation Client
- **Assistance directe et entraide** : Serveur Discord officiel structure par canaux thematiques (`#aide-installation`, `#retours-ui`, `#developpement-rust`).
- **Documentation et auto-formation** : Guides pas-a-pas illustres, FAQ complete et depannage autonome sur le portail web.
- **Transparence et confiance** : Code source integralement auditable sur GitHub et tableau Kanban public des fonctionnalites en cours.
- **Accompagnement dedie B2B** : Support personnalise pour les etablissements pilotes et reconditionneurs partenaires.

### Case 6 : Canaux
- **Site web officiel `osauce.io`** : Vitrine de presentation, simulateur WebAssembly interactif pour tester l'interface en ligne et WebUSB Flasher.
- **Depots de code GitHub** : Distribution open source, releases binaires signees cryptographiquement et documentation Markdown.
- **Boutiques en ligne des reconditionneurs** : Vente directe de terminaux d'occasion avec OSauce preinstalle comme argument commercial differentiant.
- **Salons et evenements specialises** : Rencontres open source (FOSDEM, Capitole du Libre), forums etudiants et ateliers de demontage/reparation (Repair Cafes).

### Case 7 : Cible (Segments Clients)
- **Etudiants et jeunes adultes (18-25 ans / Gen Z - Persona Marc)** : Utilisateurs intensifs cherchant a sortir de la dependance attentionnelle et du doomscrolling sans renoncer a leurs outils vitaux.
- **Developpeurs et partisans du logiciel libre (FOSS)** : Utilisateurs exigeant un systeme verifiable, sans pistage opaque et performant.
- **Consommateurs a budget contraint** : Particuliers refusant de depenser 800 a 1 200 euros dans un smartphone neuf et privilegiant la seconde main.
- **Ecoles et flottes professionnelles de terrain** : Entreprises et collectivites necessitant des smartphones fiables, sans distraction et securises pour leurs collaborateurs.

### Case 8 : Structure de Coûts
- **Infrastructure cloud** : Hebergement de l'API, base de donnees PostgreSQL et bande passante pour le telechargement des images systeme OSauce.
- **Services web & securite** : Noms de domaine, serveurs DNS, certificats SSL/TLS et sauvegardes automatisees.
- **Banc d'essai et materiel de test** : Achat de smartphones de seconde main compatibles pour valider chaque nouvelle version du firmware.
- **Communication et visibilite** : Participation aux salons specialises, supports pedagogiques et ateliers de demonstration.
- **Frais d'equipe a terme** : Salaires et prestations des developpeurs et mainteneurs du projet.

### Case 9 : Flux de Revenus
- **OSauce Core (Open Source)** : 100% gratuit en telechargement libre pour les particuliers et contributeurs autonomes.
- **SauceSync Cloud (Abonnement Freemium - 2 €/mois)** : Synchronisation automatique et chiffree de bout en bout des reglages, notes et contacts sur serveurs souverains francais.
- **Partenariat reconditionneurs (Marge materielle)** : Marge brute de 40 a 60 euros sur chaque smartphone reconditionne vendu clef-en-main avec OSauce preinstalle.
- **Offres Entreprises & Flottes B2B** : Prestations de support technique dedie, personnalisation de l'OS et configuration de flottes closes.
- **Mecenat et dons communautaires** : Financement via Open Collective et GitHub Sponsors pour soutenir la recherche et developpement.
