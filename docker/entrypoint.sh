#!/bin/bash
set -e

# Nettoyage preventif des verrous X11 residuels
rm -f /tmp/.X0-lock /tmp/.X11-unix/X0

# Demarrage du serveur X virtuel Xvfb avec support GLX
Xvfb :0 -screen 0 440x920x24 +extension GLX +render -noreset &

# Attente active que le serveur X virtuel soit initialise
for i in $(seq 1 20); do
    if [ -e /tmp/.X11-unix/X0 ]; then
        break
    fi
    sleep 0.2
done

# Demarrage d'un gestionnaire de fenetres minimaliste
openbox &

# Demarrage du serveur VNC x11vnc sans mot de passe sur le port 5900
x11vnc -display :0 -nopw -forever -shared -rfbport 5900 &

# Demarrage du pont web noVNC (websockify) sur le port 6080
websockify --web /usr/share/novnc 6080 localhost:5900 &

# Execution du simulateur mobile OSauce
exec /app/osauce-shell
