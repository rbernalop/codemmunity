
const RENAME_SCRIPT_POSITION = 0;

export class MyScriptListPageObject {

    constructor(cy) {
        this.cy = cy;
    }

    getScripList() {
        return cy.get('div[class="ant-card-body"]')
    }

    getScriptByPosition(position) {
        return this.getScripList().eq(position)
    }


    renameScript(script, newName) {
        script.rightclick()
        cy.get('li[class="ant-dropdown-menu-item"]').eq(RENAME_SCRIPT_POSITION).click()
        const nameInput = cy.get('input[id="name"]')
        nameInput.clear()
        nameInput.type(newName)
        cy.get('button[type="submit"]').click()
    }

    getScriptByText(bubblesortEnPython) {
        return this.getScripList().contains(bubblesortEnPython)
    }
}