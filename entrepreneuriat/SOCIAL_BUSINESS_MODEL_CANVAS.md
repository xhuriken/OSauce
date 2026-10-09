# Social Business Model Canvas (SBMC) - Projet OSauce

Ce document formalise l'integralite du Social Business Model Canvas (Planche 3 du dossier d'entrepreneuriat et d'impact), structure selon le template officiel en 9 blocs strategiques d'impact societal et environnemental (aligne sur les ODD 12, 3, 9 et 10).

---

## 1. Vue d'Ensemble Synthetique (Schema Officiel Planche 3)

```
+-----------------------------------------------------------------------------------------------------------------------------------------------+
| DATE: Octobre 2026   | PROGRAMME: Titre CDA / Entrepreneuriat   | SPECIALITE: Ingenierie Logicielle, Sobriete Numerique & Open Source         |
+-----------------------------------------------------------------------------------------------------------------------------------------------+
| COMMUNITIES           | GOVERNANCE            | SOCIAL VALUE            | SOCIETAL CULTURE      | USER                                        |
|                       |                       |                         |                       |                                             |
| - Communaute FOSS     | - Modele 100% libre   | - Emancipation          | - Denormalisation     | - Etudiants & Gen Z (18-25)                 |
|   (Rust, postmarketOS,|   (licences MIT /     |   attentionnelle        |   de l'hyper-         |   satures de l'addiction                    |
|   Slint, Alpine)      |   Apache 2.0 / GPL)   |   (anti-distraction,    |   connexion et du     |   aux ecrans & du FOMO                      |
| - Collectifs anti-    | - Gouvernance         |   zero dark pattern)    |   scrolling infini    | - Foyers a budget contraint                 |
|   obsolescence (HOP,  |   participative       | - Justice sociale &     | - Culture du soin et  |   bloques par les lenteurs                  |
|   Framasoft, Repair   |   (RFC publiques,     |   accessibilite         |   de la reparabilite  |   artificielles d'Android                   |
|   Cafes, Low-Tech Lab)|   votes usagers)      |   economique (OS fluide |   (Care) contre la    | - Professionnels &                          |
| - Etudiants tech &    | - Charte ethique :    |   sur smartphone de     |   societe du jetable  |   travailleurs du savoir en                 |
|   assos ecologiques   |   zero vente de       |   6 ans a 0 euro)       | - Rehabilitation du   |   quete de charge cognitive                 |
| - Beta-testeurs &     |   donnees, zero pub   | - Allongement de la     |   smartphone comme    |   reduite et de serenite                    |
|   membres Discord     +-----------------------+   vie materielle de     |   simple outil au     | - Citoyens engages pour                     |
| - Acteurs du          | EMPLOYEES             |   +3 ans (ODD 12)       |   service de l'humain |   la vie privee et la                       |
|   reconditionnement   |                       | - Souverainete des      | - Communs numeriques  |   souverainete numerique                    |
|   solidaire (Emmaus   | - Droit absolu a la   |   donnees & respect de  |   face aux monopoles  | - Ecoles et flottes pro                     |
|   Connect, Envie,     |   deconnexion et      |   la vie privee         |   GAFAM               |   recherchant un parc sobre                 |
|   YesYes solidaire)   |   culture asynchrone  | - Pragmatisme Waydroid  +-----------------------+   et exempt de distraction                  |
|                       | - Valorisation juste  |   (acces preserve aux   | IMPACT STRATEGY       |                                             |
|                       |   et remuneration des |   services vitaux       |                       |                                             |
|                       |   mainteneurs open src|   sans regression)      | - Installation WebUSB |                                             |
|                       | - Sens et impact      |                         |   en 1 clic sans      |                                             |
|                       |   ecologique direct   |                         |   barriere technique  |                                             |
|                       | - Outils internes     |                         | - Partenariats ESS &  |                                             |
|                       |   libres et souverains|                         |   reconditionneurs    |                                             |
|                       |                       |                         | - Mesure d'impact KPI |                                             |
+-----------------------------------------------+-------------------------+-----------------------+---------------------------------------------+
| SOCIAL IMPACTS (-) (Risques & Mitigations)                              | SOCIAL IMPACTS (+) (Benefices Societaux & Ecologiques)              |
|                                                                         |                                                                     |
| - Barriere technique initiale au flashage                               | - Sante mentale & recuperation cognitive                            |
|   Mitigation: WebUSB Flasher 1 clic automatise & guide illustre         |   Gain de 1h30/jour d'attention; chute de 80 a 15 notifications/j   |
| - Absence des Google Play Services officiels                            | - Gain de pouvoir d'achat & justice sociale                         |
|   Mitigation: microG integre + sandbox Waydroid pour apps bancaires     |   Economie directe de 300 a 800 euros en repoussant l'achat de 3 ans|
| - Risque d'epuisement de l'equipe de support benevole                   | - Impact carbone & preservation de la Terre (ODD 12)                |
|   Mitigation: perimetre de materiels officiels cibles & docs FAQ        |   82 kg de matieres premieres brutes et 50 a 70 kg CO2 evites par app|
| - Sentiment transitoire de privation digitale                           | - Souverainete citoyenne & liberation des donnees                   |
|   Mitigation: ergonomie zen et valorisante ("flux des Signaux")         |   Zero traqueur publicitaire, ecosysteme europeen independant       |
+-------------------------------------------------------------------------+---------------------------------------------------------------------+
```

---

## 2. Contenu Detaille Bloc par Bloc (Pour Restitution & Soutenance)

### Bloc 1 : Communities (Communautes)
*Quelles sont les communautes actrices, partenaires et beneficiaires gravitant autour du projet ?*

1. **Communaute Open Source et Linux Mobile** :
   - Developpeurs de l'ecosysteme Rust, Slint UI, postmarketOS, Alpine Linux et Waydroid.
   - Partage mutuel des correctifs de pilotes graphiques et noyaux mobiles reversibles en amont (*upstream*).
2. **Collectifs de sobriete numerique et anti-obsolescence** :
   - Associations citoyennes de defense du consommateur : HOP (Halte a l'Obsolescence Programmee), Framasoft, Repair Cafes et Low-Tech Lab.
   - Co-organisation d'ateliers de revalorisation de vieux smartphones lors de journees citoyennes ou universitaires.
3. **Communautes etudiantes et jeunesse engagee** :
   - Etudiants en informatique, ingenieurie et assos ecologiques mobilises contre la crise de l'attention et le gachis d'equipements.
   - Ambassadeurs locaux sur les campus pour tester et promouvoir le systeme.
4. **Communaute active de beta-testeurs OSauce** :
   - Utilisateurs pionniers sur le Discord du projet et sur GitHub testant les nouvelles versions de l'interface et remontant les besoins reels.
5. **Reseau du reconditionnement solidaire et de l'ESS** :
   - Ateliers d'insertion professionnelle et acteurs associatifs (Emmaus Connect, Envie, ateliers d'auto-reparation) cherchant a equiper des publics precaires avec du materiel perenne.

---

### Bloc 2 : Governance (Gouvernance)
*Comment les decisions sont-elles prises, sur quelles regles et garanties ethiques repose le projet ?*

1. **Modele Open Source Radical & Transparence** :
   - Code source public sous licences permissives et libres (MIT / Apache 2.0 pour le shell, GPL pour les composants noyau).
   - Audits de securite et de conformite realisables par n'importe quel citoyen ou expert independant.
2. **Gouvernance Participative & Co-construction (Do-ocratie & RFC)** :
   - Les evolutions logicielles majeures font l'objet de RFC (*Request for Comments*) ouvertes au vote de la communaute.
   - Pas de decisionnaire opaque dictant un algorithme commercial secret.
3. **Charte Ethique Inviolable (Statuts Fondateurs)** :
   - Interdiction formelle et statutaire de toute collecte, profilage ou commercialisation de donnees privees.
   - Refus strict du modele d'affaires publicitaire base sur la retention d'attention.
   - Refus de capitaux d'investissement predateurs imposant l'insertion de traqueurs ou de dark patterns.
4. **Comite d'Ethique et de Sobriete Numerique** :
   - Evaluation trimestrielle de la consommation memoire (< 150 Mo RAM) et de l'empreinte energetique du shell pour prevenir toute derive obesenicielle (*bloatware*).
5. **Statut d'Entreprise a Impact / SCIC cible** :
   - Evolution vers une forme cooperative (SCIC - Societe Cooperative d'Interet Collectif) ou entreprise solidaire d'utilite sociale (ESUS) integrant les utilisateurs et les developpeurs au sociétariat.

---

### Bloc 3 : Employees (Equipe & Collaborateurs)
*Quelle est la politique humaine, les conditions de travail et la culture d'equipe ?*

1. **Droit Absolu a la Deconnexion & Rythme Asynchrone** :
   - Application interne stricte des valeurs portees par le produit : interdiction de la culture de l'urgence artificielle, suppression des alertes intrusives le soir et le week-end.
   - Mode de communication asynchrone privilegiant la reflexion de fond plutot que la reactivite immediate.
2. **Juste Remuneration et Soutien des Mainteneurs Libres** :
   - Redistribution d'une part des revenus de services (SauceSync, reconditionneurs) aux contributeurs et developpeurs cles du projet.
   - Prise en charge des equipements de test materiel pour les developpeurs eloignes.
3. **Sens et Alignement des Valeurs Personnelles** :
   - Zero dissonance cognitive pour les developpeurs : travail dedie a desengorger la planete et a soulager les usagers de l'addiction numerique, a l'oppose des missions de captation d'attention des GAFAM.
4. **Inclusion, Mentorat et Transmission Pedagogique** :
   - Accompagnement bienveillant des nouveaux contributeurs juniors ou en reconversion grace a des guides clairs (RUST_GUIDE, SLINT_GUIDE, ARCHITECTURE).
5. **Souverainete et Respect de la Vie Privee au Travail** :
   - Utilisation exclusive d'outils collaboratifs souverains, libres et auto-heberges (Nextcloud, Matrix/Element, Git) sans espionnage salarial.

---

### Bloc 4 : Social Value (Proposition de Valeur Sociale)
*Quelle valeur fondamentale d'utilite publique et humaine le projet cree-t-il ?*

1. **Emancipation Attentionnelle et Sante Mentale** :
   - Rompre avec le modele toxique de la dopamine industrielle (fin des feeds infinis, suppression des pastilles rouges anxiogenes, interface epuree "Respirer").
   - Restitution du temps de concentration, du sommeil et de la paix de l'esprit a l'utilisateur.
2. **Lutte contre l'Obsolescence Logicielle et Materielle (ODD 12)** :
   - Rendre des smartphones ages de 5 a 8 ans plus fluides, reactifs (120 FPS) et autonomes qu'au premier jour grace au moteur natif Rust ultra-leger (< 150 Mo RAM).
   - Augmentation reelle de la duree de detention de +3 ans par appareil.
3. **Justice Sociale et Pouvoir d'Achat (ODD 10)** :
   - Fin de la taxe technologique forcee : un smartphone reconditionne a 80-100 euros installe sous OSauce delivre une experience plus rapide et digne qu'un terminal neuf a 600 euros sature de bloatwares.
4. **Protection Sans Concession des Libertes Civiques** :
   - Protection totale de la vie privee : zero traqueur, zero telemetrie cachee, zero identifiant publicitaire unique.
5. **Pragmatisme face a la Realite Quotidienne** :
   - Contrairement aux *dumbphones* (telephones a touches) qui marginalisent leurs usagers (impossibilite de valider un paiement bancaire 3D Secure, de composter un billet de train ou d'ouvrir un document universitaire), OSauce isole ces obligations dans une sandbox conteneurisee Waydroid etanche, assurant la continuite de vie sans la pollution attentionnelle permanente.

---

### Bloc 5 : Societal Culture (Culture Societale)
*Quelle transformation culturelle et quels changements de mentalites le projet impulse-t-il ?*

1. **Denormalisation de l'Hyper-connexion et de la Toxicite Digitale** :
   - Faire passer le smartphone d'un objet d'addiction compulsive a un outil technique sobre, calme et elegant.
   - Rendre la sobriete numerique attrayante et valorisante pour la jeunesse (mouvement "dopamine diet") plutot que moralisatrice ou punitive.
2. **Culture de la Reparabilite et du Soin Materiel (*Ethique du Care*)** :
   - Revaloriser l'acte de garder son telephone longtemps face au culte pueril de la consommation ostentatoire du dernier modele sorti.
3. **Demystification Technologique et Autonomie Citoyenne** :
   - Reconnecter les utilisateurs avec le fonctionnement reel de leurs outils numeriques via un installateur transparent et du logiciel ouvert.
4. **Promotion des Biens Communs Numeriques** :
   - Renforcer la culture collective des logiciels libres comme patrimoine d'interet general, independant des geants monopolistiques prives.

---

### Bloc 6 : Impact Strategy (Strategie d'Impact)
*Comment l'impact est-il deploie, mesure et massifie sur le terrain ?*

1. **Suppression Radicale de la Barriere Technique (WebUSB Installer)** :
   - Passer d'un processus de flashage complexe reserve aux hackers a une installation en 1 clic direct depuis un navigateur web (Chrome/Brave avec API WebUSB/WebADB).
2. **Canaux de Diffusion Multiplicateurs (Partenariats Ecosystémiques)** :
   - Collaboration avec les reconditionneurs professionnels pour commercialiser des telephones pre-equipes de l'OS avec garantie materielle.
   - Diffusion dans les universites et tiers-lieux citoyens via des journees de revalorisation numerique ("Install Parties" OSauce).
3. **Pilotage par la Mesure d'Impact (Indicateurs Cles & KPI)** :
   - *Indicateur d'extension de vie* : Nombre de smartphones prolonges de 3 ans et plus (cible annee 1 : 2 500 unites; annee 3 : 25 000 unites).
   - *Indicateur environnemental (Source ADEME)* : Masse de matieres premieres evitees (82 kg / smartphone, soit 2 050 tonnes de matieres brutes des la phase 2).
   - *Indicateur d'attention* : Diminution moyenne declaree du temps d'ecran quotidien de 1h30 a 2h00 par utilisateur.
4. **Plaidoyer Public & Normalisation Institutionnelle** :
   - Participation aux debats publics sur l'indice de reparabilite et de durabilite logicielle en collaboration avec l'ARCEP et HOP.

---

### Bloc 7 : User (Utilisateurs & Beneficiaires)
*Qui sont precisement les individus touches et que leur apporte OSauce ?*

1. **Etudiants et Jeunes Adultes (18-25 ans - Cible Principale)** :
   - En recherche active de deconnexion face a l'anxiete, au burn-out academique et a la chute de concentration, mais ayant besoin de leurs applications vitales (banque, transport, messagerie de groupe).
2. **Etudiants et Foyers a Pouvoir d'Achat Limite** :
   - Ne pouvant pas remplacer un terminal rendu inutilisable par la lourdeur des mises a jour d'Android ou d'iOS, et beneficiant d'un systeme qui tourne a 120 FPS sur du materiel modeste.
3. **Travailleurs Intellectuels & Professionnels Satures** :
   - Cadres, developpeurs, chercheurs ou independants epuises par l'infobesite et les sollicitations incessantes, voulant un outil de travail silencieux et efficace.
4. **Citoyens Sensibilises a l'Ecologie et a la Protection des Donnees** :
   - Refusant le pillage de leur vie privee par les geants du web et refusant le cycle d'obsolescence programmee destructeur pour la planete.
5. **Ecoles, Universites et Collectivites Publiques** :
   - Souhaitant equiper des etudiants ou agents de terminaux securises, sobres, sans publicite ni distractions algorithmiques.

---

### Bloc 8 : Social Impacts (-) (Impacts Negatifs & Mesures d'Attenuation)
*Quels risques sociaux, freins ou externalites negatives potentielles, et comment y remedier ?*

| Risque Identifie | Impact Potentiel | Strategie d'Attenuation Concretisee (Mitigation) |
| :--- | :--- | :--- |
| **Peur du flashage et barriere technique** | Reticence des usagers non-techniciens a effacer leur telephone actuel. | - Deploiement du WebUSB Flasher automatique en 1 clic avec sauvegarde prealable et verification du modele.<br>- Option cle en main d'achat direct de smartphones reconditionnes avec OSauce deja installe. |
| **Incompatibilite de certaines applications bancaires ou anti-triche** | Blocage potentiel de services prives stricts verifiant SafetyNet / Play Integrity. | - Integration native de l'environnement de compatibilite microG dans la sandbox Waydroid.<br>- Documentation transparente et catalogue d'applications certifiees compatibles sur SauceHub. |
| **Sentiment d'isolement ou sevrage dopaminergique brutal** | Frustration ou abandon les premiers jours en cas de manque de stimulation algorithmique. | - Ergonomie positive et apaisante : ecran d'accueil zen, gestion bienveillante des "Signaux" et liens humains choisis.<br>- Pas de blocage moralisateur : l'usager reste maitre de ses choix et peut lancer ses applications en cas de besoin. |
| **Risque d'epuisement de l'equipe et des benevoles (Burnout FOSS)** | Demande de support ecrasante face a la diversite des milliers de modeles de smartphones. | - Focalisation stricte sur une gamme maitrisee de processeurs et modeles de reference (Google Pixel, Fairphone, Samsung populaires).<br>- Base de connaissances collaborative (Wiki, FAQ, entraide communaute Discord). |

---

### Bloc 9 : Social Impacts (+) (Impacts Positifs & Externalites Vertueuses)
*Quels sont les gains humains, societaux et planetaires reels et demontrables ?*

1. **Sante Mentale et Reconstruction de l'Attention (ODD 3)** :
   - Baisse massive du nombre d'interruptions cognitives : passage de 80 notifications invasives par jour a moins de 15 signaux pertinents.
   - Recuperation d'une moyenne de 1h30 a 2h00 d'attention par jour, reinvestie dans les etudes, le sommeil, la creativite et les liens interpersonnels reels.
2. **Impact Ecologique Massif & Economie Circulaire (ODD 12)** :
   - Prolongation prouvee de 3 ans de la duree de vie d'un smartphone.
   - Chaque telephone preserve evite l'extraction de **82 kg de matieres premieres brutes** (minerais rares, lithium, cobalt, or) et evite la production de **50 a 70 kg d'emissions de gaz a effet de serre** (etude ADEME / Arcep).
3. **Justice Sociale et Pouvoir d'Achat Retrouve (ODD 10)** :
   - Economie brute de **300 a 800 euros** pour l'usager par rapport a l'achat force d'un nouveau terminal.
   - Democratisation d'une experience mobile de premier ordre (120 FPS, reactivite immediate) sur du materiel d'occasion accessible a tous.
4. **Emancipation Technologique et Souverainete Civique (ODD 9)** :
   - Rupture totale avec la dependance envers les monopoles extra-europeens des GAFAM.
   - Reappropriation de la souverainete des donnees individuelles et renforcement du patrimoine logiciel libre et independant.

---

## 3. Guide de Recopie Minute pour l'Affiche / Slide (Format Cartouches)

Pour remplir directement les cases de la feuille d'examen ou la diapositive Canva/PowerPoint numero 3 :

- **COMMUNITIES** :
  * Communaute FOSS (Rust, postmarketOS, Slint UI, Alpine, Waydroid).
  * Associations anti-obsolescence & low-tech (HOP, Framasoft, Repair Cafes).
  * Collectifs etudiants tech & ecologiques (campus ambassadeurs).
  * Reseau des reconditionneurs et de l'insertion solidaire (Emmaus Connect, YesYes).
  * Communaute beta-testeurs & utilisateurs Discord.

- **GOVERNANCE** :
  * 100% Open Source (licences MIT, Apache 2.0, GPL).
  * Prise de decision collegiale (RFC publiques et consultations ouvertes).
  * Charte ethique inalterable : zero pub, zero revente de donnees, zero dark patterns.
  * Comite de sobriete : controle continu de l'empreinte memoire (< 150 Mo RAM).
  * Cible societaire : transformation a terme en SCIC ou entreprise ESUS.

- **EMPLOYEES** :
  * Droit reel a la deconnexion et mode de travail asynchrone respectueux du cerveau.
  * Juste retribution et mecenat direct pour les developpeurs du libre.
  * Fierte d'impact direct : travail aligne avec l'urgence ecologique et sociale.
  * Culture de mentorat technique bienveillant et ouverture a la diversite.
  * Utilisation interne exclusive de briques logicielles libres et souveraines.

- **SOCIAL VALUE** :
  * Liberation attentionnelle et reduction de l'anxiete numerique (anti-addiction).
  * Anti-obsolescence programmee : prolongation de vie du smartphone de +3 ans (ODD 12).
  * Justice economique : OS ultra-fluide (120 FPS) gratuit sur appareil ancien.
  * Respect inconditionnel de la vie privee (zero profilage publicitaire).
  * Pragmatisme Waydroid : acces securise et etanche aux applications obligatoires.

- **SOCIETAL CULTURE** :
  * Dénormalisation de l'hyperconnexion et des interfaces toxiques dopaminergiques.
  * Revalorisation de la reparation et de la sobriete materielle (Ethique du Care).
  * Le smartphone redevient un simple outil utile, silencieux et maitrise.
  * Diffusion de la culture des communs numeriques comme bien collectif.

- **IMPACT STRATEGY** :
  * WebUSB Installer : installation universelle en 1 clic sans competence requise.
  * Partenariats reconditionnement pour distribuer des modeles pre-installes.
  * Indicateurs d'impact suivis : annees de vie prolongees, kg de matieres evitees, temps d'ecran reduit.
  * Plaidoyer aupres des regulateurs et collectifs de durabilite (ARCEP, HOP).

- **USER** :
  * Etudiants et Gen Z cherchant a sortir de la spirale infernale du temps d'ecran.
  * Personnes aux revenus modestes contraintes par le vieillissement logiciel de leur materiel.
  * Professionnels saturés par les alertes demandant un terminal reposant.
  * Citoyens militants pour l'environnement et l'autonomie numerique.
  * Ecoles et structures educatives souhaitant des outils sans distraction.

- **SOCIAL IMPACTS (-)** :
  * Barriere psychologique du flashage -> Leve par WebUSB automatique en 1 clic.
  * Blocage de certaines applications fermees -> Solutionne par microG & Waydroid.
  * Risque d'epuisement de l'equipe benevole -> Cadre par un parc de modeles cibles et FAQ.
  * Frustration de sevrage initial -> Compense par une ergonomie apaisante et bienveillante.

- **SOCIAL IMPACTS (+)** :
  * Gain de 1h30 a 2h00 d'attention/jour et baisse des alertes de 80 a 15/jour (Sante).
  * 82 kg de matieres premieres et 50 a 70 kg CO2 epargnes par smartphone (Planete).
  * Economie directe de 300 a 800 euros en repoussant l'achat de 3 ans (Pouvoir d'achat).
  * Souverainete et reprise de controle face aux monopoles technologiques (Liberte).
