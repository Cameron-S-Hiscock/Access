FROM ubuntu:latest

WORKDIR /usr/src

RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential git curl wget unzip zip ca-certificates \
    openjdk-25-jdk \
    gcc-mingw-w64 \
    dpkg rpm \
    clang \
    && rm -rf /var/lib/apt/lists/*

# Kotlin (no apt package — install via SDKMAN)
RUN curl -s "https://get.sdkman.io" | bash && \
    bash -c "source \$HOME/.sdkman/bin/sdkman-init.sh && sdk install kotlin && sdk install gradle"
ENV PATH="/root/.sdkman/candidates/kotlin/current/bin:/root/.sdkman/candidates/gradle/current/bin:${PATH}"

# Rust via rustup (not in apt as current stable)
RUN curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh -s -- -y
ENV PATH="/root/.cargo/bin:${PATH}"
RUN rustup target add x86_64-unknown-linux-gnu aarch64-unknown-linux-gnu
RUN cargo install cross --git https://github.com/cross-rs/cross

COPY . .
RUN chmod +x gradlew

CMD ["./gradlew", "clean", "build", "test"]
