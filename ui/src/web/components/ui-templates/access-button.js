export class AccessBtn extends HTMLElement {
    connectedCallback() {
        const shadow = this.attachShadow({ mode: "open" });
        shadow.innerHTML = "<button>Button<button>";
        shadow.querySelector("button").onclick = () => this.onClick();
    }
    onClick() {
        console.log("Clicked");
    }
    setLoading(state) {
        this.shadow.querySelector("button").disabled = state;
    }
}
customElements.define("access-btn", AccessBtn);
