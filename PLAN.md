Current State

- Working: Gradle multi-module Kotlin build (JVM 25, Compose Multiplatform 1.11.1), IOStream framework skeletons (Data/Task/Message/Pipeline/Ports), Scheduler/Launcher/Registry, FFM↔Rust FFI bridge, Node.js subprocess bridge.
- Smoke-test only: Rust lib exports just systems_add/systems_log/free_str; the real engine (pipeline.rs, linker.rs, runner.rs) is barely started.
- Empty: api/, cagis/ modules; DatabaseBridge.kt, DataBridge.kt, Router.kt; all JS in web/.
- Stubs: UI screens/theme/components are TODO placeholders.
- No tests (test dirs exist but no files).

Roadmap

Phase 1 — Solidify IOStream foundation (core)
Complete the Data/Task/Message → Pipeline → Port flow so messages actually transit between ports. Add unit tests per model (test dirs already scaffolded). Highest priority: this is the whole project's premise.

Phase 2 — Real Rust engine + FFI (systems)
Implement pipeline creation, message transmit, linking, and the runner loop in Rust; export real symbols; replace smoke-test FFI in SystemsBridge; add Kotlin integration tests through FFM.

Phase 3 — Working core loop (core/app)
Wire Controller/Scheduler/Launcher/Registry into a real task-execution cycle; replace the placeholder infinite loop in App.kt. Demonstrate an end-to-end task: scheduled → launched → message delivered.

Phase 4 — UI layer (ui)
Fill the stubbed screens/theme/components; bind Compose state to controller state; push real updates via pushUI.

Phase 5 — Data + Docker (data)
Implement DatabaseBridge (PostgreSQL), Router (network), and the Docker port for containers.

Phase 6 — Plugin system (api/web)
Define the public API surface in api/; host JS plugins via GraalVM Polyglot (dependency is already declared but unused); flesh out web/ JS.

Phase 7 — CAGIS (cagis)
ML/AI research/integration — intentionally last, exploratory, low risk to the rest.

Phase 8 — Packaging/QA
Docker build (Dockerfile exists), distributable, CI, test coverage.

Suggested start: Phase 1 — the IOStream pipeline is the foundation everything else hangs off.
