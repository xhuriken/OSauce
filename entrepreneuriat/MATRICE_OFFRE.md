# Matrice de l'Offre - Projet OSauce

Ce document formalise la grille strategique de l'Offre (Vision, Mission, Valeurs, Cible, Proposition de Valeur et Matrice 3x5 : Basique, Intermediaire, Premium), adaptee a un modele Open Source econome, perenne et viable (Open Core et Services a Valeur Ajoutee).

---

## 1. Comment Monétiser un Projet Open Source ? (Le Modèle Économique)

Dans le monde du logiciel libre (Linux, Android, Red Hat, WordPress, Mozilla), **être Open Source n'interdit absolument pas de faire du chiffre d'affaires**.

Le code source du moteur ([osauce-shell](file:///c:/Users/celestin/OS/src/main.rs)) reste libre, gratuit et auditable par tous. La valeur marchande se structure sur :
1. **La commodite et le zero-effort** : Les utilisateurs qui ne veulent pas flasher eux-memes achetent un smartphone reconditionne cle en main avec OSauce deja installe.
2. **Les services cloud souverains (SauceSync)** : Synchronisation securisee et chiffree de bout en bout des donnees personnelles pour quelques euros par mois.
3. **Le marche B2B / Education (Flottes professionnelles)** : Les ecoles, collectivites et entreprises paient pour un support technique garanti (SLA) et un outil de deploiement de parc (MDM sobre).

---

## 2. En-tête et Cadrage Haut de la Fiche

### Vision / Mission / Valeurs (Cartouche Haut Gauche)
- **VISION** : Faire du smartphone un outil d'emancipation sobre, durable et reparable, liberé de la tyrannie attentionnelle et de l'obsolescence programmee des geants de la tech.
- **MISSION** : Donner une seconde vie de 3 a 5 ans aux smartphones existants grace a un OS natif en Rust (< 150 Mo RAM) et un installateur WebUSB en 1 clic.
- **VALEURS** : Sobriete numerique (KISS), transparence open source, respect inconditionnel de la vie privee, justice sociale et ecologique (ODD 12).

### Proposition de Valeur (Cartouche Haut Centre)
- **Offre Principale** : Le premier systeme d'exploitation mobile ultra-leger qui redonne 120 FPS et 2 jours d'autonomie a votre telephone, supprime 80 notifications polluantes par jour, tout en conservant vos applications bancaires et de transport vitales dans une sandbox isolee.

### Cible (Cartouche Haut Droite)
- **Cible B2C** : Etudiants et Gen Z (18-25 ans) souffrant d'anxiete numerique et cherchant a reduire leur temps d'ecran ; proprietaires de smartphones vieillissants refusant de payer 800 euros pour un nouvel appareil.
- **Cible B2B / Institutionnelle** : Ecoles, universites, collectivites et entreprises souhaitant deployer des flottes mobiles sobres, securisees et exemptes de distractions.

---

## 3. Matrice de l'Offre (Grille 3 x 5)

```
+---------------+------------------------+------------------------+----------------------+--------------------------+----------------------------+
| NIVEAU        | PRODUIT                | PLUS                   | PRIX                 | CONTENU                  | PROCESSUS                  |
+---------------+------------------------+------------------------+----------------------+--------------------------+----------------------------+
| BASIQUE       | OSauce Community       | - Code 100% libre      | 0 euro               | - Image OS complete      | 1. Visite du site          |
| (Open Source  | (ROM autonome a        | - Zero pub, zero data  | (Gratuit & libre     | - Mises a jour FOSS      | 2. Connexion USB           |
|  Grand Public)| installer soi-meme via | - Waydroid integre     |  a vie)              | - Acces au Discord       | 3. Flash WebUSB en 1 clic  |
|               |  WebUSB Flasher)       | - Rendu 120 FPS        |                      | - Store F-Droid integre  | 4. Configuration locale    |
+---------------+------------------------+------------------------+----------------------+--------------------------+----------------------------+
| INTERMEDIAIRE | OSauce Cle en Main &   | - Smartphone garanti   | 2 euros / mois       | - Appareil reconditionne | 1. Commande sur site web   |
| (Serenite     | SauceSync Cloud        |   1 a 2 ans            |   (Cloud SauceSync)  |   pret a l'emploi        | 2. Reception a domicile    |
|  Particulier) | (Pack smartphone       | - Zero manip technique | OU                   | - Espace Cloud 50 Go E2EE| 3. Allumage immediat       |
|               |  reconditionne ou      | - Sauvegarde continue  | 120 a 160 euros      | - Support prioritaire    | 4. Restauration cloud      |
|               |  abonnement Cloud E2EE)|   chiffree en France   |   (pack telephone)   |   par e-mail             |    automatique en 20s      |
+---------------+------------------------+------------------------+----------------------+--------------------------+----------------------------+
| PREMIUM       | OSauce Fleet Pro       | - Console MDM central  | 4 a 6 euros          | - Dashboard web de flotte| 1. Audit du parc client    |
| (Flottes B2B  | (Gestion de parcs pour | - Mode examen / Kiosk  |   par appareil/mois  | - Images OS personnalisees| 2. Signature contrat SLA   |
|  Ecoles &     |  collectivites, ecoles | - Support garanti 4h   | OU                   | - SLA assistance dedie   | 3. Deploiement par lot     |
|  Entreprises) |  et entreprises)       | - Conformite RGPD pro  | 45 euros / an / unit | - Formation des admins   | 4. Supervision centralisee |
+---------------+------------------------+------------------------+----------------------+--------------------------+----------------------------+
```

---

## 4. Contenu Detaille a Reporter dans Chaque Case

### Ligne 1 : Niveau Basique (Community / DIY)
*Pour qui : Les utilisateurs autonomes, etudiants debrouillards, passionnes de logiciels libres.*

- **Produit** :
  * OSauce Community Edition (systeme d'exploitation complet base sur postmarketOS, Slint UI et Rust).
- **Plus** :
  * Gratuit et open source sans aucune limitation artificielle.
  * Respect inconditionnel de la vie privee (aucun compte obligatoire, zero traqueur).
  * Sandbox Waydroid incluse par defaut pour faire tourner les applications vitales.
- **Prix** :
  * **0 €** (Licence libre et telechargement gratuit a vie).
- **Contenu** :
  * Image binaire optimisee pour le smartphone de l'utilisateur.
  * Outil WebUSB Flasher accessible depuis n'importe quel navigateur Chromium (Chrome, Brave, Edge).
  * Acces libre au catalogue d'applications auditees SauceHub / F-Droid.
  * Documentation d'auto-assistance et support d'entraide communautaire sur Discord.
- **Processus** :
  1. L'utilisateur se rend sur le site `osauce.io`.
  2. Il branche son smartphone compatible avec un simple cable USB.
  3. Il clique sur « Installer OSauce » via l'API WebUSB.
  4. L'installation s'execute en 3 minutes sans saisie de commande.

---

### Ligne 2 : Niveau Intermédiaire (Sérénité & Matériel Clé en Main)
*Pour qui : Les personnes qui veulent les benefices d'OSauce sans toucher a la technique, et les utilisateurs souhaitant synchroniser leurs donnees en toute confidentialite.*

- **Produit** :
  * **Option A** : Pack Smartphone Reconditionné OSauce (Pixel ou Fairphone d'occasion reconditionne en France avec OSauce pre-installe).
  * **Option B** : Service Cloud souverain **SauceSync** (sauvegarde et synchronisation continue chiffree de bout en bout).
- **Plus** :
  * Zero manipulation technique : telephone utilisable des la sortie de la boite.
  * Garantie materielle de 12 a 24 mois sur le terminal.
  * Sauvegarde transparente des contacts, agenda et notes sur serveurs francais bas-carbone.
- **Prix** :
  * **SauceSync Cloud** : **2 € / mois** (ou 20 € / an).
  * **Pack Smartphone** : **120 € à 160 €** achat unique (dont 40 € de marge commerciale pour OSauce).
- **Contenu** :
  * Smartphone reconditionne grade A avec batterie neuve et OSauce pre-configure.
  * 50 Go d'espace de stockage cloud souverain chiffre de bout en bout (Zero-Knowledge).
  * Support client dedie avec reponse par e-mail sous 24 heures.
  * Kit de bienvenue papier recycle avec guide de demarrage rapide et conseils de sobriete.
- **Processus** :
  1. Choix du modele de smartphone ou souscription au service cloud sur le site marchand.
  2. Paiement securise en ligne.
  3. Livraison du terminal en colis recyclable sous 48 a 72 heures.
  4. Au premier demarrage, synchronisation instantanee des donnees en scannant un QR code prive.

---

### Ligne 3 : Niveau Premium (Flottes B2B / Éducation / Entreprises)
*Pour qui : Les lycees, universites, collectivites territoriales et entreprises cherchant a equiper leurs equipes avec des smartphones securises, sobres et sans fuite de donnees.*

- **Produit** :
  * **OSauce Fleet Pro** : Solution complete de gestion de terminaux mobiles d'entreprise (*Mobile Device Management* - MDM sobre).
- **Plus** :
  * Console d'administration centralisee : verrouillage a distance, deploiement silencieux des applications metiers.
  * Mode "Examen / Travail" (Kiosk Mode) : desactivation programmable des fonctions distrayantes pendant les cours ou horaires de service.
  * Engagement de service professionnel (SLA : reponse sous 4h ouvrées).
  * Conformite stricte RGPD et certification SecNumCloud hebergee en Europe.
- **Prix** :
  * **4 € à 6 € par appareil et par mois** (ou forfait annuel de 45 € / appareil / an).
  * Forfait de mise en service et formation des administrateurs : 500 € a 1 500 € selon la taille du parc.
- **Contenu** :
  * Licence du tableau de bord Web d'administration de flotte.
  * Images systeme specifiques personnalisees aux couleurs de l'etablissement.
  * Canal de support prioritaire (telephone, hotline dediee et interlocuteur technique unique).
  * Mises a jour de securite garanties avec fenetres de deploiement planifiees.
  * Rapport trimestriel d'impact RSE chiffrant les economies de CO2 et de matieres de la flotte.
- **Processus** :
  1. Prise de contact commerciale et cadrage des besoins du parc client.
  2. Deploiement pilote sur 5 a 10 appareils de test.
  3. Signature du contrat de service et flashage automatise de la flotte par lot.
  4. Gestion autonome et supervision continue du parc par l'administrateur informatique.

---

## 5. Guide de Recopie Minute pour la Fiche

Pour remplir les cartouches de la feuille d'examen :

### Cartouches du Haut :
- **VISION** : Rompre avec l'obsolescence et le vol d'attention en revalorisant les smartphones par le logiciel sobre.
- **MISSION** : Prolonger la vie des mobiles de +3 ans via un OS Rust leger (< 150 Mo RAM) installable en 1 clic.
- **VALEURS** : Sobriete numerique, independance open source, respect de la vie privee, durabilite (ODD 12).
- **PROPOSITION DE VALEUR** : Un smartphone fluide a 120 FPS, 2 jours d'autonomie, sans bloatware ni pistage, isolant les apps vitales.
- **CIBLE** : Etudiants/Gen Z en surcharge numerique, menages rejetes par le cout du neuf, ecoles et flottes pro responsables.

### Tableau 3x5 :
- **BASIQUE (Gratuit)** :
  * *Produit* : OSauce Community (ROM open source libre).
  * *Plus* : 100% gratuit, zero traqueur, Waydroid sandbox, 120 FPS natif.
  * *Prix* : 0 € (Libre a vie).
  * *Contenu* : Image systeme, WebUSB Flasher, store F-Droid, entraide Discord.
  * *Processus* : Connexion USB -> Flash WebUSB en 1 clic -> Pret en 3 min.

- **INTERMEDIAIRE (Particulier Sérénité)** :
  * *Produit* : Smartphone reconditionne pre-installe OU SauceSync Cloud.
  * *Plus* : Terminal cle en main garanti 2 ans, zero manipulation technique, cloud chiffre francais.
  * *Prix* : 2 € / mois (Cloud) OU 120-160 € (Achat smartphone complet).
  * *Contenu* : Smartphone reconditionne pret a l'emploi, 50 Go E2EE, support mail 24h.
  * *Processus* : Commande en ligne -> Livraison 48h -> Allumage direct -> Restauration en 20s.

- **PREMIUM (Pro & Écoles)** :
  * *Produit* : OSauce Fleet Pro (OS + Console MDM pour parcs mobiles).
  * *Plus* : Gestion centralisee, mode examen/kiosk anti-distraction, support pro SLA 4h.
  * *Prix* : 4 a 6 € / appareil / mois (ou 45 € / an).
  * *Contenu* : Dashboard d'administration, ROM sur mesure, assistance prioritaire, bilan RSE carbone.
  * *Processus* : Audit client -> Pilote test -> Deploiement par lot -> Supervision centralisee.
