# Environmental Business Model Canvas (Planche 2) - Projet OpenSauce (O.Sauce)

Ce document formalise l'intégralité de l'Environmental Business Model Canvas (Planche 2 du dossier d'entrepreneuriat et d'impact, basé sur le modèle de l'Analyse du Cycle de Vie - ACV / Triple Layered Business Model Canvas). Il détaille l'ensemble du cycle de vie d'OpenSauce et quantifie les gains environnementaux reliés à l'ODD 12 (Consommation et production responsables).

---

## 1. Vue d'Ensemble Synthétique (Schéma Officiel Planche 2)

```
+-----------------------------------------------------------------------------------------------------------------------------------------------+
| DATE: Octobre 2026   | PROGRAMME: Titre CDA / Entrepreneuriat   | SPÉCIALITÉ: Ingénierie Logicielle Mobile, Écoconception & Open Source        |
| NOM: OpenSauce (Aka O.Sauce)                                                                                                                  |
+-----------------------------------------------------------------------------------------------------------------------------------------------+
| MATÉRIELS ET OUTILS   | PRODUCTION            | VALEUR FONCTIONNELLE    | FIN DE VIE            | PHASE D'USAGE                               |
|                       |                       |                         |                       |                                             |
| - PC de développement | - Code natif Kotlin   | - Prolongation de vie   | - Report de la fin de | - Zéro service fantôme en                   |
|   reconditionnés pour   & Jetpack Compose       du smartphone existant    vie matérielle de     veille (CPU au repos profond,               |
|   l'équipe              optimisé GPU            de +2 à +3 ans            2 à 4 ans             zéro wakelock parasite)                     |
| - Serveurs CI/CD      | - Pipeline CI/CD      | - Empreinte logicielle  | - Effacement propre   | - Autonomie batterie préservée :            |
|   décarbonés (PUE<1.15  sobre sous GitHub       minimale : APK de ~15 Mo  des données pour        cycles de recharge espacés                  |
|   OVHcloud/Scaleway)    Actions & Gradle cache  au lieu d'une surcouche   faciliter le réemploi | - Fond noir OLED pur (#000000) :            |
| - Banc d'essai tests  | - Binaire compact       constructeur de 20 Go   | - Orientation vers    extinction physique des pixels              |
|   sur smartphones       signé, zéro télémétrie| - Fluidité 60/120 FPS   |   filière DEEE agréée   (-30% à -40% conso écran)                   |
|   d'occasion (Pixel,  | - Hébergement en        sur GPU modeste         |   (Ecosystem, Ecologic) | - Zéro publicité ni traqueur :              |
|   Galaxy d'occasion)    datacenter à 90%+     | - Évitement de l'achat  +-----------------------+   économie de plusieurs Go de                 |
| - Câbles standards      électricité bas-carbone forcé d'un appareil     | DISTRIBUTION          |   bande passante 4G/5G par mois               |
|   durables USB-C        (France / Europe)       neuf pour accéder à des |                       | - Interception locale des alertes           |
|                       +-----------------------+ services modernes       | - Téléchargement APK  |   (NotificationListenerService)             |
|                       | MATIÈRES              |                         |   ultra-léger (~15 Mo)|   sans appel réseau externe                 |
|                       |                       |                         |   direct ou F-Droid   |                                             |
|                       | - 100% immatériel :   |                         | - Zéro boîte plastique|                                             |
|                       |   zéro plastique      |                         | - Packs reconditionnés|                                             |
|                       | - 82 kg de matières   |                         |   expédiés en colis   |                                             |
|                       |   brutes épargnées    |                         |   carton recyclé      |                                             |
|                       |   par terminal (ADEME)|                         |   (Poste neutre carb.)|                                             |
+-----------------------+-----------------------+-------------------------+-----------------------+---------------------------------------------+
| IMPACTS ENVIRONNEMENTAUX (-) (Empreinte & Mitigations)                  | BÉNÉFICES ENVIRONNEMENTAUX (+) (Ressources & Climat)                |
|                                                                         |                                                                     |
| - Électricité consommée par la compilation et CI/CD                     | - 82 kg de matières premières brutes épargnées par smartphone prolongé|
|   Mitigation: builds incrémentaux, caches Gradle, PUE < 1.15            |   (terres rares, lithium, cobalt, or, cuivre - source ADEME)        |
| - Trafic réseau du téléchargement APK (très faible : ~15 Mo)            | - 50 à 70 kg de CO2e évités par smartphone dont l'achat neuf est repoussé|
|   Mitigation: compression ProGuard / R8 et distribution locale F-Droid  |   (80% de l'empreinte carbone d'un smartphone est à la fabrication) |
| - Transport postal pour les packs reconditionnés clés en main           | - Allongement de la durée de vie chimique des batteries             |
|   Mitigation: expédition groupée Colissimo compensée carbone            | - Baisse drastique des déchets électroniques toxiques (DEEE)        |
| - Empreinte matérielle des postes de travail des développeurs           | - Désengorgement du réseau mondial télécom (zéro pub, zéro pistage) |
|   Mitigation: ordinateurs reconditionnés conservés plus de 5 ans        |                                                                     |
+-------------------------------------------------------------------------+---------------------------------------------------------------------+
```

---

## 2. Contenu Détaillé Bloc par Bloc

### Bloc 1 : Matériels et Outils
1. **Postes de travail reconditionnés** : Ordinateurs portables de développement professionnels de seconde main (ThinkPad reconditionnés). Conservation du matériel sur 5 ans minimum.
2. **Banc d'essai matériel exclusivement d'occasion** : Flotte de test restreinte à 2 smartphones d'occasion sous différentes versions d'Android (Android 9 à 15). Zéro achat d'équipement neuf.
3. **Infrastructure CI/CD sobre** : Runners GitHub Actions et hébergement chez des prestataires à haute efficacité énergétique (PUE < 1.15).

### Bloc 2 : Production
1. **Architecture native Kotlin & Jetpack Compose** : Code compilé optimisé par les outils Android R8 / ProGuard, éliminant tout code mort et produisant un APK ultra-compact (~15 Mo).
2. **Builds incrémentaux et caches stricts** : Réduction maximale du temps processeur en environnement serveur lors des releases.
3. **Hébergement vert** : Serveurs alimentés par une électricité française/européenne décarbonée.

### Bloc 3 : Matières
1. **Distribution purement logicielle et dématérialisée** : Zéro support physique, zéro emballage plastique pour l'application.
2. **Épargne massive de ressources terrestres (ADEME)** : 82 kg de matières premières brutes (cuivre, terres rares, cobalt, lithium) épargnées pour chaque smartphone conservé.

### Bloc 4 : Valeur Fonctionnelle
1. **Prolongation de vie utile des téléphones (+2 à 3 ans)** : Remplacement de la surcouche constructeur devenue trop lente par un Launcher sobre et réactif.
2. **Sobriété d'usage** : Accès complet aux services modernes sans renouvellement d'appareil.

### Bloc 5 : Fin de Vie
1. **Recul de la mise au rebut** : Décalage de 2 à 4 ans de la phase de déchet électronique.
2. **Filières agréées** : Sensibilisation de la communauté au recyclage auprès d'Ecosystem / Ecologic en cas de panne matérielle finale.
3. **Réemploi solidaire** : Les packs reconditionnés favorisent directement l'économie circulaire et le réemploi.

### Bloc 6 : Distribution
1. **Téléchargement direct F-Droid et APK** : Empreinte réseau insignifiante (15 Mo par téléchargement contre 1,5 à 20 Go pour une image OS).
2. **Colis écologiques** : Pour les packs reconditionnés, emballages en carton 100% recyclé et livraisons neutres en carbone.

### Bloc 7 : Phase d'Usage
1. **Zéro wakelock parasite** : Le processeur reste en veille profonde (*Deep Sleep*) quand l'écran est éteint.
2. **Fond noir OLED pur (#000000)** : Extinction physique des pixels sur dalles OLED, permettant d'économiser jusqu'à 30% à 40% de batterie sur l'écran d'accueil.
3. **Suppression de la télémétrie publicitaire** : Zéro requête réseau superflue, soulageant la bande passante 4G/5G.

### Bloc 8 : Impacts Environnementaux (-)
- *Énergie des compilations CI/CD* : Minimisée par les caches de build.
- *Fret postal pour les téléphones reconditionnés* : Compensé carbone et mutualisé avec les ateliers solidaires.

### Bloc 9 : Bénéfices Environnementaux (+)
- **82 kg de matières premières brutes épargnées** par smartphone prolongé.
- **50 à 70 kg de CO2e évités** par smartphone neuf non produit.
- **Contribution mesurable à l'ODD 12** (Consommation et production responsables).
