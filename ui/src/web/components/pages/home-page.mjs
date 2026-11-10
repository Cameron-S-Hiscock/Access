import { AccessPanel } from "./components/ui-templates/access-panel.js";
import { AccessBtn } from "./components/ui-templates/access-btn.js";
import { ExitBtn } from "./components/utility-components/exit-btn.js"
import { RestartBtn } from "./components/utility-components/restart-btn.js";

export class HomePage extends AccessPanel {
    connectedCallback() {
        super.connectedCallback();
        const page = this.shadowRoot.querySelector("section");
        page.innerHTML = `
            <h1>Access Home</h1>
        `
    }
}