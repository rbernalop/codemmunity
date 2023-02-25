describe('template spec', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000')
  });
  it('Should have Welcome message', () => {
    cy.get('h1[class="title"]').should('contain', 'Welcome to the')
    cy.get('h1[class="title"]').should('contain', 'new programming experience')

    cy.get('button[id="register-btn"]').click()
    cy.get('div[data-node-key="register"]').should('have.class', 'ant-tabs-tab-active')
    cy.get('input[id="name"]').type('name')
    cy.get('input[id="surname"]').type('surname')
    cy.get('input[id="username"]').type('username2')
    cy.get('input[id="email"]').type('email2@mail.com')
    cy.get('input[id="password"]').type('password')
    cy.get('input[id="confirmPassword"]').type('password')
    cy.get('input[id="birthDate"]').click()
    cy.get('input[id="birthDate"]').type('2000-01-01')
    cy.get('button[type="submit"]').click()

    cy.get('div[data-node-key="login"]').should('have.class', 'ant-tabs-tab-active')
  })
})