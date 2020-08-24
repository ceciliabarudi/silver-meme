package com.petproject.henlofren;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {


    public List<Animal> findAllAnimals() {
        Animal dog = new Animal();
        dog.setName("dog");

        return List.of(dog);
    }
}
