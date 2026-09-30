use osauce::MainWindow;
use slint::ComponentHandle;

/// Application entry point for the OSauce Shell Simulator.
fn main() -> Result<(), slint::PlatformError> {
    println!("[OSauce] Initializing OSauce Shell Simulator...");
    println!("[OSauce] Resolution: 390x844 (Mobile portrait)");

    // Detect and log the active display server on Linux / WSLg
    #[cfg(target_os = "linux")]
    {
        if std::path::Path::new("/mnt/wslg/runtime-dir/wayland-0").exists() {
            println!("[OSauce] Display Server: Wayland (WSLg)");
        } else {
            let disp = std::env::var("DISPLAY").unwrap_or_else(|_| ":0".to_string());
            println!("[OSauce] Display Server: X11 ({})", disp);
        }
    }

    // Initialize and run the main window event loop
    let main_window = MainWindow::new()?;
    println!("[OSauce] Window created successfully. Rendering started.");
    println!("[OSauce] Press Ctrl+C or close the window to exit.");
    main_window.run()
}

