use chrono::{Datelike, Local, Timelike};
use osauce::MainWindow;
use slint::ComponentHandle;
use std::fs;
use std::path::Path;

/// Reads battery capacity and charging status from Linux sysfs power supply (if available).
fn read_hardware_battery() -> (i32, bool) {
    // Standard Linux / postmarketOS / Android battery paths
    let candidate_paths = [
        "/sys/class/power_supply/BAT0",
        "/sys/class/power_supply/BAT1",
        "/sys/class/power_supply/battery",
    ];

    for base in &candidate_paths {
        let cap_path = format!("{}/capacity", base);
        let status_path = format!("{}/status", base);

        if Path::new(&cap_path).exists() {
            if let Ok(content) = fs::read_to_string(&cap_path) {
                if let Ok(cap) = content.trim().parse::<i32>() {
                    let is_charging = fs::read_to_string(&status_path)
                        .map(|s| s.trim().eq_ignore_ascii_case("charging"))
                        .unwrap_or(false);
                    return (cap.clamp(0, 100), is_charging);
                }
            }
        }
    }

    // Default mobile simulation value when running on a desktop machine without battery
    (88, false)
}

/// Formats the current local date into an elegant French system string.
fn get_localized_system_date() -> String {
    let now = Local::now();

    let day_name = match now.weekday() {
        chrono::Weekday::Mon => "LUNDI",
        chrono::Weekday::Tue => "MARDI",
        chrono::Weekday::Wed => "MERCREDI",
        chrono::Weekday::Thu => "JEUDI",
        chrono::Weekday::Fri => "VENDREDI",
        chrono::Weekday::Sat => "SAMEDI",
        chrono::Weekday::Sun => "DIMANCHE",
    };

    let month_name = match now.month() {
        1 => "JANVIER",
        2 => "FEVRIER",
        3 => "MARS",
        4 => "AVRIL",
        5 => "MAI",
        6 => "JUIN",
        7 => "JUILLET",
        8 => "AOUT",
        9 => "SEPTEMBRE",
        10 => "OCTOBRE",
        11 => "NOVEMBRE",
        12 => "DECEMBRE",
        _ => "MOIS",
    };

    format!("{} {} {}", day_name, now.day(), month_name)
}

/// Application entry point for the OSauce Shell Simulator.
fn main() -> Result<(), slint::PlatformError> {
    println!("[OSauce] Initializing OSauce Shell Simulator...");
    println!("[OSauce] Resolution: 390x844 (Mobile portrait)");

    // Detect and log active display server
    #[cfg(target_os = "linux")]
    {
        if Path::new("/mnt/wslg/runtime-dir/wayland-0").exists() {
            println!("[OSauce] Display Server: Wayland (WSLg)");
        } else {
            let disp = std::env::var("DISPLAY").unwrap_or_else(|_| ":0".to_string());
            println!("[OSauce] Display Server: X11 ({})", disp);
        }
    }

    // Create the main Slint window
    let main_window = MainWindow::new()?;

    // Sync initial hardware state
    let now = Local::now();
    main_window.set_system_hours(now.hour() as i32);
    main_window.set_system_minutes(now.minute() as i32);
    main_window.set_system_seconds(now.second() as i32);
    main_window.set_system_date(get_localized_system_date().into());

    let (battery_level, is_charging) = read_hardware_battery();
    main_window.set_system_battery(battery_level);
    main_window.set_system_is_charging(is_charging);

    println!(
        "[OSauce::Telemetry] Real OS Time initialized: {:02}:{:02}:{:02} | Date: {}",
        now.hour(),
        now.minute(),
        now.second(),
        get_localized_system_date()
    );
    println!(
        "[OSauce::Telemetry] Battery: {}% (Charging: {})",
        battery_level, is_charging
    );

    // Periodic telemetry loop (every 500ms for sub-second precision)
    let telemetry_timer = slint::Timer::default();
    let weak_window = main_window.as_weak();

    telemetry_timer.start(
        slint::TimerMode::Repeated,
        std::time::Duration::from_millis(500),
        move || {
            if let Some(window) = weak_window.upgrade() {
                let now = Local::now();
                window.set_system_hours(now.hour() as i32);
                window.set_system_minutes(now.minute() as i32);
                window.set_system_seconds(now.second() as i32);
                window.set_system_date(get_localized_system_date().into());

                let (batt, chg) = read_hardware_battery();
                window.set_system_battery(batt);
                window.set_system_is_charging(chg);
            }
        },
    );

    // Callbacks: Handle user interactions from Slint in Rust
    main_window.on_request_unlock({
        let weak = main_window.as_weak();
        move || {
            println!("[OSauce::Hardware] User touch authentication confirmed. Unlocking mobile shell.");
            if let Some(window) = weak.upgrade() {
                window.set_is_unlocked(true);
            }
        }
    });

    main_window.on_request_lock({
        let weak = main_window.as_weak();
        move || {
            println!("[OSauce::Hardware] Lock gesture triggered. Locking system screen.");
            if let Some(window) = weak.upgrade() {
                window.set_is_unlocked(false);
            }
        }
    });

    println!("[OSauce] Rendering started. Press Ctrl+C or close window to exit.");
    main_window.run()
}
