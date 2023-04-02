import {HomePageObject} from "./page-objects/HomePageObject";
import {LocalStorage} from "./page-objects/LocalStorage";
import {registeredUser} from "./object-mother/UserMother";
import {tabActiveClass} from "./constants/cssClasses";
import {LoginTabObject} from "./page-objects/LoginTabObject";
import {MyScriptListPageObject} from "./page-objects/MyScriptListPageObject";

describe('template spec', () => {
    beforeEach(() => {
        cy.visit(Cypress.env('FRONTOFFICE_URL'))
    });
    it('Should Script List when user is authenticated', () => {
        const homePage = new HomePageObject(cy)
        const loginTab = new LoginTabObject(cy)
        const myScriptListPage = new MyScriptListPageObject(cy)
        const localStorage = new LocalStorage(cy, Cypress.env('FRONTOFFICE_URL'))

        const user = registeredUser()

        const pageWelcomeMessage = homePage.getPageWelcomeMessage()
        pageWelcomeMessage.should('contain', 'Welcome to the')
        pageWelcomeMessage.should('contain', 'new programming experience')
        homePage.goToUserScriptsPage()

        loginTab.getLoginTab().should('have.class', tabActiveClass)
        loginTab.makeLogin(user.userName, user.password)

        homePage.getLoggedWelcomeMessage().should('contain', `Welcome, ${user.userName}`)
        localStorage.get('token').should('not.be.null')
        localStorage.get('username').should('be.equal', user.userName)
        localStorage.get('email').should('be.equal', user.email)
        localStorage.get('id').should('be.a', 'string')

        myScriptListPage.getScripList().should('exist')

        homePage.logout()
        homePage.getLoggedWelcomeMessage().should('not.exist')
        localStorage.isEmpty().should('be.true')
    })
})