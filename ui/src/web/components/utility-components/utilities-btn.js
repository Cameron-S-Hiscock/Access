import { AccessBtn } from "../ui-templates/access-btn.js";

export class UtilitiesBtn extends AccessBtn {
    connectedCallback() {
        super.connectedCallback();
        this.style.setProperty("--btn-background-color", "var(--onyx)");
        this.style.setProperty("--btn-color", "var(--seashell)");
        const btn = this.shadowRoot.querySelector("button");
        btn.classList.add("utilities");
    }
    onClick() {
        this.setLoading(true);
        window.cefQuery({
            request: "open-utilities",
            onSuccess: () => {},
            onFailure: () => {},
        });
    }
}
customElements.define("utilities-btn", UtilitiesBtn);