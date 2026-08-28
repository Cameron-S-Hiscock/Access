import { AccessBtn } from "../ui-templates/access-btn.js";

export class ExitBtn extends AccessBtn {
    connectedCallback() {
        super.connectedCallback();
        this.style.setProperty("--btn-background-color", "var(--cerise)");
        this.style.setProperty("--btn-color", "var(--onyx)");
        const btn = this.shadowRoot.querySelector("button");
        btn.classList.add("exit");
    }
    onClick() {
        super.onClick();
        this.setLoading(true);
        window.cefQuery({
            request: "exit-app",
            onSuccess: () => {},
            onFailure: () => {},
        });
    }
}
customElements.define("exit-btn", ExitBtn);
