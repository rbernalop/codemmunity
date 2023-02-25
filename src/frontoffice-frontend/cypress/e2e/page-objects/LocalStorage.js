
export class LocalStorage {
    constructor(cy, url) {
        this.cy = cy;
        this.url = url;
    }

    get(item) {
        return this.cy.window().then((win) => {
            return win.localStorage.getItem(item);
        });
    }

    isEmpty() {
        return this.cy.window().then((win) => {
            return win.localStorage.length === 0;
        });
    }

}