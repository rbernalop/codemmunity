
export class RegisterTabObject {
    constructor(cy) {
        this.cy = cy;
    }

    makeRegistration(firstName, lastName, userName, email, password, birthDate) {
        cy.get('input[id="name"]').type(firstName)
        cy.get('input[id="surname"]').type(lastName)
        cy.get('input[id="username"]').type(userName)
        cy.get('input[id="email"]').type(email)
        cy.get('input[id="password"]').type(password)
        cy.get('input[id="confirmPassword"]').type(password)
        cy.get('input[id="birthDate"]').click()
        cy.get('input[id="birthDate"]').type(birthDate)
        cy.get('button[type="submit"]').click()
    }

    getRegisterTab() {
        return cy.get('div[data-node-key="register"]')
    }
}