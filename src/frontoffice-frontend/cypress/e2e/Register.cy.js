import {HomePageObject} from "./page-objects/HomePageObject";
import {RegisterTabObject} from "./page-objects/RegisterTabObject";
import {randomUser} from "./object-mother/UserMother";
import {LoginTabObject} from "./page-objects/LoginTabObject";
import {tabActiveClass} from "./constants/cssClasses";

describe('template spec', () => {
  beforeEach(() => {
    cy.visit(Cypress.env('FRONTOFFICE_URL'))
  });

  it('Should have Welcome message', () => {
    const homePage = new HomePageObject(cy)
    const registerTab = new RegisterTabObject(cy)
    const loginTab = new LoginTabObject(cy)

    const user = randomUser()

    const pageWelcomeMessage = homePage.getPageWelcomeMessage()
    pageWelcomeMessage.should('contain', 'Welcome to the')
    pageWelcomeMessage.should('contain', 'new programming experience')
    homePage.openRegisterTab()

    registerTab.getRegisterTab().should('have.class', tabActiveClass)
    registerTab.makeRegistration(user.firstName, user.lastName, user.userName, user.email, user.password, user.birthDate)

    loginTab.getLoginTab().should('have.class', tabActiveClass)
  })
})