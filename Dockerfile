FROM archlinux:latest

WORKDIR /usr/src

# Base system update + essential build tools
RUN pacman -Syu --noconfirm
RUN pacman -S --noconfirm base-devel git curl wget unzip

# Java / Kotlin (JDK, includes jlink + jpackage)
RUN pacman -S --noconfirm jdk-openjdk
RUN pacman -S --noconfirm kotlin

# Gradle
RUN pacman -S --noconfirm gradle

# Rust toolchain (rustup preferred over pacman's rust for cross-compilation/target management)
RUN pacman -S --noconfirm rustup
RUN rustup default stable
RUN rustup target add x86_64-unknown-linux-gnu aarch64-unknown-linux-gnu

# Rust cross-compilation support
RUN pacman -S --noconfirm mingw-w64-gcc

# cross (Rust cross-compilation tool, installed via cargo)
RUN cargo install cross --git https://github.com/cross-rs/cross

# Packaging dependencies for jpackage output (Linux .deb/.rpm)
RUN pacman -S --noconfirm dpkg rpm-tools

CMD [ "./gradlew", "clean", "build", "test", "run" ]
