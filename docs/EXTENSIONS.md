# OSauce — Système de Thèmes & Extensions

Présentation de la modularité d'OpenSauce : personnalisation légère, sans serveur distant ni compte obligatoire.

---

## 1. Philosophie
La personnalisation dans OSauce respecte trois principes :
1. **100% Local** : Tout mod ou thème s'exécute directement sur l'appareil.
2. **Léger & Sans bloatware** : Pas de code obscur ou de scripts lourds.
3. **Sécurisé** : L'interface repose sur des composants déclaratifs immuables.

---

## 2. Niveaux de Personnalisation

### Niveau 1 : Thèmes Graphiques (JSON)
Les thèmes d'OSauce sont de simples structures JSON injectées à chaud dans `OSauceThemeEngine` :
```json
{
  "id": "theme_custom_nordic",
  "name": "Nordic Frost",
  "author": "Communauté",
  "version": "1.0",
  "colors": {
    "background": "#0F141C",
    "surface": "#1A2230",
    "border": "#2E3C54",
    "textPrimary": "#ECEFF4",
    "textSecondary": "#7B8EA6",
    "primaryAccent": "#88C0D0",
    "secondaryAccent": "#81A1C1",
    "elevated": "#243044"
  },
  "vibrancy": "SUBTLE"
}
```
L'utilisateur peut importer un thème par simple copier-coller de son code JSON dans l'application.

### Niveau 2 : Motifs d'Arrière-Plan & Shaders AGSL
Le fond interactif « The Sauce » (`FluidBackground.kt`) sépare le calcul mathématique de la grille de son rendu graphique :
* **Moteur par défaut** : 3 vortex d'ondes harmoniques en temps réel + gerbes particulaires tactiles.
* **Compatibilité Shaders AGSL** : Sur Android 13+, utilisation de `RenderEffect.createRuntimeShaderEffect` pour créer des variantes esthétiques (ondes liquides, grilles géométriques, distorsions discrètes).

### Niveau 3 : Export & Sauvegarde
Les sphères personnalisées, intentions et réglages peuvent être exportés dans un fichier JSON pour sauvegarde ou réinstallation sur un nouvel appareil.
