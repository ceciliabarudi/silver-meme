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
        it('should be able to create new animal', function () {
            cy.visit('/')
            cy.request('POST', '/animals', {name: 'Narwhal'})
                .then((response) => {
                    expect(response.status).to.eq(201)
                    expect(response.body).to.contain('Narwhal')
                    expect(response.headers.location).to.eq('http://localhost/animals')
                })
        });
        it('should be able to update animal', function () {
            cy.visit('/')
            cy.request('PUT', '/animals/3', {name: 'Pigeon'})
                .then((response) => {
                    expect(response.status).to.eq(200)
                    expect(response.body).to.contain('Pigeon')
                    expect(response.headers.location).to.eq('http://localhost/animals')
                })
        });
    });
})