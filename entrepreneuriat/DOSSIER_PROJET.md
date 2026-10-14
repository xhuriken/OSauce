# Dossier Projet & Étude de Marché - OpenSauce (Aka O.Sauce)

Ce document synthétise la stratégie produit, l'intégration dans le référentiel CDA (RNCP 37873, Niveau 6), l'étude de marché chiffrée et la viabilité économique d'OpenSauce.

---

## 1. Synthèse Produit & Référentiel CDA (Niveau 6)

### 1.1 Le Problème
- **Surcharge et obsolescence logicielle (Bloatware)** : Android et les surcouches constructeurs (Samsung OneUI, MIUI) pèsent de 15 à 25 Go sur le stockage, consomment 3 à 4 Go de RAM à vide, et ralentissent délibérément les smartphones après 2 ou 3 ans.
- **Pollution cognitive et pistage** : 65 à 100 notifications par jour, applications partenaires imposées impossibles à désinstaller, télémétrie publicitaire continue consommant jusqu'à 20% de batterie.
- **Échec des dumbphones et des fausses alternatives** : Les dumbphones à touches (+148% chez la Gen Z) sont invivables en 2026 (banques 3D Secure, billets SNCF/Navigo impossibles), et les téléphones minimalistes neufs (Light Phone III à 800 $) forcent encore à racheter du neuf hors de prix.

### 1.2 La Solution : OpenSauce (Aka O.Sauce)
1. **Launcher Android Sobre (Client Mobile)** : Développé nativement en **Kotlin** et **Jetpack Compose**. Écran noir OLED pur (#000000), typographie épurée, horloge vivante, 60 à 120 FPS sur GPU, zéro pastille rouge anxiogène.
2. **Tri intelligent des alertes** : Utilisation de l'API système Android `NotificationListenerService` pour intercepter les notifications localement et les classer par Groupes d'Action (*Humain*, *Travail*, *Quotidien*, *Temps Libre*) avec conversion en tâches.
3. **Backend & Catalogue de Mods Ouverts** : API REST connectée à une base relationnelle PostgreSQL pour gérer le registre de mods, widgets et shaders AGSL vérifiés sans pisteurs.
4. **Zéro barrière d'installation** : S'installe en 30 secondes depuis un APK ou F-Droid, sans toucher au bootloader, sans root, avec maintien à 100% des applications bancaires et indispensables.
5. **Packs Reconditionnés Clés en Main** : Pour ceux qui ne veulent aucune manipulation, offre de smartphones reconditionnés garantis avec OpenSauce déjà préconfiguré.

### 1.3 Couverture des 11 Compétences CDA (RNCP 37873)
- **CP 1 (Environnement)** : Toolchain Android, Gradle Kotlin DSL, Git, Android Studio / ADB, CI/CD.
- **CP 2 (UI)** : Interface mobile tactile en Jetpack Compose, Material 3 adapté OLED sombre, accessibilité.
- **CP 3 (Métier)** : Architecture applicative Kotlin moderne (Coroutines, StateFlow, Room local DB, respect RGPD).
- **CP 4 (Gestion de projet)** : Méthodologie Agile/Scrum, Kanban public, gestion des versions Git.
- **CP 5 (Besoins & Maquettage)** : Cahier des charges, user stories, maquettage Figma et prototypes.
- **CP 6 (Architecture logicielle)** : Clean Architecture / MVVM, séparation Presentation / Domain / Data, écoconception logicielle.
- **CP 7 (Base de données relationnelle)** : Conception MCD/MPD PostgreSQL du catalogue de mods et métadonnées.
- **CP 8 (Accès données SQL & NoSQL)** : Requêtes préparées sécurisées, transactions ACID, cache et requêtes Room SQLite.
- **CP 9 (Tests)** : Tests unitaires Kotlin (JUnit, Mockk) et tests d'interface Jetpack Compose.
- **CP 10 (Déploiement)** : Packaging APK signé, intégration F-Droid et livraison continue.
- **CP 11 (DevOps)** : CI/CD GitHub Actions (linting, tests automatiques, compilation et releases automatisées).

---

## 2. Analyse de Marché Approfondie (Chiffres, Dates, Sources)

Le projet s'inscrit à la convergence de trois dynamiques de marché majeures :

### 2.1 Marché du Smartphone Neuf vs Reconditionné

#### Marché du Neuf en Stagnation Structurelle
- **Tendance** : Saturation du marché mondial du smartphone neuf (IDC, 2024-2025). Le cycle moyen de renouvellement d'un smartphone est passé de 24 mois à **plus de 36 à 42 mois** en Europe (Source : Arcep / ADEME).
- **Cause** : Manque d'innovations de rupture, flambée des prix des modèles haut de gamme (> 1 000 €) et inflation.

#### Explosion du Marché Reconditionné
- **Croissance en France** : En 2025, le marché français du reconditionné a atteint **4,2 millions d'appareils vendus**, en hausse de **+23%** sur un an (Source : Recommerce / Kantar).
- **Poids économique** : Le marché français représente **1,2 milliard d'euros**. **1 smartphone vendu sur 5** (22% du parc actif) est désormais un smartphone reconditionné.
- **Impact écologique (Source : ADEME)** :
  - Un smartphone reconditionné permet d'éviter **77% à 91%** d'impact environnemental annuel par rapport au neuf.
  - **-87% d'émissions de gaz à effet de serre**.
  - **82 kg de matières premières vierges** épargnées par appareil.

### 2.2 Tendance "Digital Detox", Minimalisme & Rejet de l'Hyper-Connexion
- **Croissance des "Dumbphones" chez les jeunes** : Les ventes de téléphones minimalistes ou déconnexion auprès de la tranche 18-24 ans (Gen Z) ont progressé de **+148% entre 2021 et 2024** (Source : Dr. A. Shaji George, PUIRP 2024 ; Counterpoint Research).
- **La fatigue des écrans** : Le temps d'écran moyen quotidien sur smartphone atteint **4h37 par jour** en France (Source : Data.ai / Arcep), avec 80 notifications par jour. Le besoin d'outils épurés, sans notifications toxiques, répond à une attente sociologique de santé mentale avérée.

### 2.3 Alignement Réglementaire Européen
- **Digital Markets Act (DMA)** : L'Union Européenne favorise les magasins d'applications alternatifs et le sideloading, brisant les monopoles fermés.
- **Directive Écoconception et Droit à la Réparabilité** : Incitation forte à prolonger la durée de vie logicielle et matérielle des appareils.

---

## 3. Positionnement Concurrentiel

| Critère | Android Stock (Google) | Dumbphones (Light Phone / Nokia) | Custom ROMs (LineageOS) | OpenSauce (Notre Solution) |
| :--- | :--- | :--- | :--- | :--- |
| **Poids & Empreinte** | 15 à 25 Go / 3-4 Go RAM | < 50 Mo / 512 Mo RAM | 8 à 12 Go / 2 Go RAM | **APK ~15 Mo / Légèreté Compose** |
| **Rendu & Fluidité** | Surcharge constructeur | Écran basique / E-ink | Standard Android | **Jetpack Compose 60/120 FPS** |
| **Design & Attention** | Bannières / Badges rouges | Rigide / Monochrome | Standard Android | **OLED pur, Silence, Hub d'Action** |
| **Compatibilité Apps** | Totale | Nulle à quasi-nulle | Totale (sans SafetyNet) | **100% native (Banque, Train, WhatsApp)**|
| **Facilité d'install** | Préinstallé | Préinstallé | Complexe (bootloader, PC) | **30 secondes (APK / F-Droid)** |
| **Modèle Économique** | Publicitaire / Données | Vente appareil cher (800 $) | Dons | **100% Libre/Dons + Pack Reconditionné**|

---

## 4. Stratégie Économique & Viabilité

1. **Canal Logiciel Libre (Gratuit & Universel)** :
   - Ciblant les étudiants, travailleurs du savoir et adeptes de sobriété numérique.
   - Téléchargement direct APK ou F-Droid en 30 secondes.
   - Financement participatif par **dons libres et mécénat** (GitHub Sponsors, Open Collective). Zéro freemium bloqué.
2. **Canal Clé en Main Reconditionné (Option Verte Éthique)** :
   - Partenariat avec des reconditionneurs solidaires (YesYes, Emmaüs Connect, ateliers de l'ESS) : vente de smartphones reconditionnés abordables (120 à 150 €) avec OpenSauce déjà préconfiguré et testé, garanti 1 an.
   - Marge éthique de 40 à 50 € par unité pour couvrir la configuration, les tests et financer le projet.
3. **Accompagnement Éducation & Flottes (Campus)** :
   - Sessions de sensibilisation à l'attention numérique et profils de configuration pour flottes étudiantes ou professionnelles.

---

## 5. Cadrage ODD (ONU)

- **ODD 12 (Principal) : Consommation et production responsables** (allongement de la durée de vie des smartphones, valorisation du reconditionné, sobriété logicielle).
- **ODD 3 (Secondaire) : Bonne santé et bien-être** (réduction de la surcharge mentale et de l'anxiété liée aux flux infinis et aux pastilles de notification).
- **ODD 9 (Support) : Industrie, innovation et infrastructure** (communs numériques ouverts, respect strict des données personnelles).

---

## 6. Matrice de Marché Multi-Échelle (MTA, MAS, MARS, PAP, Sous-PAP)

| Acronyme FR | Terme Français | Définition Périmètre OpenSauce | Faits & Chiffres Clés |
| :--- | :--- | :--- | :--- |
| **MTA** | **Marché Total Accessible** | Parc mondial des utilisateurs Android et smartphones reconditionnés. | - **3,3+ milliards** d'utilisateurs Android dans le monde.<br/>- **431 millions d'unités** reconditionnées projetées d'ici 2027 (IDC). |
| **MAS** | **Marché Accessible Servi** | Utilisateurs Android dans l'Union Européenne sous cadre DMA. | - **250+ millions** d'utilisateurs Android dans l'UE.<br/>- Marché européen du reconditionné estimé à **25 millions d'appareils / an**. |
| **MARS** | **Marché Réellement Servi** | France : utilisateurs Android cherchant à réduire leur temps d'écran et acheteurs de reconditionné. | - **4,2 millions** de smartphones reconditionnés vendus en France (Recommerce 2025).<br/>- **37%** des Français souhaitent réduire leur temps d'écran (Arcep / Credoc). |
| **PAP** | **Adopteurs Précoces** | Étudiants tech, Gen Z et propriétaires d'appareils de 3-5 ans sensibles à la vie privée. | - **180 000 étudiants/pros** tech en France.<br/>- +148% d'intérêt pour la déconnexion chez les 18-24 ans (PUIRP 2024). |
| **Sous-PAP** | **Zone Test** | Campus étudiants et communauté Discord de bêta-testeurs. | - **200 à 500 étudiants et contributeurs** mobilisables pour tester l'APK et remonter des retours d'usage. |
