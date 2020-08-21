/// <reference types="cypress" />

context('Pet project', () => {
    describe('Viewing a resource', function () {
        it('should display list of existing animals', function () {
            cy.visit('/animals')
            cy.get('[data-test="animalList"] li').first().should('have.value', 'dog')
        });
        it('should display existing animal when requested by id', function () {
            cy.visit('/animals/s0m3D0g1d')
            cy.contains('dog')
        });
    });
})