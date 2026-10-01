# Guide Syntaxe Slint - Projet OSauce

Ce document presente la syntaxe declarative du moteur graphique Slint utilise pour construire l'interface mobile OSauce.

---

## 1. Structure d'un Fichier Slint

Un fichier `.slint` definit des composants graphiques reutilisables ou des globals de styles :

```slint
// Importation de composants ou globals
import { Theme } from "styles/theme.slint";

// Declaration d'un composant exporte
export component MonComposant inherits Rectangle {
    // Proprietes publiques et privees
    in property <string> label: "Defaut";
    
    // Rendu visuel
    background: Theme.surface;
    border-radius: 12px;

    Text {
        text: root.label;
        color: Theme.text_primary;
        horizontal-alignment: center;
        vertical-alignment: center;
    }
}
```

---

## 2. Types de Donnees

Slint possede un systeme de typage statique adapte aux interfaces graphiques :
- `string` : chaine de caracteres textuelle (`"OSauce"`, `"09:41"`).
- `int`, `float` : entiers et nombres decimaux (`42`, `0.75`).
- `bool` : booleen (`true`, `false`).
- `color` : couleur (`#0f172a`, `rgb(15, 23, 42)`, `rgba(56, 189, 248, 0.2)`).
- `length` : distance physique ou logique en pixels (`16px`, `100%`).
- `duration` : duree temporelle (`150ms`, `1s`).
- `image` : ressource graphique bitmap ou SVG (`@image-url("assets/icon.png")`).

---

## 3. Proprietes (`property`)

La visibilite des proprietes determine qui peut les lire et les ecrire :
- `in property <type> nom` : entree modifiable uniquement par le parent ou depuis le code Rust.
- `out property <type> nom` : sortie calculee ou exportee par le composant.
- `in-out property <type> nom` : bidirectionnelle (lecture et ecriture partages).
- `private property <type> nom` : interne au composant (invisible de l'exterieur).

```slint
export component BatteryBadge inherits Rectangle {
    in property <int> level: 85;
    in property <bool> is_charging: false;
    out property <bool> is_low: root.level < 20;
    private property <color> bar_color: root.is_low ? #ef4444 : #4ade80;

    background: root.bar_color;
    width: 32px;
    height: 16px;
    border-radius: 4px;
}
```

---

## 4. Conteneurs de Mise en Page (Layouts)

Slint propose des layouts automatiques tres performants :

### A. VerticalLayout et HorizontalLayout
Alignent les elements verticalement ou horizontalement avec espacement et marge :
```slint
VerticalLayout {
    padding: 16px;
    spacing: 12px;
    alignment: center;

    Text { text: "Titre"; }
    Text { text: "Sous-titre"; }
}
```

### B. Espaceurs Flexibles
Pour pousser des elements aux extremites sans calcul complexe :
```slint
HorizontalLayout {
    Text { text: "Gauche"; }
    
    // Espaceur qui absorbe tout l'espace restant
    Rectangle { horizontal-stretch: 1; }
    
    Text { text: "Droite"; }
}
```

---

## 5. Interactions et Evenements Tactiles (`TouchArea`)

Pour rendre un element interactif (clic, survol, appui long) :

```slint
export component QuickButton inherits Rectangle {
    in property <string> text: "Valider";
    callback clicked();

    background: touch.pressed ? #0284c7 : (touch.has-hover ? #0ea5e9 : #38bdf8);
    border-radius: 12px;
    height: 48px;

    // Animation fluide au survol et a l'appui
    animate background { duration: 120ms; easing: ease-out; }

    Text {
        text: root.text;
        color: #ffffff;
        horizontal-alignment: center;
        vertical-alignment: center;
    }

    touch := TouchArea {
        clicked => {
            root.clicked();
        }
    }
}
```

---

## 6. Animations et Transitions Fluides

Slint permet d'animer n'importe quelle propriete numerique, dimension ou couleur avec `animate` :

```slint
export component SmoothCard inherits Rectangle {
    in property <bool> is_expanded: false;

    width: root.is_expanded ? 350px : 280px;
    height: root.is_expanded ? 180px : 64px;
    opacity: root.is_expanded ? 1.0 : 0.85;

    // Animations rapides avec courbe d'attenuation
    animate width, height {
        duration: 220ms;
        easing: cubic-bezier(0.16, 1.0, 0.3, 1.0);
    }
    animate opacity {
        duration: 150ms;
        easing: ease-out;
    }
}
```

---

## 7. Global Tokens (Design System)

Pour maintenir une source de verite unique (`SSOT`) de styles dans OSauce :

```slint
// Dans styles/theme.slint
export global Theme {
    out property <color> background: #0b0f19;
    out property <color> surface: #172033;
    out property <color> primary: #38bdf8;
    out property <length> radius_full: 9999px;
    out property <duration> anim_quick: 160ms;
}
```

---

## 8. Bonnes Pratiques Slint dans OSauce
1. **Separation Modulaire** : Un fichier par ecran ou bloc fonctionnel dans `ui/components/` ou `ui/views/`.
2. **Micro-Interactions** : Toujours coupler l'etat `touch.pressed` a une reaction visuelle immediate (changement d'opacite, leger deplacement en Y ou legere contraction) pour un ressenti tactile dynamique.
3. **Zero Emoji** : N'utiliser aucun emoji dans les textes, glyphes ou labels Slint. Utiliser des formes geometriques (`Rectangle`, `border-radius: 9999px`, `Path`) pour les icones.
