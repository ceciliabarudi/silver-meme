package com.petproject.henlofren;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;


    public List<Animal> findAllAnimals() {
        Animal dog = new Animal();
        dog.setName("dog");

        return List.of(dog);
    }

    public Animal findAnimalById(long id) {
        return animalRepository.findById(id).orElseThrow(WhoDisAnimalException::new);
    }

    public Animal save(Animal animal) {
        return animal;
    }

    public void deleteAnimalById(long id) {

    }
}
