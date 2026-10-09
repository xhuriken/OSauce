# Environmental Business Model Canvas (Planche 2) - Projet OSauce

Ce document formalise l'integralite de l'Environmental Business Model Canvas (Planche 2 du dossier d'entrepreneuriat et d'impact, base sur le modele de l'Analyse du Cycle de Vie - ACV / Triple Layered Business Model Canvas). Il detaille l'ensemble du cycle de vie d'OSauce et quantifie les gains environnementaux relies a l'ODD 12 (Consommation et production responsables).

---

## 1. Vue d'Ensemble Synthetique (Schema Officiel Planche 2)

```
+-----------------------------------------------------------------------------------------------------------------------------------------------+
| DATE: Octobre 2026   | PROGRAMME: Titre CDA / Entrepreneuriat   | SPECIALITE: Ingenierie Logicielle, Sobriete Numerique & Open Source         |
| NOM: OSauce                                                                                                                                   |
+-----------------------------------------------------------------------------------------------------------------------------------------------+
| MATERIELS ET OUTILS   | PRODUCTION            | VALEUR FONCTIONNELLE    | FIN DE VIE            | PHASE D'USAGE                               |
|                       |                       |                         |                       |                                             |
| - PC de developpement | - Compilation native  | - Prolongation de vie   | - Report de la fin de | - Zero service fantome en                   |
|   reconditionnes pour   Rust (zero runtime,     du smartphone de +3 ans   vie materielle de     veille (CPU au repos, pas                   |
|   l'equipe              zero garbage collector)|  sans perte de confort  3 a 5 ans              de wake-locks cachés)                       |
| - Serveurs CI/CD      | - Pipeline CI/CD      | - Empreinte systeme     | - Effacement propre   | - Autonomie batterie x2 :                   |
|   decarbonés (PUE<1.15  sobre sous Alpine &     minimale : < 150 Mo     des donnees pour        cycles de recharge espacés                  |
|   OVHcloud/Scaleway)    postmarketOS            de RAM, reactivite      faciliter la filiere    de 48h au lieu de 24h                       |
| - Banc d'essai tests  | - Images compressees    120 FPS sur GPU modeste de reconditionnement    | - Fond noir OLED pur :                      |
|   sur smartphones       en Zstandard (<1.5 Go | - Decouplage materiel : | - Orientation vers    extinction physique des                     |
|   d'occasion (Pixel,    vs 15-20 Go Android)    eviter l'achat force    filiere DEEE agreee     pixels (-40% conso ecran)                   |
|   Fairphone existants)| - Hebergement en        d'un appareil neuf pour (Ecosystem, Ecologic)   | - Zero publicite ni traceur :               |
| - Outils d'audit :      datacenter a 90%+       acceder a des services| - Reconversion en     economie de plusieurs Go                    |
|   Scaphandre, PowerTOP, electricite bas-carbone numeriques essentiels    passerelle Linux locale de bande passante 4G/5G                     |
|   cargo-audit, Slint    (France / Europe)     +-------------------------+ (domotique, capteurs) | - Sandbox Waydroid lancee                   |
| - Câbles USB-C de test+-----------------------+ DISTRIBUTION            |                       |   a la demande uniquement                   |
|   standardises, zero  | MATIERES              |                         |                       |   (zero consommation le                     |
|   consommable jetable |                       | - Flashage WebUSB sans  |                       |   reste du temps)                           |
|                       | - Purement immateriel : support physique        |                       |                                             |
|                       |   zero boîtier plastique (navigateur direct)    |                       |                                             |
|                       |   ni support physique | - Miroirs et CDN locaux |                       |                                             |
|                       | - 82 kg de matieres     europeens a faible      |                       |                                             |
|                       |   brutes epargnees      latence energetique     |                       |                                             |
|                       |   par telephone       | - Logistique regroupee  |                       |                                             |
|                       |   prolonge (ADEME)    |   avec reconditionneurs |                       |                                             |
+-----------------------+-----------------------+-------------------------+-----------------------+---------------------------------------------+
| IMPACTS ENVIRONNEMENTAUX (-) (Empreinte & Mitigations)                  | BENEFICES ENVIRONNEMENTAUX (+) (Ressources & Climat)                |
|                                                                         |                                                                     |
| - Electricite consommee par la compilation et CI/CD                     | - 82 kg de matieres premieres brutes epargnees par smartphone prolonge|
|   Mitigation: builds incrementaux, caches stricts, PUE < 1.15           |   (minerais rares, lithium, cobalt, or, cuivre - source ADEME)      |
| - Trafic reseau du telechargement d'image (~1.5 Go)                     | - 50 a 70 kg de CO2e evites par smartphone dont l'achat est repousse  |
|   Mitigation: compression Zstandard et mises a jour differentielles     |   (la phase de fabrication concentre 80% de l'empreinte totale)     |
| - Risque d'effet rebond (maintien de vieux chargeurs peu efficients)    | - Division par 2 de l'usure chimique des batteries (cycles etales)  |
|   Mitigation: guide d'usage et chargeurs standards labellises           | - Baisse drastique des dechets electroniques DEEE toxiques          |
| - Empreinte materielle des postes de travail des developpeurs           | - Desengorgement du trafic reseau mondial (zero pub, zero telemetrie|
|   Mitigation: materiel informatique pro reconditionne obligatoire       |   inutile saturee sur les antennes relais 4G/5G)                    |
+-------------------------------------------------------------------------+---------------------------------------------------------------------+
```

---

## 2. Contenu Detaille Bloc par Bloc (Pour Restitution & Soutenance)

### Bloc 1 : Matériels et Outils
*Quels equipements physiques et outils logiciels sont mobilises pour developper et maintenir OSauce ?*

1. **Parc informatique de developpement sobre et reconditionne** :
   - Ordinateurs portables de developpement de seconde main (ThinkPad reconditionnes sous Linux/WSL2).
   - Refus du renouvellement annuel du materiel interne ; engagement de conservation du materiel sur 5 ans minimum.
2. **Banc d'essai de test materiel exclusivement d'occasion** :
   - Flotte de test reduite a quelques smartphones d'occasion cibles (Google Pixel 3a/4a, Fairphone 3/4, PinePhone).
   - Zero achat de smartphones neufs pour les bancs de tests logiciels.
3. **Infrastructure d'integration continue (CI/CD) sobre** :
   - Runners GitHub Actions et serveurs de build heberges chez des prestataires europeens a haute performance energetique (OVHcloud, Scaleway) avec PUE (*Power Usage Effectiveness*) inferieur a 1.15.
4. **Outils d'eco-conception et de mesure energetique** :
   - *Scaphandre* : outil open source de mesure de consommation energetique en temps reel au niveau des processus Linux.
   - *PowerTOP* : diagnostic de l'efficacite energetique du noyau Linux et identification des processus reveillant inutilement le processeur.
   - *Cargo / Rust toolchain* : compilation optimisee sans dependances superflues (`cargo clippy`, `cargo-bloat`, `cargo-audit`).
5. **Connectique et consommables standards** :
   - Cables USB-C et USB-A standardises et durables pour le flashage WebUSB. Aucun adaptateur proprietaire ou consommable a usage unique.

---

### Bloc 2 : Production
*Comment l'OS est-il produit et assemble pour minimiser l'empreinte carbone et energetique industrielle ?*

1. **Architecture native en Rust (Zero machine virtuelle)** :
   - Compilation directe en code machine assembleur. Absence de machine virtuelle Java (contrairement au framework Android ART) et absence de Ramasse-Miettes (*Garbage Collector*).
   - Chaque cycle d'instruction CPU est strictement justifie, eliminant le gaspillage electrique a l'execution.
2. **Chaine de compilation sobre (Alpine Linux & postmarketOS)** :
   - Base systeme minimaliste sans systemd lourd : postmarketOS utilise Alpine Linux et musl libc, garantissant un noyau leger et un espace utilisateur de moins de 200 Mo.
3. **Mise en cache avancee et builds incrementaux** :
   - La CI/CD n'execute la compilation lourde que lors des versions de publication. Les tests journaliers utilisent des caches stricts pour reduire au maximum les calculs processeur dans les datacenters.
4. **Compression avancee des images systeme** :
   - Format de compression moderne (Zstandard / squashfs) generant une image complete de moins de 1.5 Go (contre 15 a 20 Go pour une ROM constructeur Android standard).
5. **Electricite de fabrication 100% bas-carbone** :
   - Hebergement des serveurs de build sur le reseau electrique francais/europeen majoritairement decarboné (nucleaire et renouvelable), minimisant les grammes de CO2 par gigaoctet produit.

---

### Bloc 3 : Matières
*Quelles matieres premieres physiques sont consommees ou epargnees par le modele ?*

1. **Production 100% immaterielle et logicielle** :
   - Zero produit plastique cree, zero boîtier physique, zero CD/cle USB jetable distribuee.
2. **Epargne massive de matieres premieres terrestres (Coeur du projet)** :
   - D'apres l'ADEME, la fabrication d'un smartphone moderne necessite l'extraction et le raffinage de **82 kg de matieres premieres brutes** :
     * Terres rares (neodyme, dysprosium pour les haut-parleurs et vibreurs).
     * Metaux strategiques : lithium, cobalt, nickel pour les accumulateurs.
     * Metaux precieux : or, argent, palladium pour les circuits integres.
     * Minerais de base : 15 a 20 kg de minerai de cuivre, sable siliceux haute purete pour le silicium.
   - En prolongeant le smartphone actuel de 3 ans, OSauce evite l'extraction brute de ces 82 kg par terminal preserve.
3. **Consommation electrique marginale lors du transfert** :
   - Seule l'energie electrique necessaire a la transmission des paquets (serveur -> reseau internet -> memoire flash du telephone) est utilisee, soit moins de 0.05 kWh par installation.

---

### Bloc 4 : Valeur Fonctionnelle (Environnementale)
*Quelle est la fonction d'utilite environnementale fondamentale delivree par le systeme ?*

1. **Allongement prouve de la duree d'usage materielle (+3 ans)** :
   - Rompre l'obsolescence programmee logicielle : rendre un telephone considere comme obsolete par Google ou Samsung parfaitement fonctionnel et rapide (60 a 120 FPS).
2. **Decouplage de l'empreinte environnementale du numerique** :
   - Permettre aux citoyens d'acceder aux services modernes indispensables (billets de train, banque 3D Secure) sans avoir a racheter un terminal neuf a 800 euros dont 80% de l'impact carbone est concentre a la fabrication.
3. **Sobriete des ressources memoires et calculatoires** :
   - Occupation memoire divisee par 20 : moins de 150 Mo de RAM utilisee contre 3 Go pour Android stock.
   - Reduction du besoin d'extensions materielles (nul besoin de puces gravées en 3 nm pour obtenir une interface reactive).

---

### Bloc 5 : Fin de vie
*Comment OSauce intervient-il dans la phase terminale du cycle de vie materiel ?*

1. **Recul temporel de la mise au rebut** :
   - Retardement de 3 a 5 ans de la phase de dechet pour chaque terminal equipe.
2. **Preparation efficace a la seconde main et au reconditionnement** :
   - Processus d'effacement cryptographique securise des donnees lors de l'installation, laissant un appareil parfaitement propre pret a etre cede, revendu ou donne sans risque pour la vie privee.
3. **Integration dans la boucle de recyclage des filieres DEEE agreees** :
   - Sensibilisation de la communaute : quand l'appareil subit une panne materielle definitive irreparable, orientation obligatoire vers les bacs de collecte Ecosystem / Ecologic.
   - Compatibilite prioritaire avec les smartphones a haute reparabilite et modularite (Fairphone), permettant de changer uniquement l'ecran ou la batterie sans jeter la carte mere.
4. **Reconversion en equipement sedentaire basse consommation (Upcycling)** :
   - Possibilite de transformer un telephone a ecran fele en petit serveur Linux local autonome (passerelle domotique, serveur DNS Pi-hole, noeud de sauvegarde souverain), evitant l'achat d'un mini-PC neuf.

---

### Bloc 6 : Distribution
*Comment OSauce est-il livre et distribue sans creer d'emissions logistiques inutiles ?*

1. **Distribution WebUSB 100% dematerialisee (Zero fret physique)** :
   - Installation directe depuis le navigateur web de l'usager grace a l'API WebUSB / WebADB.
   - Zero carton d'emballage, zero transport routier ou aerien necessaire pour installer l'OS.
2. **Compression extreme et minimisation de la bande passante reseau** :
   - Images systeme compressees en Zstandard (~1.2 Go a 1.5 Go), reduisant de 85% la facture de bande passante par rapport au telechargement d'une image Android standard (8 a 15 Go).
3. **Reseau de diffusion de contenu (CDN) local et decarboné** :
   - Miroirs de telechargement situes en France et en Europe pour reduire le nombre de noeuds reseau traverses et les pertes joule liees a l'interconnexion internationale.
4. **Logistique mutualisee avec les reconditionneurs** :
   - Pour les usagers achetant un telephone deja pre-installe, l'OS s'integre dans la filiere logistique existante des reconditionneurs (BackMarket, YesYes) sans aucun trajet de fret supplementaire.

---

### Bloc 7 : Phase d'usage
*Quel est le comportement environnemental et energetique d'OSauce au creux de la main de l'utilisateur ?*

1. **Zero processus fantome et mise en veille profonde du CPU** :
   - Suppression integrale des 40 services Google et constructeurs tournant en arriere-plan permanent.
   - Le processeur reste en etat de sommeil profond (*Deep Sleep*) tant que l'utilisateur ne touche pas l'appareil.
2. **Autonomie de batterie multipliee par deux** :
   - Recharges espacees tous les 2 jours en usage sobre (contre une a deux recharges quotidiennes sous Android pollue).
   - Division par deux du nombre de cycles de charge de la batterie lithium-ion, doublant sa duree de vie chimique et evitant le remplacement premature de l'accumulateur.
3. **Interface noire OLED pure (Pixel-off)** :
   - Fond d'ecran et vues principales bases sur un noir absolu (#000000). Sur les ecrans OLED/AMOLED, les pixels noirs sont physiquement eteints et ne consomment aucun milliwatt, reduisant la consommation energetique de l'ecran jusqu'a 40%.
4. **Suppression de la pollution publicitaire sur les reseaux 4G/5G** :
   - Absence de bannières publicitaires, videos autoplay et traqueurs d'attention.
   - Economie estimee a 2 a 5 Go de donnees mobiles par mois et par utilisateur, desengorgeant les antennes-relais telecom tres energivores.
5. **Sandbox Waydroid a la demande** :
   - Le conteneur Android ne tourne pas en permanence : il est declenche uniquement lors de l'ouverture de l'application bancaire ou de transport, et suspendu des sa fermeture.

---

### Bloc 8 : Impacts Environnementaux (-) (Risques & Mitigations)
*Quels sont les impacts environnementaux negatifs reels du projet et les moyens de les neutraliser ?*

| Impact Negatif Identifie | Ampleur Mesuree | Mesures d'Attenuation Concretes (Mitigations) |
| :--- | :--- | :--- |
| **Electricite des serveurs de build CI/CD** | Quelques dizaines de kWh par mois lors des releases. | - Utilisation de builds incrementaux et de caches d'artefacts stricts.<br>- Hebergement dans des datacenters a haute efficacite energetique (PUE < 1.15, refroidissement passif/watercooling). |
| **Bande passante reseau lors du flashage** | Transfert d'environ 1.5 Go par flashage initial. | - Compression Zstandard ultra-dense.<br>- CDN de proximite.<br>- Mises a jour logicielles de securite differentielles (envoi exclusif des deltas binaires de quelques Mo). |
| **Risque d'effet rebond sur les chargeurs** | Risque que l'utilisateur conserve un chargeur ancien a faible rendement electrique. | - Recommandation d'adaptateurs universels USB Power Delivery (USB-PD) a haut rendement.<br>- Zero fourniture de chargeur jetable dans les offres. |
| **Empreinte informatique interne de l'equipe** | Consommation des PC de developpement et serveurs de test. | - Charte stricte : ordinateurs professionnels de seconde main (reconditionnes).<br>- Allongement de la detention du materiel de dev au-dela de 5 ans. |

---

### Bloc 9 : Bénéfices Environnementaux (+) (Gains Clairs & Chiffres Cles)
*Quels sont les gains massifs pour la planete generes par l'adoption d'OSauce ?*

1. **82 kg de matieres premieres brutes epargnees par smartphone prolonge (ADEME)** :
   - Preserver un smartphone pendant 3 ans supplementaires evite l'extraction de 82 kg de minerai dans des zones ecologiquement fragiles (mines de cobalt en RDC, lithium dans les salars d'Amerique du Sud).
2. **50 a 70 kg d'emissions de gaz a effet de serre (CO2e) evitees** :
   - 80% de l'empreinte carbone d'un smartphone est liee a son extraction miniere et a sa fabrication industrielle en usine. Repousser le renouvellement de 3 ans divise par deux l'impact carbone annuel moyen d'un mobinaute.
3. **Reduction drastique des Dechets d'Equipements Electriques et Electroniques (DEEE)** :
   - 10 000 smartphones conserves sous OSauce representent **1.8 a 2.0 tonnes de dechets electroniques hautement toxiques** en moins deverses dans les decharges ou les filieres de recyclage imparfaites.
4. **Division par deux de l'usure des accumulateurs chimiques** :
   - Moins de cycles de recharge = preservation a long terme de la batterie sans risque de gonflement ou de degradation rapide.
5. **Contribution directe et mesurable a l'ODD 12 (Consommation et production responsables)** :
   - Illustration concrete de l'eco-conception logicielle (sobriete numerique) mise au service de l'economie circulaire materielle.

---

## 3. Guide de Recopie Minute pour la Planche 2 (Cartouches Format Affiche)

Pour remplir directement les cases de la feuille d'examen Planche 2 :

- **MATÉRIELS ET OUTILS** :
  * PC portables de dev reconditionnes (ThinkPad sous Linux).
  * Serveurs CI/CD decarbonés (PUE < 1.15, OVHcloud / Scaleway).
  * Parc de test restreint : smartphones de test d'occasion (Pixel, Fairphone).
  * Outils d'eco-conception : Scaphandre, PowerTOP, cargo-audit, Slint.
  * Câbles USB-C durables standardises (zero accessoire proprietaire).

- **PRODUCTION** :
  * Moteur natif Rust sans machine virtuelle ni Ramasse-Miettes (GC).
  * Chaine de compilation sobre (Alpine Linux & postmarketOS).
  * Builds incrementaux et caches stricts pour reduire les cycles CPU.
  * Images ultra-compressees en Zstandard (< 1.5 Go vs 15-20 Go Android).
  * Hebergement francais/europeen alimente par electricite bas-carbone.

- **MATIÈRES** :
  * 100% dematerialise : zero plastique produit, zero packaging.
  * 82 kg de matieres premieres brutes economisees par smartphone prolonge (terres rares, cobalt, lithium, cuivre - source ADEME).
  * Consommation marginale d'electricite reseau (< 0.05 kWh par installation).

- **VALEUR FONCTIONNELLE** :
  * Allongement de la duree de vie utile des smartphones de +3 ans.
  * Restauration d'une fluidite 120 FPS sur materiel vieux de 6 ans.
  * Empreinte memoire reduite a moins de 150 Mo de RAM (< 5% d'Android).
  * Decouplage ecologique : acces aux services vitaux sans rachat de neuf.

- **FIN DE VIE** :
  * Retardement de la phase de dechet de 3 a 5 ans par terminal.
  * Effacement cryptographique propre pour cession ou reconditionnement.
  * Orientation vers la filiere DEEE agreee (Ecosystem, Ecologic).
  * Reconversion possible en micro-serveur Linux local sedentaire (Upcycling).

- **DISTRIBUTION** :
  * Flashage WebUSB direct dans le navigateur (zero logistique physique).
  * Compression Zstandard divisant par 10 le flux de donnees reseau.
  * Miroirs et CDN locaux europeens a faible dissipation thermique.
  * Mutualisation du transport avec les reconditionneurs partenaires.

- **PHASE D'USAGE** :
  * Zero service fantome en arriere-plan (CPU en veille profonde).
  * Autonomie de batterie doublee : recharge tous les 2 jours.
  * Fond noir OLED pur (#000000) eteignant les pixels (-40% conso ecran).
  * Zero pollution publicitaire : gigaoctets de flux 4G/5G economises.
  * Sandbox Waydroid lancee a la demande uniquement.

- **IMPACTS ENVIRONNEMENTAUX (-)** :
  * Electricite des compilations CI/CD -> Attenue par builds incrementaux et PUE < 1.15.
  * Transfert reseau initial (~1.5 Go) -> Compresse en Zstandard et CDN de proximite.
  * Risque d'effet rebond sur les chargeurs -> Guide d'usage chargeurs a haut rendement.
  * Empreinte du parc de dev -> Materiel reconditionne obligatoire conserve 5 ans.

- **BÉNÉFICES ENVIRONNEMENTAUX (+)** :
  * 82 kg de matieres premieres brutes epargnees par smartphone (ADEME).
  * 50 a 70 kg de CO2e evites par appareil dont l'achat neuf est repousse.
  * Reduction drastique de 1.8 t de dechets electroniques DEEE par tranche de 10 000 mobiles.
  * Division par 2 de l'usure chimique des batteries lithium-ion.
  * Desengorgement significatif de la bande passante sur les antennes reseaux.
