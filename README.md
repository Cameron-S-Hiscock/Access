# Access

Access is a showcase of my skills as a programmer and engineer.<br>
Access provides a framework for building desktop apps and plugins without needing to rewrite or reimplement common needs and modules.


I am programming this mainly in NeoVim on Arch Linux with x86_64 architecture.

**Tech Stack**

Languages: Kotlin (JVM), Java, Rust, C, JavaScript (plugins)
UI: Compose Multiplatform (Desktop, Material3)
Build: Gradle 9.5.1 (Kotlin DSL), Cargo
Libraries: Kotlinx, GraalVM Polyglot, Guava
FFI: Project Panama (FFM), Rust cdylib native libs
Data: PostgreSQL, SQL
Runtime: JVM 25, Docker

**Modules**
App: Entry-point, Kotlin
Core: Main backend and business logic, Kotlin
Systems: Low-level control and high-compute tasks, Rust
API: Connection between framework and the apps built on top and connecting plugins, Kotlin
CAGIS: ML/AI research and integration, language unknown
Data: Connection to PostgreSQL database and data analytics tools, Kotlin, SQL
UI: Frontend the end user sees, Kotlin
Utils: Library of tools used in many different modules, Kotlin

**Structure**
Information and actions are wrapped in Data/Task objects and are passed around the framework along iostreams in Message objects. Iostreams contain two ports to connect to the framework, apps, plugins, or Docker containers that the user/developer needs to use, and two one-way pipelines that connect those ports and regulate the flow of Message objects.

IOStream: Kotlin
Plugins: JavaScript
Security: Rust
FFI: C
UI: Kotlin

**Languages Used**
Kotlin
Java
Rust

**Tools Used**
Gradle
Git
Cargo
PostgreSQL
Docker
Project Panama (FFM)
