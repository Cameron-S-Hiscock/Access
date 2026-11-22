class AcsButton extends HTMLButtonElement {
    connectedCallback() {
        this.classList.add("acs-button");
        this.addEventListener("click", this.#onClick);
    }

    disconnectedCallback() {
        this.removeEventListener("click", this.#onClick);
    }

    #onClick = () => this.dispatchEvent(new CustomEvent("acs-press", { bubbles: true }));
}
customElements.define("acs-button", AcsButton, { extends: "button" });
