package com.petproject.henlofren;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnimalServiceTest {

    private final AnimalRepository animalRepository = mock(AnimalRepository.class);

    private final AnimalService service;

    AnimalServiceTest() {
        service = new AnimalService(animalRepository);
    }

    @Test
    void shouldReturnAllAnimals() {
        Animal dog = new Animal("Dog");
        Animal cat = new Animal("Cat");
        Animal duck = new Animal("Duck");
        when(animalRepository.findAll()).thenReturn(List.of(dog,cat,duck));
        assertThat(service.findAllAnimals(), is(List.of("Dog","Cat","Platypus")));
    }
}