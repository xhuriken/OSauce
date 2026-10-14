# Business Model Canvas (BMC) - Projet OpenSauce (Aka O.Sauce)

Ce document formalise l'intégralité du Business Model Canvas d'OpenSauce selon le template officiel, structuré en 9 blocs stratégiques, aligné sur l'ODD 12 (Consommation et production responsables) et l'ODD 3 (Santé et bien-être).

---

## 1. Vue d'Ensemble Structurée (Les 9 Blocs)

```
+----------------------------------------------------------------------------------------------------+
| 1. PARTENAIRES CLÉS   | 2. ACTIVITÉS CLÉS     | 4. PROPOSITION DE VALEUR | 5. RELATION CLIENT  | 7. CIBLE (SEGMENTS)  |
|                       |                       |                          |                     |                      |
| - Reconditionneurs    | - Développement       | - Launcher Android sobre | - Entraide &        | - Étudiants & Gen Z  |
|   solidaires (YesYes, |   Android natif       |   anti-dopamine (OLED,   |   support Discord   |   (18-25 ans) saturés|
|   Emmaüs Connect,     |   (Kotlin & Compose)  |   zéro badge anxiogène)  |   communautaire     |   par les écrans     |
|   BackMarket)         | - Gestion du service  | - Installation en 30s    | - Transparence FOSS | - Propriétaires de   |
| - Magasins FOSS       |   de notifications    |   sans flasher ni risquer|   (GitHub issues,   |   smartphones de     |
|   (F-Droid, IzzyDroid)|   (ListenerService)   |   de bricker l'appareil  |   discussions)      |   3-5 ans refusant   |
| - Créateurs de mods   | - API légère catalogue| - Maintien 100% natif    | - Guide utilisateur |   de racheter neuf   |
|   & widgets ouverts   |   de plugins/mods     |   des applis bancaires,  |   sobre en ligne    | - Passionnés de vie  |
| - Associations de     | - Packaging F-Droid   |   SNCF et messageries    | - Co-conception avec|   privée et FOSS     |
|   sobriété numérique  |   et releases APK     | - Tri des notifications  |   beta-testeurs     | - Acheteurs de       |
|   (HOP, Framasoft,    | - Partenariats packs  |   par Groupes d'Action   +---------------------+   reconditionné clé  |
|   Lève les yeux !)    |   reconditionné       + - Marketplace ouvert de  | 6. CANAUX           |   en main sans manip |
| - Hébergeurs sobres   +-----------------------+   mods & shaders AGSL    |                     | - Écoles & flottes   |
|   (Scaleway, Hetzner, | 3. RESSOURCES CLÉS    | - 100% Libre & Gratuit   | - Dépôt F-Droid     |   pro cherchant un   |
|   OVHcloud)           |                       |   (Zéro freemium bloqué) | - Releases GitHub   |   mode focus         |
|                       | - Équipe dev Android  | - Option verte clé en    |   et site web       |                      |
|                       | - Dépôt GitHub FOSS   |   main (smartphone       | - Reconditionneurs  |                      |
|                       | - CI/CD GitHub Actions|   reconditionné garanti  |   partenaires       |                      |
|                       | - API catalogue mods  |   avec O.Sauce préinstall| - Salons éthiques   |                      |
|                       | - Communauté Discord  |   [ ODD 12 & ODD 3 ]     |   & universités     |                      |
+-----------------------+-----------------------+--------------------------+---------------------+----------------------+
| 8. STRUCTURE DE COÛTS                                                    | 9. FLUX DE REVENUS                         |
|                                                                          |                                            |
| - Hébergement sobre : site web, documentation & API catalogue mods       | - OpenSauce Launcher : 100% Gratuit & Libre|
| - Noms de domaine et certificats de sécurité                             | - Dons libres & Mécénat communautaire      |
| - Smartphone de test d'occasion pour validation matérielle               |   (GitHub Sponsors, Open Collective, dons) |
| - Achat des smartphones reconditionnés partenaires (à la commande)       | - Marge éthique sur packs smartphones      |
| - Frais postaux d'expédition des smartphones reconditionnés clés en main |   reconditionnés préinstallés (40 à 50 €)  |
| - Frais de salons et supports de communication de sensibilisation        | - Soutiens institutionnels & fondations    |
+--------------------------------------------------------------------------+--------------------------------------------+
```

---

## 2. Contenu Détaillé à Reporter par Case

### Case 1 : Partenaires Clés
- **Reconditionneurs solidaires et ateliers éthiques** : Ateliers français/européens (YesYes, Emmaüs Connect, Recommerce, BackMarket) pour proposer des packs smartphones reconditionnés avec OpenSauce déjà préconfiguré.
- **Écosystème FOSS & Dépôts alternatifs** : F-Droid, IzzyOnDroid et la communauté Android open source pour la distribution vérifiée et sécurisée sans passer par des canaux fermés.
- **Créateurs de mods et widgets** : Développeurs indépendants et designers créant des shaders AGSL et des passerelles utiles (minuteur Pomodoro, widget Obsidian, transports locaux).
- **Associations d'intérêt général** : HOP (Halte à l'Obsolescence Programmée), Framasoft, Lève les yeux ! et associations étudiantes militant pour la déconnexion et le numérique responsable.
- **Hébergeurs souverains à faible empreinte** : Scaleway, Hetzner ou OVHcloud pour héberger le site web et l'API légère du catalogue de mods.

### Case 2 : Activités Clés
- **Développement du Launcher Android natif** : Conception de l'interface graphique en Jetpack Compose et de la logique applicative en Kotlin moderne.
- **Interception et gestion intelligente des alertes** : Développement et optimisation du `NotificationListenerService` pour filtrer les notifications et les rattacher aux Groupes d'Action sans impacter la batterie.
- **Gestion du Store de mods communautaires** : Maintenance de l'API légère et du dépôt de manifests de plugins audités sans traceurs.
- **Assurance qualité et performance** : Maintien d'un framerate constant à 60/120 FPS, vérification de l'absence de fuites mémoire et optimisation de la consommation de batterie.
- **Préparation des packs reconditionnés** : Installation, vérification et expédition soignée des smartphones reconditionnés partenaires commandés clés en main.
- **Animation de la communauté** : Support utilisateur sur Discord, gestion des contributions GitHub et documentation pédagogique.

### Case 3 : Ressources Clés
- **Ressources humaines** : Compétences en ingénierie logicielle mobile Android (Kotlin, Jetpack Compose, APIs système Android).
- **Ressources logicielles & techniques** : Dépôt de code open source GitHub, pipelines d'intégration continue CI/CD (GitHub Actions), documentation en ligne.
- **Infrastructure serveur minimale** : API REST légère pour distribuer les manifests de mods et les métadonnées.
- **Communauté active** : Groupe d'utilisateurs bêta-testeurs et contributeurs de mods.

### Case 4 : Proposition de Valeur
- **Le sanctuaire anti-dopamine** : Un écran d'accueil noir OLED pur qui éteint les pixels, élimine les pastilles rouges anxiogènes et remplace l'obsession du scroll par un hub d'action structuré.
- **Zéro barrière à l'entrée** : Installation en 30 secondes depuis un fichier APK ou F-Droid. Pas besoin de PC, pas de bootloader à débloquer, zéro risque de bricker son smartphone.
- **Compatibilité 100% native** : Aucune régression. Toutes les applications vitales (banque 3D Secure, billets SNCF/Navigo, messageries professionnelles) fonctionnent immédiatement.
- **Éthique et gratuité totale** : Pas de freemium castrateur. OpenSauce est 100% libre et gratuit pour tous, sans publicité et sans revente de données personnelles.
- **Alternative verte clé en main** : Possibilité d'acquérir un smartphone reconditionné garanti avec OpenSauce déjà installé pour ceux qui veulent un appareil prêt à l'emploi sans effort technique.

### Case 5 : Relation Client
- **Transparence et confiance open source** : Roadmap publique, code entièrement auditable sur GitHub, discussions ouvertes.
- **Support communautaire réactif** : Entraide sur Discord et FAQ en ligne axée sur la simplicité.
- **Accompagnement bienveillant** : Guides d'hygiène numérique pour aider les utilisateurs à retrouver un usage apaisé de leur téléphone.

### Case 6 : Canaux de Distribution
- **Distribution directe mobile** : F-Droid, IzzyOnDroid, téléchargement direct de l'APK sur le site officiel et GitHub Releases.
- **Boutique en ligne solidaire** : Commande des smartphones reconditionnés préinstallés directement sur le site web.
- **Prescription militante** : Salons du libre, interventions universitaires, relais par les associations écologiques et de santé mentale.

### Case 7 : Segments Clients (Cible)
- **Étudiants et Gen Z (18-25 ans)** : Conscients de leur surconsommation d'écrans, en recherche d'une « diète dopaminergique » sans renoncer à leur vie sociale et universitaire.
- **Utilisateurs de smartphones vieillissants** : Propriétaires d'appareils de 3 à 5 ans qui rament sous les surcouches constructeurs et refusent de racheter un téléphone neuf à 800 €.
- **Passionnés du logiciel libre et de la vie privée** : Utilisateurs recherchant un environnement sain et auditable sans télémétrie commerciale.
- **Acheteurs de reconditionné sans compétence technique** : Personnes souhaitant un téléphone durable déjà configuré de manière sobre sans devoir installer d'application eux-mêmes.
- **Écoles et organisations** : Établissements souhaitant sensibiliser ou équiper des flottes mobiles d'un mode épuré sans distractions.

### Case 8 : Structure de Coûts
- **Hébergement et réseau légers** : Serveur web, documentation et API de mods (15 à 30 € / mois).
- **Noms de domaine et sécurité** : ~30 € / an.
- **Matériel de test** : 1 ou 2 smartphones Android d'occasion pour valider la compatibilité des versions Android (Android 9 à 15).
- **Achat des téléphones reconditionnés** : Achetés auprès du reconditionneur partenaire au moment où un utilisateur passe commande (flux tendu, zéro stock dormant).
- **Frais d'emballage et d'expédition** : Colissimo vert pour les commandes matérielles (~5 € / envoi).

### Case 9 : Flux de Revenus
- **OpenSauce Core** : 100% Gratuit et Open Source (aucun abonnement obligatoire, aucun freemium).
- **Dons libres et mécénat communautaire** : Campagnes GitHub Sponsors, Open Collective et dons libres d'utilisateurs reconnaissants de retrouver leur tranquillité d'esprit (~20 à 50 € / mois au lancement).
- **Marge éthique sur packs reconditionnés préinstallés** : Marge de 40 à 50 € par téléphone pour le travail d'installation, de contrôle qualité et de garantie logicielle.
- **Subventions et mécénat d'intérêt général** : Appels à projets écoconception numérique (ADEME, fondations pour l'open source).
