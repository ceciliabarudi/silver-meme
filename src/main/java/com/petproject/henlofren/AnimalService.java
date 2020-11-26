package com.petproject.henlofren;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAllAnimals() {
        return animalRepository.findAll();
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
