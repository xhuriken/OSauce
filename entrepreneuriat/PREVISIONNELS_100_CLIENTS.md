# Prévisionnels Simplifiés sur 12 Mois pour 100 Clients - Projet OpenSauce (O.Sauce)

Ce document présente les trois tableaux prévisionnels obligatoires au format **ultra-KISS (Keep It Simple, Stupid)**, adaptés au modèle éthique d'OpenSauce :
- **Modèle 100% Libre & Gratuit, Zéro Freemium**.
- **Deux modes d'adoption** :
  1. **Adoption Gratuite (Sur son propre smartphone)** : Téléchargement autonome de l'application via APK ou F-Droid (70 clients sur 100).
  2. **Pack Reconditionné Éco-responsable** : Achat d'un smartphone reconditionné garanti avec OpenSauce déjà préinstallé pour ceux qui ont besoin d'un appareil prêt à l'emploi (30 clients sur 100).
- **Financement complémentaire** : Dons libres de la communauté pour soutenir le développement open source.

---

# 1. FEUILLE 1 : PRÉVISIONNEL ÉCONOMIQUE (Sac d'argent)

### Hypothèses simples :
- 70 utilisateurs installent OpenSauce gratuitement sur leur téléphone existant (0 €).
- 30 utilisateurs achètent un smartphone reconditionné préinstallé à 150 € (acheté 100 € auprès de l'atelier partenaire solidaire = 50 € de marge éthique par téléphone).
- Dons libres de la communauté (GitHub Sponsors / Open Collective) : 30 € / mois = 360 € / an.

### Tableau Prévisionnel

```
+-----------------------------------------------------------------------+-----------------------------------------------------------------------+
| COÛTS (-)                                                             | REVENUS (+)                                                           |
+--------------------------+---------------+---------------+------------+--------------------------+---------------+---------------+------------+
| Poste de dépense         | Coût unit (€) | Qté / Durée   | Total (€)  | Poste revenu gain        | Prix unit (€) | Qté / Durée   | Total (€)  |
+--------------------------+---------------+---------------+------------+--------------------------+---------------+---------------+------------+
| Achat smartphones recond.| 100,00 €      | 30 unités     | 3 000,00 € | Vente smartphones recond.| 150,00 €      | 30 unités     | 4 500,00 € |
| Hébergement site & API   | 15,00 € / mois| 12 mois       |   180,00 € | Dons libres communauté   | 30,00 € / mois| 12 mois       |   360,00 € |
| Nom de domaine (osauce.io| 30,00 €       | 1 an          |    30,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| Frais d'envoi colis postal| 5,00 € / envoi| 30 colis      |   150,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| Smartphone test d'occasion| 150,00 €     | 1 unité       |   150,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| (Ligne libre)            | -             | -             |     0,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| (Ligne libre)            | -             | -             |     0,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| (Ligne libre)            | -             | -             |     0,00 € | (Ligne libre)            | -             | -             |     0,00 € |
+--------------------------+---------------+---------------+------------+--------------------------+---------------+---------------+------------+
| TOTAL COÛTS                              | 3 510,00 €                 | TOTAL REVENUS                            | 4 860,00 €                 |
+------------------------------------------+----------------------------+------------------------------------------+----------------------------+
```

### Formule Finale du Bas de Feuille (Tableau 1)
- **4 860,00 €** (recettes) - **3 510,00 €** (dépenses) = **1 350,00 € de bénéfice brut**
- Modèle auto-financé, sans dette, sans avance de trésorerie risquée (achats reconditionnés à la commande en flux tendu).

---

# 2. FEUILLE 2 : PRÉVISIONNEL CARBONE (Usine)

### Hypothèses simples :
- Les 100 clients évitent d'acheter un smartphone neuf (fabrication d'un smartphone neuf = 55 kg CO2e selon l'ADEME).
- 30 téléphones envoyés par la Poste en Colissimo compensé carbone (1,5 kg CO2e par colis).
- Serveur de téléchargement et API de taille minime (l'APK pèse ~15 Mo, empreinte réseau divisée par 100 par rapport à une image OS lourde).

### Tableau Prévisionnel

```
+-----------------------------------------------------------------------+-----------------------------------------------------------------------+
| ÉMISSIONS DU PROJET (-)                                               | ÉMISSIONS ÉVITÉES PAR FOYER (+)                                       |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| SOURCE               | ÉMISSIONS (kg CO2e)| HYPOTHÈSES                | SOURCE               | ÉVITÉES (kg CO2e)  | HYPOTHÈSES                |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Serveur web & API    | 20 kg CO2e         | Serveur vert en France    | Achat de téléphones  | 5 500 kg CO2e      | 55 kg CO2e évités pour    |
| de téléchargement APK|                    | (hébergeur bas-carbone)   | neufs évité (ADEME)  |                    | 100 téléphones prolongés  |
| Envoi 30 colis postes| 45 kg CO2e         | 30 colis Colissimo        | Énergie économisée   |   200 kg CO2e      | Fond noir OLED pur        |
| (téléphones recond.) |                    | (1,5 kg CO2e par colis)   | en usage quotidien   |                    | et zéro tâche fantôme     |
| Électricité PC de dev| 30 kg CO2e         | PC portable sobre recond. | (Ligne libre)        | -                  | -                         |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| TOTAL ÉMISSIONS PROJET                    | 95 kg CO2e                | TOTAL ÉMISSIONS ÉVITÉES                  | 5 700 kg CO2e             |
+-------------------------------------------+---------------------------+------------------------------------------+---------------------------+
```

### Formule Finale du Bas de Feuille (Tableau 2)
- **5 700 kg CO2e** (émissions évitées) - **95 kg CO2e** (émissions du projet) = **5 605 kg CO2e évités nets pour 100 clients** (soit l'équivalent de 28 000 km en voiture thermique évités).

---

# 3. FEUILLE 3 : ÉQUILIBRE SOCIAL ET DE VIE DE L'ÉQUIPE (Bonhomme / Horloge)

### Hypothèses simples :
- Pas de stress de production industrielle ni de SAV complexe.
- Respect absolu du temps de travail et déconnexion de l'équipe (droit à la déconnexion appliqué aux créateurs eux-mêmes).
- Répartition claire du temps de travail sur 12 mois.

```
+-----------------------------------------------------------------------+-----------------------------------------------------------------------+
| TEMPS CONSACRÉ AU PROJET (-)                                          | TEMPS DE VIE PERSONNELLE & APPAISEMENT (+)                            |
+--------------------------+---------------+----------------------------+--------------------------+---------------+----------------------------+
| Activité                 | Heures / sem  | Total Annuel (52 sem)      | Sphère de vie préservée  | Heures / sem  | Bénéfice ressenti          |
+--------------------------+---------------+----------------------------+--------------------------+---------------+----------------------------+
| Dév Android & Compose    | 8 h / sem     | 416 heures                 | Sommeil réparateur       | 56 h / sem    | Pas de rush nocturne       |
| Support Discord & dons   | 2 h / sem     | 104 heures                 | Vie sociale & famille    | 25 h / sem    | Week-ends 100% déconnectés |
| Préparation packs recond.| 2 h / sem     | 104 heures                 | Sport, plein air & santé | 10 h / sem    | Réduction du temps d'écran |
| Maintenance serveur & doc| 1 h / sem     |  52 heures                 | Temps de veille créative |  5 h / sem    | Lectures et projets libres |
+--------------------------+---------------+----------------------------+--------------------------+---------------+----------------------------+
| TOTAL HEURES PROJET                      | 676 h (soit ~13 h / sem)   | TOTAL TEMPS PERSONNEL                    | 96 h / sem                 |
+------------------------------------------+----------------------------+------------------------------------------+----------------------------+
```
