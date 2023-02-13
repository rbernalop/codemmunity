describe('template spec', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000')
  });
  it('Should have Welcome message', () => {
    cy.get('h1[class="title"]').should('contain', 'Welcome to the')
    cy.get('h1[class="title"]').should('contain', 'new programming experience')
  })
})