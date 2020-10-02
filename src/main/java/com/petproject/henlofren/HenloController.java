package com.petproject.henlofren;

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
import java.util.stream.Collectors;

@RestController
public class HenloController {

    private final AnimalService animalService = new AnimalService();

    @RequestMapping("/")
    public String index() {
        return "Henlo fren";
    }

    @GetMapping("/animals")
    public String all() {
        return animalService.findAllAnimals().stream()
                .map(Animal::getName)
                .collect(Collectors.joining(" "));
    }

    @GetMapping("/animals/{id}")
    public Animal findAnimal(@PathVariable long id) throws WhoDisAnimalException {
        return animalService.findAnimalById(id);
    }

    @PostMapping("/animals")
    public ResponseEntity<Object> createAnimal(@RequestBody Animal animal) {
        Animal savedAnimal = animalService.save(animal);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAnimal.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/animals/{id}")
    public ResponseEntity<Object> updateAnimal(@RequestBody Animal animal, @PathVariable long id) {
        animalService.findAnimalById(id);

        animal.setId(id);

        animalService.save(animal);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/animals/{id}")
    public void deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimalById(id);
    }

}