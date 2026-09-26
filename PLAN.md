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






Immediate Fix Plan — IOStream + RSE Stabilization

1. Fix startup sequencing and await semantics
   - Replace fire-and-forget launch patterns with awaited initialization in UICoreBridgeTest, UICoreBridge.init(), Controller.init(), and Composer.init().
   - Use coroutineScope, async/await, or join() so setup completes before send/receive begins.
   - Ensure IOStream.init() is called before any bridge message is sent.

2. Correct the IOStream lifecycle
   - Build all ports inside IOStream.init() exactly once.
   - Add a valid ready/open phase for each Port before allowing message flow.
   - Make author-target registration idempotent and consistent in IOStreamAuthorTable.
   - Guard receive/send against empty queues and missing ports.

3. Fix port and pipeline state transitions
   - Define a real state machine: PENDING -> QUEUED -> SENDING -> SENT -> RECEIVED.
   - Set port state to OPEN when the port is ready.
   - Replace impossible require() checks with guarded logic that handles invalid states cleanly.
   - Ensure Pipeline.deliver() only moves a queued message when the destination is actually ready.

4. Correct message routing semantics
   - Change the UICoreBridge cache loop so it drains messages instead of immediately re-queuing them.
   - Make a single clear rule for where each message is stored and forwarded.
   - Ensure Message receive/send operations work against a valid queue state, not stale or uninitialized ports.

5. Fix the RSE task lifecycle
   - Implement a real worker lifecycle for ProcessWorker and Worker rather than no-op start/join methods.
   - Ensure registry, schedule, and execution phases each happen once, in order.
   - Prevent re-adding the same task to the same queue without explicit scheduling logic.
   - Use a consistent task state flow: REGISTERED -> SCHEDULED -> RUNNING -> COMPLETED.

6. Add focused validation tests
   - IOStream.init() creates ports and routes messages.
   - Port.receive() handles empty queues without crashing.
   - Message state transitions progress correctly.
   - Task state transitions progress correctly.
   - Bridge tests validate actual delivery as a full-system check.

7. Verify in small increments
   - Fix startup race first.
   - Then validate IOStream setup.
   - Then validate message delivery.
   - Then validate RSE execution.
   - Re-run the bridge test after each patch before broadening scope.

This is the next critical milestone: the project cannot progress reliably until the IOStream and RSE layers behave deterministically and are proven by tests.
