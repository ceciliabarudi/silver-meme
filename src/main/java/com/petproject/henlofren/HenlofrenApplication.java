package com.petproject.henlofren;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HenlofrenApplication {

	private static final Logger log = LoggerFactory.getLogger(HenlofrenApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HenlofrenApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Inspecting the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

	@Bean
	public CommandLineRunner demo(AnimalRepository repository) {
		return (args) -> {
			// save a few animals
			repository.save(new Animal("Dog"));
			repository.save(new Animal("Cat"));
			repository.save(new Animal("Platypus"));
			repository.save(new Animal("Raccoon"));
			repository.save(new Animal("Duck"));

			// fetch all animals
			log.info("Animals found with findAll():");
			log.info("-------------------------------");
			for (Animal animal : repository.findAll()) {
				log.info(animal.toString());
			}
			log.info("");

			// fetch an individual animal by ID
			Animal animal = repository.findById(1L).get();
			log.info("Animal found with findById(1L):");
			log.info("--------------------------------");
			log.info(animal.toString());
			log.info("");
		};
	}

}
