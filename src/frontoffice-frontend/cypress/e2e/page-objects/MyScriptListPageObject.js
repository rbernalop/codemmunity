
export class MyScriptListPageObject {
    constructor(cy) {
        this.cy = cy;
    }

    getScripList() {
        return cy.get('div[class="ant-card-body"]')
    }


}