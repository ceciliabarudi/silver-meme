package com.petproject.henlofren;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}