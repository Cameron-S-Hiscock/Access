import { ExitBtn } from "./exit-btn.js";
import { RestartBtn } from "./restart-btn.js";
import { AccessPanel } from "../ui-templates/access-panel.js";

export class UtilitiesPanel extends AccessPanel {
    connectedCallback() {
        super.connectedCallback();
        this.style.setProperty("--panel-background", "var(--vintage-vessel)");
        const panel = this.shadowRoot.querySelector("section");
        panel.innerHTML = `
            <h3>Utilities</h3>
            <exit-btn></exit-btn>
            <restart-btn></restart-btn>
        `;
        panel.classList.add("utilities-panel");
    }
}
customElements.define("utilities-panel", UtilitiesPanel);