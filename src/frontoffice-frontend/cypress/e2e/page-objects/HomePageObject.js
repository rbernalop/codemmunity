
export class HomePageObject {
    constructor(cy) {
        this.cy = cy;
    }

    getPageWelcomeMessage() {
        return cy.get('h1[class="title"]')
    }

    getLoggedWelcomeMessage() {
        return cy.get('span[id="welcome-msg"]')
    }

    openRegisterTab() {
        cy.get('button[id="register-btn"]').click()
    }

    openLoginTab() {
        cy.get('button[id="login-btn"]').click()
    }

    logout() {
        cy.get('button[id="logout-btn"]').click()
    }

}