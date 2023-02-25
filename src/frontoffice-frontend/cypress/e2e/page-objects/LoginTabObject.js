
export class LoginTabObject {
    constructor(cy) {
        this.cy = cy;
    }

    makeLogin(userName, password) {
        cy.get('input[id="username-login"]').type(userName)
        cy.get('input[id="password-login"]').type(password)
        cy.get('button[id="login-submit"]').click()
    }

    getLoginTab() {
        return cy.get('div[data-node-key="login"]')
    }

}