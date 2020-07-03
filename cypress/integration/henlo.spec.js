/// <reference types="cypress" />

context('Pet project', () => {
    describe('Home page', function () {
        it('shouldSayHenloWhenSomeoneVisits', function () {
            cy.visit('/')
            cy.get('body').should('have.text', 'Henlo fren')
        });
    });
})