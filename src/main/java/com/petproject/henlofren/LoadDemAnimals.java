package com.petproject.henlofren;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDemAnimals {
    private static final Logger log = LoggerFactory.getLogger(LoadDemAnimals.class);

    @Bean
    CommandLineRunner initDatabase(AnimalRepository repository) {

        return args -> {
            // save a few animals
            log.info("Preloading animals: ");
            repository.save(new Animal("Dog"));
            repository.save(new Animal("Cat"));
            repository.save(new Animal("Platypus"));
            repository.save(new Animal("Raccoon"));
            repository.save(new Animal("Duck"));

            // fetch all animals
            log.info("Animals found with findAll():");
            log.info("-------------------------------");
            for (Animal animal : repository.findAll()) {
                log.info("Animal is: {}", animal);
            }
            log.info("");

            // fetch an individual animal by ID
            Animal animal = repository.findById(1L).get();
            log.info("--------------------------------");
            log.info("Animal found with findById(1L): {}", animal);
            log.info("");
        };
    }
}