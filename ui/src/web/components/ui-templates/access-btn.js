export class AccessBtn extends HTMLElement {
    connectedCallback() {
        this.shadow = this.attachShadow({ mode: "open" });
        this.shadow.innerHTML = `
            <style>
                background-color: #01455E;
                color: #94B2A6;
                text-align: center;
            </style>
            <button>
                <slot></slot>
            </button>
        `;
        this.shadow.querySelector("button").onclick = () => this.onClick();
    }
    onClick() {
        console.log("Clicked");
    }
    setLoading(state) {
        this.shadow.querySelector("button").disabled = state;
    }
}
customElements.define("access-btn", AccessBtn);
