import { AccessBtn } from "../ui-templates/access-btn.js";

export class RestartBtn extends AccessBtn {
    connectedCallback() {
        super.connectedCallback();
        this.style.setProperty("--btn-background-color", "var(--onyx)");
        const btn = this.shadowRoot.querySelector("button");
        btn.classList.add("restart");
    }
    onClick() {
        super.onClick();
        this.setLoading(true);
        window.cefQuery({
            request: "restart-app",
            onSuccess: () => {},
            onFailure: () => {},
        });
    }
}
customElements.define("restart-btn", RestartBtn);
