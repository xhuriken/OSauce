# Prévisionnels Simplifiés sur 12 Mois pour 100 Clients - Projet OSauce

Ce document présente les trois tableaux prévisionnels obligatoires au format **ultra-KISS (Keep It Simple, Stupid)**, strictement recalibrés sur le scope réel du projet :
- **Seulement 2 offres** : 
  1. **Offre Basique (Gratuite)** : Téléchargement et flashage autonome WebUSB (70 clients sur 100).
  2. **Offre Intermédiaire (Reconditionné)** : Achat d'un smartphone reconditionné avec OSauce préinstallé (30 clients sur 100).
- **Zéro offre Cloud**, zéro sur-ingénierie, calculs limpides et réalistes.

---

# 1. FEUILLE 1 : PRÉVISIONNEL ÉCONOMIQUE (Sac d'argent)

### Hypothèses simples :
- 70 utilisateurs installent OSauce gratuitement sur leur téléphone existant (0 €).
- 30 utilisateurs achètent un smartphone reconditionné pré-installé à 150 € (acheté 100 € auprès du reconditionneur partenaire = 50 € de marge par téléphone).
- Quelques dons libres de la communauté (GitHub Sponsors / pourboires libres) : 25 € / mois = 300 € / an.

### Tableau à Recopier

```
+-----------------------------------------------------------------------+-----------------------------------------------------------------------+
| COÛTS (-)                                                             | REVENUS (+)                                                           |
+--------------------------+---------------+---------------+------------+--------------------------+---------------+---------------+------------+
| Poste de dépense         | Coût unit (€) | Qté / Durée   | Total (€)  | Poste revenu gain        | Prix unit (€) | Qté / Durée   | Total (€)  |
+--------------------------+---------------+---------------+------------+--------------------------+---------------+---------------+------------+
| Achat smartphones recond.| 100,00 €      | 30 unités     | 3 000,00 € | Vente smartphones recond.| 150,00 €      | 30 unités     | 4 500,00 € |
| Hébergement site web & OS| 15,00 € / mois| 12 mois       |   180,00 € | Dons libres communauté   | 25,00 € / mois| 12 mois       |   300,00 € |
| Nom de domaine (osauce.io| 30,00 €       | 1 an          |    30,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| Frais d'envoi colis postal| 5,00 € / envoi| 30 colis      |   150,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| Smartphone test d'occasion| 150,00 €     | 1 unité       |   150,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| (Ligne libre)            | -             | -             |     0,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| (Ligne libre)            | -             | -             |     0,00 € | (Ligne libre)            | -             | -             |     0,00 € |
| (Ligne libre)            | -             | -             |     0,00 € | (Ligne libre)            | -             | -             |     0,00 € |
+--------------------------+---------------+---------------+------------+--------------------------+---------------+---------------+------------+
| TOTAL COÛTS                              | 3 510,00 €                 | TOTAL REVENUS                            | 4 800,00 €                 |
+------------------------------------------+----------------------------+------------------------------------------+----------------------------+
```

### Formule Finale du Bas de Feuille (Tableau 1)
- **4 800,00 €** (recettes) - **3 510,00 €** (dépenses) = **1 290,00 € de bénéfice brut**

---

# 2. FEUILLE 2 : PRÉVISIONNEL CARBONE (Usine)

### Hypothèses simples :
- Les 100 clients évitent d'acheter un smartphone neuf (fabrication d'un smartphone neuf = 55 kg CO2e selon l'ADEME).
- 30 téléphones envoyés par la Poste (1,5 kg CO2e par colis Colissimo).
- Serveur de téléchargement et PC de dev sobres.

### Tableau à Recopier

```
+-----------------------------------------------------------------------+-----------------------------------------------------------------------+
| ÉMISSIONS DU PROJET (-)                                               | ÉMISSIONS ÉVITÉES PAR FOYER (+)                                       |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| SOURCE               | ÉMISSIONS (kg CO2e)| HYPOTHÈSES                | SOURCE               | ÉVITÉES (kg CO2e)  | HYPOTHÈSES                |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Serveur site & téléch| 20 kg CO2e         | Serveur sobre en France   | Achat de téléphones  | 5 500 kg CO2e      | 55 kg CO2e évités pour    |
| de l'image OS        |                    | (100 téléchargements)     | neufs évité (ADEME)  |                    | 100 téléphones prolongés  |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Postes de travail de | 50 kg CO2e         | PC portable dev amorti    | Recharge batterie    | 200 kg CO2e        | Charge espacée tous les   |
| l'équipe (1 an)      |                    | sur l'année               | divisée par 2        |                    | 2 jours au lieu de 24h    |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Envoi postal des     | 45 kg CO2e         | 30 colis Colissimo neutre | Moins de déchets     | 100 kg CO2e        | 100 téléphones non jetés  |
| 30 téléphones        |                    | en carbone (1,5 kg/colis) | électroniques jetés  |                    | à la poubelle             |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| (Ligne libre)        | 0 kg CO2e          | -                         | (Ligne libre)        | 0 kg CO2e          | -                         |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| (Ligne libre)        | 0 kg CO2e          | -                         | (Ligne libre)        | 0 kg CO2e          | -                         |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| TOTAL ÉMISSIONS      | 115 kg CO2e                                    | TOTAL ÉVITÉES        | 5 800 kg CO2e                                  |
+----------------------+------------------------------------------------+----------------------+------------------------------------------------+
```

### Formule Finale du Bas de Feuille (Tableau 2)
- Émissions évitées : **5 800 kg CO2e** - Émissions générées : **115 kg CO2e**
- Impact net positif = **5 685 kg CO2e/an**
- Soit **56,85 kg CO2e économisés par foyer et par an**

---

# 3. FEUILLE 3 : PRÉVISIONNEL SOCIAL (Réseau de personnes)

### Hypothèses simples :
- Économie financière directe pour les foyers : évite d'acheter un téléphone neuf à 400 € (économie de 250 € à 400 € par personne). En moyenne 300 € économisés par foyer = 30 000 €.
- Récupération de temps de vie (1h30/jour d'écran en moins) valorisée à 150 € / foyer / an = 15 000 €.
- Réduction du stress et de l'anxiété (zéro pub, zéro pastille rouge) valorisée à 50 € / foyer / an = 5 000 €.

### Tableau à Recopier

```
+-----------------------------------------------------------------------+-----------------------------------------------------------------------+
| BILAN SOCIAL DU PROJET                                                | GAINS / DÉTAIL                                                        |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| ASPECT               | COÛTS / INVEST. (-) | BÉNÉFICES / GAINS (+)     | Source de gains      | Estimation (€)     | Détail                    |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Pouvoir d'achat      | 3 000,00 €         | 30 000,00 €               | Économie directe     | 30 000,00 €        | Évite un achat neuf       |
| des utilisateurs     | (achat stocks)     | (gain pouvoir d'achat)    | sur achat smartphone |                    | à 400 € (gain 300 €/foyer)|
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Sérénité mentale     | 0,00 €             | 15 000,00 €               | Récupération de temps| 15 000,00 €        | 1h30 d'écran en moins/j   |
| et déconnexion       |                    | (temps de vie récupéré)   | libre et de sommeil  |                    | (valorisé 150 €/foyer/an) |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Bien-être et         | 0,00 €             | 5 000,00 €                | Moins de stress      | 5 000,00 €         | Zéro notification intrusive|
| fin du matraquage pub|                    | (réduction du stress)     | et fin des bannières |                    | (valorisé 50 €/foyer/an)  |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Frais de gestion     | 360,00 €           | 0,00 €                    | (Ligne libre)        | 0,00 €             | -                         |
| et hébergement web   | (serveur et domaine|                           |                      |                    |                           |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| Envoi et logistique  | 150,00 €           | 0,00 €                    | (Ligne libre)        | 0,00 €             | -                         |
| des 30 colis         | (frais de port)    |                           |                      |                    |                           |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| (Ligne libre)        | 0,00 €             | 0,00 €                    | (Ligne libre)        | 0,00 €             | -                         |
+----------------------+--------------------+---------------------------+----------------------+--------------------+---------------------------+
| TOTAL COÛTS SOCIAL   | 3 510,00 €         | 50 000,00 €               | TOTAL BÉNÉFICES GAINS| 50 000,00 €                                    |
+----------------------+--------------------+---------------------------+----------------------+------------------------------------------------+
```

### Formule Finale du Bas de Feuille (Tableau 3)
- Bilan net social estimé :
- Gains : **50 000,00 €** - Coûts : **3 510,00 €** = **46 490,00 € de bénéfice social net**
- Soit **464,90 € de valeur sociale créée par foyer/an**

---

## Synthèse Ultra-Rapide pour Recopier

### 1. Finance (Sac d'argent) :
- Total Coûts : **3 510 €**
- Total Revenus : **4 800 €**
- Résultat : **4 800 € - 3 510 € = 1 290 € de bénéfice brut**

### 2. Carbone (Usine) :
- Total Émissions : **115 kg CO2e**
- Total Évitées : **5 800 kg CO2e**
- Résultat : **5 800 kg - 115 kg = 5 685 kg CO2e/an** (soit **56,85 kg CO2e/foyer/an**)

### 3. Social (Réseau de personnes) :
- Total Coûts : **3 510 €**
- Total Gains : **50 000 €**
- Résultat : **50 000 € - 3 510 € = 46 490 € de bénéfice social net** (soit **464,90 €/foyer/an**)
