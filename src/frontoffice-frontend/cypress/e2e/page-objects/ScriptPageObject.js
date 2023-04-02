

export class ScriptPageObject {

    constructor(cy) {
        this.cy = cy;
    }

    runScript() {
        cy.get('button[id="run-script-btn"]').click()
    }

    getExecutionResult() {
        return cy.get('pre[id="execution-result"]')
    }
}