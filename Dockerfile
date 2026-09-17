FROM debian:bookworm-slim

WORKDIR /usr/src

RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential git curl wget unzip zip ca-certificates gnupg \
    gcc-mingw-w64 dpkg rpm clang \
    && rm -rf /var/lib/apt/lists/*

# Debian stable has no openjdk-25 package; install Temurin JDK 25
RUN curl -fsSL https://packages.adoptium.net/artifactory/api/gpg/key/public \
        | gpg --dearmor -o /etc/apt/trusted.gpg.d/adoptium.gpg \
    && echo "deb https://packages.adoptium.net/artifactory/deb bookworm main" \
        > /etc/apt/sources.list.d/adoptium.list \
    && apt-get update && apt-get install -y --no-install-recommends temurin-25-jdk \
    && rm -rf /var/lib/apt/lists/*
ENV JAVA_HOME=/usr/lib/jvm/temurin-25-jdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Kotlin + Gradle via SDKMAN
RUN curl -s "https://get.sdkman.io" | bash && \
    bash -c "source \$HOME/.sdkman/bin/sdkman-init.sh && sdk install kotlin && sdk install gradle"
ENV PATH="/root/.sdkman/candidates/kotlin/current/bin:/root/.sdkman/candidates/gradle/current/bin:${PATH}"

# Rust via rustup
RUN curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh -s -- -y
ENV PATH="/root/.cargo/bin:${PATH}"
RUN rustup target add x86_64-unknown-linux-gnu aarch64-unknown-linux-gnu
RUN cargo install cross --git https://github.com/cross-rs/cross

COPY . .
RUN chmod +x gradlew

CMD ["./gradlew", "clean", "build", "test"]