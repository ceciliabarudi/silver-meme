package com.petproject.henlofren;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnimalServiceTest {

    private final AnimalRepository animalRepository = mock(AnimalRepository.class);

    private final AnimalService service;

    AnimalServiceTest() {
        service = new AnimalService(animalRepository);
    }

    @Test
    void shouldReturnAllExistingAnimals() {
        Animal dog = new Animal("Dog");
        Animal cat = new Animal("Cat");
        Animal duck = new Animal("Duck");
        when(animalRepository.findAll()).thenReturn(List.of(dog, cat, duck));

        assertThat(service.findAllAnimals(), contains(
                hasProperty("name", is("Dog")),
                hasProperty("name", is("Cat")),
                hasProperty("name", is("Duck"))
                )
        );
    }

    @Test
    void shouldThrowAWhoDisWhenAnimalIsNotFoundById() {
        when(animalRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        assertThrows(WhoDisAnimalException.class, () -> service.findAnimalById(2));
    }

    @Test
    void shouldReturnAnimalById() {
        Animal cat = new Animal("Cat");
        when(animalRepository.findById(2L)).thenReturn(java.util.Optional.of(cat));

        assertThat(service.findAnimalById(2), is(cat));
    }
}