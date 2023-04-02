import {HomePageObject} from "./page-objects/HomePageObject";
import {LocalStorage} from "./page-objects/LocalStorage";
import {registeredUser} from "./object-mother/UserMother";
import {tabActiveClass} from "./constants/cssClasses";
import {LoginTabObject} from "./page-objects/LoginTabObject";
import {MyScriptListPageObject} from "./page-objects/MyScriptListPageObject";
import {ScriptPageObject} from "./page-objects/ScriptPageObject";

describe('template spec', () => {
    beforeEach(() => {
        cy.visit(Cypress.env('FRONTOFFICE_URL'))
    });
    it('Should Run Script when user is authenticated', () => {
        const homePage = new HomePageObject(cy)
        const loginTab = new LoginTabObject(cy)
        const myScriptListPage = new MyScriptListPageObject(cy)
        const scriptPage = new ScriptPageObject(cy)
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

        myScriptListPage.getScripList().then($scriptList => {
            myScriptListPage.createScript()
            myScriptListPage.getScripList().should('have.length', $scriptList.length + 1)

            cy.visit(Cypress.env('FRONTOFFICE_URL') + '/script/d9b385e7-1831-425a-8222-83fdc086b03d')
            scriptPage.getExecutionResult().should('be.empty')
            scriptPage.runScript()
            scriptPage.getExecutionResult().should('not.be.empty')

            homePage.logout()
            homePage.getLoggedWelcomeMessage().should('not.exist')
            localStorage.isEmpty().should('be.true')
        })
    })
})