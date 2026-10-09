# Dossier Projet & Etude de Marche - OSauce

Ce document synthetise la strategie produit, l'integration dans le referentiel CDA (RNCP 37873, Niveau 6), l'etude de marche chiffree et la viabilite economique d'OSauce.

---

## 1. Synthese Produit & Referentiel CDA (Niveau 6)

### 1.1 Le Probleme
- **Surcharge et obsolescence logicielle (Bloatware)** : Android et iOS pesent de 15 a 25 Go sur le stockage, consomment 3 a 4 Go de RAM a vide, et ralentissent deliberement les smartphones apres 3 ans.
- **Pollution cognitive et pistage** : 65 a 100 notifications par jour, applications partenaires imposees impossibles a desinstaller, telemetrie publicitaire continue consommant jusqu'a 20% de batterie.
- **Complexite des ROMs alternatives** : Installer LineageOS ou postmarketOS requiert des lignes de commande fastboot/ADB complexes et rebute 99% des utilisateurs.

### 1.2 La Solution : L'Ecosysteme OSauce
1. **OS Client (OSauce Shell)** : Rust + Slint sur noyau Linux (postmarketOS). Moins de 150 Mo de RAM, zero bloatware, 60/120 FPS materiel sur GPU, compatibilite Android isolee a la demande via conteneur Waydroid.
2. **Backend & BDD Relationnelle (OSauce Hub API)** : API REST connectee a une base relationnelle PostgreSQL (MCD/MPD rigoureux) et cache Redis pour valider les competences CP 7 et CP 8 du titre CDA :
   - Gestion des paquets et applications verifiees sans trackers (checksums SHA-256).
   - Gestion des utilisateurs et synchronisation chiffree des reglages/contacts (SauceSync).
3. **WebUSB Installer** : Site web capable de flasher l'OS sur n'importe quel telephone branche en USB via le standard W3C WebUSB / WebADB dans Chrome/Edge, sans aucune ligne de commande.

### 1.3 Couverture des 11 Competences CDA (RNCP 37873)
- **CP 1 (Environnement)** : Toolchain Rust, Cargo, Linux WSL2, Docker noVNC, Git.
- **CP 2 (UI)** : Interface mobile tactile Slint 390x844, tokens visuels, zero tracking RGPD.
- **CP 3 (Metier)** : Rust memory-safe, architecture defensive, zero buffer overflow (recommandation ANSSI).
- **CP 4 (Gestion de projet)** : Agile/Scrum, B-Board Kanban, branches Git KISS.
- **CP 5 (Besoins & Maquettage)** : Cahier des charges, user stories, maquettage Figma/Slint.
- **CP 6 (Architecture logicielle)** : Presentation (Slint) / Runtime (Rust) / Systeme (Linux sysfs). Ecoconception.
- **CP 7 (Base de donnees relationnelle)** : Conception MCD/MPD PostgreSQL du catalogue d'apps et SauceSync.
- **CP 8 (Acces donnees SQL & NoSQL)** : Requetes preparees securisees, transactions ACID, cache NoSQL.
- **CP 9 (Tests)** : Tests unitaires Rust (`cargo test`), tests d'integration UI et telemetrie.
- **CP 10 (Deploiement)** : Images systeme, flash WebUSB documente pas a pas.
- **CP 11 (DevOps)** : CI/CD GitHub Actions (build Wasm, validation continue), conteneurisation Docker.

---

## 2. Analyse de Marche Approfondie (Chiffres, Dates, Sources)

Le projet s'inscrit a la convergence de trois dynamiques de marche majeures :

### 2.1 Marche du Smartphone Neuf vs Reconditionne

#### Marche du Neuf en Stagnation Structurelle
- **Tendance** : Saturation du marche mondial du smartphone neuf (IDC, 2024-2025). Le cycle moyen de renouvellement d'un smartphone est passe de 24 mois a **plus de 36 a 42 mois** en Europe (Source : Arcep / ADEME, 2024).
- **Cause** : Manque d'innovations de rupture, flambee des prix des modeles haut de gamme (> 1 000 euros) et inflation.

#### Explosion du Marche Reconditionne
- **Croissance en France** : En 2025, le marche francais du reconditionne a atteint **4,2 millions d'appareils vendus**, en hausse de **+23%** sur un an (Source : Recommerce / Kantar, 2025).
- **Poids economique** : Le marche francais represente **1,2 milliard d'euros** en 2025. **1 smartphone vendu sur 5** (22% du parc actif) est desormais un smartphone reconditionne.
- **Perspectives mondiales** : Les expeditions mondiales de smartphones de seconde main et reconditionnes atteindront **431 millions d'unites d'ici 2027**, avec un taux de croissance annuel moyen (CAGR) de **+8,8%** sur 2022-2027 (Source : IDC Worldwide Used Smartphone Forecast).
- **Impact ecologique (Source : ADEME, rapport evaluation d'impact du reconditionne)** :
  - Un smartphone reconditionne permet d'eviter **77% a 91%** d'impact environnemental annuel par rapport au neuf.
  - **-87% d'emissions de gaz a effet de serre**.
  - **82 kg de matieres premieres vierges** epargnees par appareil.

### 2.2 Tendance "Digital Detox", Minimalisme & Rejet de l'Hyper-Connexion
- **Croissance des "Dumbphones" chez les jeunes** : Les ventes de telephones minimalistes ou deconnexion ("dumbphones" / feature phones) aupres de la tranche 18-24 ans (Gen Z) ont progresse de **+148% entre 2021 et 2024** aux Etats-Unis et en Europe (Source : Briefs / Counterpoint Research, 2024).
- **Projection de marche** : Le segment mondial des telephones minimalistes et deconnexion est estime a **52,4 milliards de dollars en 2025** et projete a **78,9 milliards d'ici 2034** (Source : Shelftrend Research).
- **La fatigue des ecrans** : Le temps d'ecran moyen quotidien sur smartphone atteint **4h37 par jour** en France (Source : Data.ai / Arcep, 2024), dont 70% passe sur des flux de reseaux sociaux algorithmiques. Le besoin d'outils epures, sans notifications toxiques, repond a une attente sociologique de sante mentale averee.

### 2.3 Alignement Reglementaire Europeen (2024-2026)
- **Digital Markets Act (DMA - Mars 2024)** : L'Union Europeenne force Apple et Google a ouvrir leurs ecosystemes, a autoriser le sideloading et les magasins d'applications alternatifs, cassant le duopole historique.
- **Directive Ecoconception et Droit a la Reparabilite (2025-2026)** : Obligation pour les constructeurs de maintenir la fourniture de pieces detachees et les mises a jour logicielles pendant au moins 5 a 7 ans, rendant viable la revalorisation de materiels anciens avec un OS leger.

---

## 3. Positionnement Concurrentiel

| Critere | Android Stock (Google) | Dumbphones (Light Phone / Nokia) | Custom ROMs (LineageOS / GrapheneOS) | OSauce (Notre Solution) |
| :--- | :--- | :--- | :--- | :--- |
| **Poids OS & RAM** | 15 a 25 Go / 3-4 Go RAM | < 50 Mo / 512 Mo RAM | 8 a 12 Go / 2 Go RAM | **< 1 Go / < 150 Mo RAM** |
| **Rendu & Fluidite** | Java/Kotlin (lourd) | E-ink ou ecran basique | Java/AOSP (lourd) | **Rust + Slint natif GPU (60/120 FPS)** |
| **Design & Personnalisation** | Publicitaire / Surcharge | Rigide / Monochrome | Standard Android | **Minimaliste, typographique, OLED pur** |
| **Compatibilite Apps** | Totale | Nulle a quasi-nulle | Totale | **Hybride (Natif pur + Waydroid a la demande)** |
| **Facilite d'installation** | Preinstalle | Preinstalle | Complexe (lignes de commande) | **WebUSB en 1 clic dans le navigateur** |
| **Respect Vie Privee** | Faible (pistage continu) | Bon | Tres bon | **Absolu (Zero tracking, 100% local)** |

---

## 4. Strategie pour "Faire Marcher le Projet" (Go-to-Market & Viabilite)

L'echec classique des OS alternatifs reside dans la barriere a l'entree technique et l'absence de modele economique. Voici le plan d'action operationnel :

### 4.1 Supprimer la Barriere a l'Entree : Le WebUSB Installer
- Zero telechargement d'outils SDK ou de drivers manuels.
- L'utilisateur branche son telephone en USB, ouvre `osauce.io/install` sur Chrome/Edge, et clique sur "Detecter mon telephone".
- Le site communique directement avec le bootloader via l'API WebUSB (`navigator.usb`), telecharge l'image signee et flash l'appareil en 3 minutes de maniere entierement automatisee.

### 4.2 Deux Canaux de Distribution Viables
1. **Canal Logiciel Libre (Do It Yourself - Gratuit)** :
   - Ciblant les developpeurs, etudiants, et enthousiastes open source.
   - Installation gratuite via WebUSB sur appareils supportes (Google Pixel 3a a 7, Fairphone 4/5, PinePhone, puces Snapdragon supportees par postmarketOS).
   - Communaute Discord pour le support, l'entraide et les suggestions de fonctionnalites.
2. **Canal Clef-en-Main Reconditionne (B2C Monétisé)** :
   - Partenariat avec des ateliers de reconditionnement locaux ou vente directe : smartphones de seconde main (type Pixel reconditionne a 100 euros) nettoyes et reconditionnes avec OSauce preinstalle.
   - Vente a prix abordable (150 a 190 euros) comme "Smartphone Deconnexion & Efficacite", garanti 2 ans.

### 4.3 Modèle Économique (Même avec un coeur Open Source)
- **SauceSync Cloud (Freemium)** :
  - Gratuit : Synchronisation locale via Wi-Fi / cable ou auto-hebergement.
  - Option payante (2 euros / mois) : Sauvegarde chiffree de bout en bout des reglages, notes et contacts sur serveurs souverains heberges en France/Europe.
- **Marge materielle reconditionnee** : Marge brute de 40 a 60 euros par smartphone reconditionne vendu clef-en-main.
- **Support & Customisation B2B** : Adaptation d'OSauce pour des flottes d'entreprises souhaitant des terminaux stricts, sans distraction ni fuite de donnees pour leurs collaborateurs sur le terrain.

---

## 5. Site Web, FAQ et Support Communautaire

### 5.1 Architecture du Site Web (`osauce.io`)
- **Page d'Accueil** :
  - Titre : "Votre smartphone delivre du superflu. Rapide. Sobre. Durable."
  - Demonstrateur interactif WebAssembly integre directement dans la page.
  - Comparatif chiffré en temps reel (Autonomie, RAM, Empreinte disque).
- **Installateur Web** : Assistant 3 etapes WebUSB.
- **FAQ Detaillee** :
  - *Est-ce que je peux utiliser mes applications indispensables (banque, transport, messagerie) ?* Oui. OSauce embarque le conteneur sandbox Waydroid qui execute les applications Android necessaires de maniere isolee, sans leur permettre d'espionner le reste du telephone.
  - *Quels appareils sont compatibles ?* Tous les appareils supportes par le noyau postmarketOS avec pilote d'affichage Wayland (liste dynamique filtree sur le site).
  - *Puis-je revenir en arriere ?* Oui, l'installateur WebUSB propose un bouton de restauration vers la ROM constructeur d'origine en 1 clic.
- **Support & Communaute** :
  - Serveur Discord officiel structure : `#annonces`, `#questions-installation`, `#developpement-rust`, `#suggestions-design`.
  - Documentation technique et code source heberges sur GitHub avec tableau de bord Kanban public.

---

## 6. Cadrage ODD (Objectifs de Developpement Durable - ONU)

- OSauce : Le projet traite de la reduction de l'empreinte ecologique numerique et du bien-etre mental. Les deux ODD directeurs sont :
  - **ODD 12 (Principal) : Consommation et production responsables** (lutte contre l'obsolescence programmee logicielle, allongement de la duree de vie des composants, reemploi de materiel existant).
  - **ODD 3 (Secondaire) : Bonne sante et bien-etre** (reduction de la surcharge cognitive, lutte contre le doomscrolling et les flux d'attention predateurs).
  - **ODD 9 (Support) : Industrie, innovation et infrastructure** (souverainete logicielle europeenne, developpement en code ouvert et auditabilite).

### 6.2 Analyse Multi-Capital et Budgets Restants
- **Capital Naturel & Budgets Restants** : Un smartphone concentre environ 82 kg de matieres premieres vierges (cuivre, lithium, cobalt, terres rares). En prolongeant le cycle de vie de smartphones reconditionnes via un OS ultra-leger en Rust, OSauce preserve directement les budgets ressources epuisables de la planete.
- **Capital Humain** : Protection du temps d'attention (reduction des 65 a 100 notifications invasives par jour) et sante mentale des utilisateurs.
- **Capital Economique** : Reduction drastique de la facture materielle (terminal reconditionne durable a 150 euros vs smartphone neuf a 1 000 euros).

---

## 7. Matrice de Marche Multi-Echelle (MTA, MAS, MARS, PAP, Sous-PAP)

Ce decoupage formalise la taille du marche selon le gabarit officiel de l'ecole :

| Acronyme FR | Terme en Francais | Definition du Perimetre OSauce | Faits & Chiffres Cles (< 2 ans, Sources & Liens) |
| :--- | :--- | :--- | :--- |
| **MTA** | **Marche Total Accessible** *(TAM)* | **Monde** : Parc global d'utilisateurs de smartphones et volume mondial des appareils de seconde main / reconditionnes. | - **4,3 milliards** d'utilisateurs de smartphones en 2024-2025 ([Source : Statista Smartphone Users](https://www.statista.com/statistics/330695/number-of-smartphone-users-worldwide/)).<br/>- **431 millions d'unites** de smartphones d'occasion/reconditionnes projetes en 2027 a l'echelle mondiale, soit une croissance annuelle moyenne de +8,8% ([Source : IDC Worldwide Used Smartphone Forecast 2023-2027](https://www.idc.com/getdoc.jsp?containerId=prUS51761624)). |
| **MAS** | **Marche Accessible Servi** *(SAM)* | **Europe (UE)** : Utilisateurs europeens de smartphones sous le cadre reglementaire favorable (DMA, indice de durabilite UE 2025). | - **320 millions** d'utilisateurs de smartphones au sein de l'Union Europeenne ([Source : Eurostat Digital Economy 2024](https://ec.europa.eu/eurostat)).<br/>- Marche europeen du reconditionne evalué a environ **25 millions d'appareils par an**.<br/>- Application effective du DMA en mars 2024 brisant les monopoles de distribution fermee. |
| **MARS** | **Marche Accessible Reellement Servi** *(SOM)* | **France** : Volume annuel de smartphones reconditionnes vendus et proportion de la population sensible a la sobriete numerique. | - **4,2 millions** de smartphones reconditionnes vendus en France, pesant **1,2 milliard d'euros** (soit 22% des ventes mobiles nationales, hausse de +23% en volume) ([Source : Barometre Recommerce / Kantar 2024-2025](https://www.recommerce.com/)).<br/>- L'achat reconditionne evite de **64% a 87% d'impact environnemental** et **82 kg de matieres** ([Source : Rapport officiel ADEME 2022-2024](https://presse.ademe.fr/2022/01/etude-ademe-evaluation-de-limpact-environnemental-des-smartphones-reconditionnes.html)).<br/>- **37%** des Francais souhaitent activement reduire leur temps d'ecran ([Source : Barometre du Numerique Arcep / Credoc 2024](https://www.arcep.fr/)). |
| **PAP** | **Pionniers / Adopteurs Precoces** | **Cible Prioritaire** : Etudiants tech, developpeurs de l'ecosysteme FOSS / Linux mobile, et jeunes adultes (18-25 ans) cherchant une alternative aux OS publicitaires sur appareils supportes (Google Pixel, Fairphone). | - Environ **180 000 etudiants et professionnels** de la filiere informatique en France.<br/>- Engouement prouve pour la deconnexion (+148% de progression relative des ventes d'appareils simples chez les 18-24 ans selon Counterpoint Research), mais freine par le manque d'applications essentielles sur les vieux feature phones.<br/>- Base materielle de plus de **50 000 smartphones** Google Pixel / Fairphone compatibles en circulation chez cette cible. |
| **Sous-PAP** | **Zone Test** | **Zone pilote locale** : Le campus etudiants (Coding Factory / campus partenaire) et le serveur Discord de beta-testeurs techniques. | - **200 a 500 etudiants et developpeurs** mobilisables pour tester l'installateur WebUSB en 1 clic et remonter les metriques d'ergonomie et de stabilite du simulateur. |

---

## 8. Nuance Critique sur la Tendance "Dumbphones" vs la Realite de Marche

Il est primordial d'apporter au jury un regard analytique critique sur les chiffres des dumbphones :

1. **La realite du chiffre de +148%** :
   - Ce bond spectaculaire (+148% de ventes chez les 18-24 ans documente par Counterpoint Research entre 2021 et 2024) reflete une **hausse relative spectaculaire**, mais sur une **niche tres reduite**.
   - En volume reel, les dumbphones ne representent que **2% a 3%** des ventes totales de telephones en Occident.
2. **Le paradoxe du dumbphone et le positionnement d'OSauce** :
   - Pourquoi 98% des jeunes n'abandonnent-ils pas leur smartphone malgre le desir de deconnexion ? Parce qu'un dumbphone a touches classique rend impossible l'acces a des services du quotidien devenus obligatoires : validation bancaire 3D Secure, billets de train, WhatsApp pour les cours/famille, GPS d'orientation.
   - **La reponse de valeur d'OSauce** : Au lieu d'imposer un retour arriere technologique punitif, OSauce propose un smartphone visuellement sobre, fluide et sans sollicitation publicitaire continue, avec le support sandbox isole (Waydroid) pour les deux ou trois applications indispensables du quotidien.

