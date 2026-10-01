# Guide Syntaxe Rust - Projet OSauce

Ce document presente les fondamentaux de la syntaxe du langage Rust de facon simple, directe et appliquee a l'environnement de developpement OSauce.

---

## 1. Structure d'un Projet Rust

Dans OSauce, le projet Rust s'organise ainsi :
- `Cargo.toml` : fichier de configuration du projet, des dependances (`slint`, `wasm-bindgen`) et des options de compilation.
- `build.rs` : script execute avant la compilation pour compiler les fichiers d'interface Slint (`ui/shell.slint`).
- `src/main.rs` : point d'entree de l'application desktop (simulateur).
- `src/lib.rs` : point d'entree pour l'export WebAssembly (Wasm) ou modules partages.

```toml
# Extrait de Cargo.toml
[package]
name = "osauce"
version = "0.1.0"
edition = "2021"

[dependencies]
slint = "1.9"

[build-dependencies]
slint-build = "1.9"
```

---

## 2. Variables et Typage Fort

En Rust, les variables sont immutables par defaut. Pour permettre la modification d'une variable, le mot-cle `mut` est obligatoire.

```rust
// Immuable par defaut (valeur constante dans ce scope)
let system_name = "OSauce";

// Mutable : valeur modifiable
let mut current_battery: i32 = 100;
current_battery -= 5;

// Types primitifs courants
let is_locked: bool = true;          // Booleen
let screen_width: u32 = 390;         // Entier non signe 32 bits
let volume_level: f32 = 0.85;        // Nombre flottant
let user_name: String = String::from("Celestin"); // Chaine possedee
let title_slice: &str = "Accueil";   // Vue immuable sur une chaine
```

---

## 3. Structures et Methodes (`struct` et `impl`)

Une `struct` permet de modeliser un composant ou un etat du systeme. Le bloc `impl` regroupe ses methodes.

```rust
/// Represente l'etat de verrouillage et de l'horloge
pub struct LockState {
    pub is_locked: bool,
    pub battery_percent: i32,
    pub current_time: String,
}

impl LockState {
    /// Constructeur standard
    pub fn new() -> Self {
        Self {
            is_locked: true,
            battery_percent: 100,
            current_time: String::from("09:41"),
        }
    }

    /// Deverrouille l'appareil
    pub fn unlock(&mut self) {
        self.is_locked = false;
        println!("[OSauce] Ecran deverrouille.");
    }
}
```

---

## 4. Enumerations et Pattern Matching (`enum` et `match`)

Les enums en Rust sont tres expressives et permettent d'eviter les etats invalides.

```rust
/// Modes d'affichage possibles pour l'horloge d'accueil
pub enum ClockStyle {
    MinimalHero,
    StackedDigital,
    CardWidget,
}

/// Aiguillage selon le mode choisi
fn log_clock_mode(style: ClockStyle) {
    match style {
        ClockStyle::MinimalHero => {
            println!("Affichage classique grand format");
        }
        ClockStyle::StackedDigital => {
            println!("Affichage numerique superpose");
        }
        ClockStyle::CardWidget => {
            println!("Affichage compact dans un widget");
        }
    }
}
```

---

## 5. Gestion des Erreurs (`Option` et `Result`)

Rust n'a pas de valeur `null` ni d'exceptions non controlees. Il utilise `Option<T>` et `Result<T, E>`.

```rust
// Result : succes (Ok) ou erreur (Err)
fn parse_battery_level(text: &str) -> Result<i32, std::num::ParseIntError> {
    text.parse::<i32>()
}

// Option : presence (Some) ou absence (None)
fn find_app(name: &str) -> Option<&str> {
    if name == "browser" {
        Some("Navigateur Web OSauce")
    } else {
        None
    }
}

// Utilisation avec if let
if let Some(app) = find_app("browser") {
    println!("Application trouvee : {}", app);
}
```

---

## 6. Connexion Rust et Slint (Exemple Applicatif OSauce)

Dans OSauce, le binaire Rust pilote l'interface Slint via les wrappers generes par `build.rs`.

```rust
use osauce::MainWindow;
use slint::ComponentHandle;

fn main() -> Result<(), slint::PlatformError> {
    // 1. Instanciation de la fenetre Slint
    let main_window = MainWindow::new()?;

    // 2. Interaction avec les proprietes declarees dans Slint
    // (Exemple : ecoute d'un callback emis par l'UI)
    let handle = main_window.as_weak();
    main_window.on_unlock_requested(move || {
        if let Some(window) = handle.upgrade() {
            println!("[OSauce] Signal de deverrouillage recu depuis Slint !");
            window.set_is_locked(false);
        }
    });

    // 3. Demarrage de la boucle d'evenements
    main_window.run()
}
```

---

## 7. Bonnes Pratiques OSauce
1. **Simplicite** : Eviter les abstractions complexes (`Arc<Mutex<...>>` inutiles tant qu'un simple passage de reference suffit).
2. **Gestion des handles Slint** : Toujours utiliser `as_weak()` avant de capturer une reference de fenetre dans une closure `move`.
3. **Logs clairs** : Utiliser des prefixes `[OSauce]` explicites pour faciliter le debogage en direct.
