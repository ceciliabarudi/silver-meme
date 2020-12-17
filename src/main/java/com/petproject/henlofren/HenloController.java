package com.petproject.henlofren;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HenloController {

    @Autowired
    private AnimalService animalService;

    @RequestMapping("/")
    public String index() {
        return "Henlo fren";
    }

    @GetMapping("/animals")
    public String all() {
        List<Animal> animals = animalService.findAllAnimals();

        if (animals.equals(Collections.emptyList())) {
            return "sorry fren, no aminals here";
        }
        return animals.stream()
                .map(Animal::getName)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/animals/{id}")
    public String findAnimal(@PathVariable long id) {
        Animal animal = animalService.findAnimalById(id);

        if (animal != null) {
            return animal.getName();
        }

        return "sorry, dat aminal no exist";
    }

    @PostMapping("/animals")
    public ResponseEntity<Object> createAnimal(@RequestBody Animal animal) {
        animalService.save(animal);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/animals/{id}")
    public ResponseEntity<Object> updateAnimal(@RequestBody Animal animal, @PathVariable long id) {
        animalService.findAnimalById(id);

        animalService.save(animal);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/animals/{id}")
    public void deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimalById(id);
    }

}