#!/bin/bash
set -e

# Demarrage du serveur X virtuel Xvfb (format portrait smartphone avec marges)
Xvfb :0 -screen 0 440x920x24 &
sleep 1

# Demarrage d'un gestionnaire de fenetres minimaliste
openbox &

# Demarrage du serveur VNC x11vnc sans mot de passe sur le port 5900
x11vnc -display :0 -nopw -forever -shared -rfbport 5900 &

# Demarrage du pont web noVNC (websockify) sur le port 6080
websockify --web /usr/share/novnc 6080 localhost:5900 &

# Execution du simulateur mobile OSauce
exec /app/osauce-shell
