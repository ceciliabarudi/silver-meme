/// <reference types="cypress" />

context('Pet project', () => {
    describe('Viewing a resource', function () {
        it('should display list of existing animals', function () {
            cy.visit('/animals')
            cy.contains('Dog, Cat, Platypus, Raccoon, Duck')
        });
        it('should display existing animal when requested by id', function () {
            cy.visit('/animals/3')
            cy.contains('Platypus')
        });
    });
})