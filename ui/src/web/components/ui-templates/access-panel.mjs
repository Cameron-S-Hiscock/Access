export class AccessPanel extends HTMLElement {
    connectedCallback() {
        this.shadow = this.attachShadow({ mode: "open" });
        this.shadow.innerHTML = `
            <style>
                section {
                    background: var(--panel-background, var(--background));
                    color: var(--panel-color, var(--seashell));
                }
            </style>
            <section>
                <slot></slot>
            </section>
        `;
    }
}
customElements.define("access-panel", AccessPanel);