# Étape 1 : Construction du binaire Rust
FROM rust:slim-bookworm AS builder

RUN apt-get update && apt-get install -y --no-install-recommends \
    pkg-config \
    libfontconfig1-dev \
    libxkbcommon-dev \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY Cargo.toml Cargo.lock build.rs ./
COPY ui ./ui
COPY src ./src

RUN cargo build --release --bin osauce-shell

# Étape 2 : Image d'exécution légère avec noVNC
FROM debian:bookworm-slim

ENV DEBIAN_FRONTEND=noninteractive
ENV DISPLAY=:0

RUN apt-get update && apt-get install -y --no-install-recommends \
    xvfb \
    openbox \
    x11vnc \
    novnc \
    websockify \
    libfontconfig1 \
    libxkbcommon0 \
    libxkbcommon-x11-0 \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /root/.config/openbox /app
COPY --from=builder /app/target/release/osauce-shell /app/osauce-shell
COPY docker/entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

# Redirection index.html vers vnc.html pour acces direct sur http://localhost:6080
RUN ln -sf /usr/share/novnc/vnc.html /usr/share/novnc/index.html

EXPOSE 6080
WORKDIR /app
ENTRYPOINT ["/app/entrypoint.sh"]
