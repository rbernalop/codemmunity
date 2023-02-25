import {HomePageObject} from "./page-objects/HomePageObject";

describe('template spec', () => {
  beforeEach(() => {
    cy.visit(Cypress.env('FRONTOFFICE_URL'))
  });
  it('Should have Welcome message', () => {
    const homePage = new HomePageObject(cy)

    const pageWelcomeMessage = homePage.getPageWelcomeMessage()
    pageWelcomeMessage.should('contain', 'Welcome to the')
    pageWelcomeMessage.should('contain', 'new programming experience')
  })
})