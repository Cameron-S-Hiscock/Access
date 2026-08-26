import { AccessBtn } from "../ui-templates/access-btn.js";

class ExitBtn extends AccessBtn {
    connectedCallback() {
        super.connectedCallback();
        const btn = this.shadowRoot.querySelector("button")
        btn.classList.add("exit");
    }
    onClick() {
        super.onClick();
        this.setLoading(true);
        console.log("Exiting Access");
    }
}
customElements.define("exit-btn", ExitBtn);
