import {HomePageObject} from "./page-objects/HomePageObject";
import {LoginTabObject} from "./page-objects/LoginTabObject";
import {RegisterTabObject} from "./page-objects/RegisterTabObject";
import {LocalStorage} from "./page-objects/LocalStorage";
import {randomUser} from "./object-mother/UserMother";
import {tabActiveClass} from "./constants/cssClasses";


describe('template spec', () => {
  beforeEach(() => {
    cy.visit(Cypress.env('FRONTOFFICE_URL'))
  });

  it('passes', () => {
    const homePage = new HomePageObject(cy)
    const registerTab = new RegisterTabObject(cy)
    const loginTab = new LoginTabObject(cy)
    const localStorage = new LocalStorage(cy, Cypress.env('FRONTOFFICE_URL'))

    const user = randomUser()

    const pageWelcomeMessage = homePage.getPageWelcomeMessage()
    pageWelcomeMessage.should('contain', 'Welcome to the')
    pageWelcomeMessage.should('contain', 'new programming experience')
    homePage.openRegisterTab()

    registerTab.getRegisterTab().should('have.class', tabActiveClass)
    registerTab.makeRegistration(user.firstName, user.lastName, user.userName, user.email, user.password, user.birthDate)

    loginTab.getLoginTab().should('have.class', tabActiveClass)
    loginTab.makeLogin(user.userName, user.password)

    homePage.getLoggedWelcomeMessage().should('contain', `Welcome, ${user.userName}`)
    localStorage.get('token').should('not.be.null')
    localStorage.get('username').should('be.equal', user.userName)
    localStorage.get('email').should('be.equal', user.email)
    localStorage.get('id').should('be.a', 'string')

    homePage.logout()
    homePage.getLoggedWelcomeMessage().should('not.exist')
    localStorage.isEmpty().should('be.true')
  })
})