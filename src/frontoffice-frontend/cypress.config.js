const { defineConfig } = require("cypress");

module.exports = defineConfig({
  projectId: 'rmks3s',
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
  env: {
    FRONTOFFICE_URL: "http://localhost:3000",
  },
  chromeWebSecurity: false,
});
