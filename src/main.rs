slint::slint! {
    import { Button } from "std-widgets.slint";

    export component MainWindow inherits Window {
        title: "OSauce Simulator";
        width: 390px;
        height: 844px;
        background: #0f172a;

        VerticalLayout {
            padding: 24px;
            spacing: 16px;

            // Barre de statut supérieure
            HorizontalLayout {
                Text {
                    text: "09:41";
                    color: #f8fafc;
                    font-size: 14px;
                    font-weight: 600;
                }
                Rectangle { horizontal-stretch: 1; }
                Text {
                    text: "100% 🔋";
                    color: #4ade80;
                    font-size: 14px;
                }
            }

            Rectangle { vertical-stretch: 1; }

            // Logo / Branding central
            Text {
                text: "OSauce";
                color: #38bdf8;
                font-size: 32px;
                font-weight: 800;
                horizontal-alignment: center;
            }

            Text {
                text: "Minimalist & Fast Mobile OS";
                color: #94a3b8;
                font-size: 14px;
                horizontal-alignment: center;
            }

            Rectangle { vertical-stretch: 1; }

            // Dock d'applications inférieur
            Rectangle {
                height: 72px;
                background: #1e293b;
                border-radius: 24px;

                HorizontalLayout {
                    alignment: space-around;
                    padding-left: 16px;
                    padding-right: 16px;

                    Text {
                        text: "🌐";
                        font-size: 28px;
                        vertical-alignment: center;
                    }
                    Text {
                        text: "📱";
                        font-size: 28px;
                        vertical-alignment: center;
                    }
                    Text {
                        text: "📦";
                        font-size: 28px;
                        vertical-alignment: center;
                    }
                    Text {
                        text: "⚙️";
                        font-size: 28px;
                        vertical-alignment: center;
                    }
                }
            }
        }
    }
}

fn main() -> Result<(), slint::PlatformError> {
    let main_window = MainWindow::new()?;
    main_window.run()
}
