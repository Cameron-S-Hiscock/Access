export class AccessBtn extends HTMLElement {
    connectedCallback() {
        this.shadow = this.attachShadow({ mode: "open" });
        this.shadow.innerHTML = `
            <style>
                button {
                    background-color: var(--btn-background-color, var(--loyal-blue));
                    color: var(--btn-color, var(--vintage-vessel));
                    text-align: var(--btn-text-align, center);
                    cursor: var(--btn-cursor, pointer);
                    margin: var(--btn-margin, 4px 2px);
                    padding: var(--btn-padding, 8px 16px);
                    height: var(--btn-height, 32px);
                    min-width: var(--btn-min-width, 64px);
                    font-size: var(--btn-font-size, 16px);
                    font-weight: var(--btn-font-weight, 900);
                    border: var(--btn-border, 3px solid var(--onyx));
                    border-radius: var(--btn-border-radius, 12px);
                    outline: var(--btn-outline, none);
                    display: var(--btn-display, flex);
                    align-items: var(--btn-align-items, center);
                }
            </style>
            <button>
                <slot></slot>
            </button>
        `;
        this.shadow.querySelector("button").onclick = () => this.onClick();
    }
    onClick() {
        this.setLoading(true);
        window.cefQuery({
            request: "",
            onSuccess: () => {},
            onFailure: () => {},
        });
    }
    setLoading(state) {
        this.shadow.querySelector("button").disabled = state;
    }
}
customElements.define("access-btn", AccessBtn);
