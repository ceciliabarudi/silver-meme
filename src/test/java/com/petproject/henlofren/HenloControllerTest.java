package com.petproject.henlofren;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HenloController.class)
class HenloControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnimalService animalService;

    @Test
    void shouldGetAHenloWhenPingingEndpoint() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Henlo fren")));
    }

    @Test
    void shouldReturnAllExistingAnimalsWhenRequested() throws Exception {
        Animal dog = new Animal("Dog");
        Animal cat = new Animal("Cat");
        Animal duck = new Animal("Duck");
        when(animalService.findAllAnimals()).thenReturn(List.of(dog, cat, duck));

        mvc.perform(MockMvcRequestBuilders.get("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("dog, cat, duck")));
    }

    @Test
    void shouldSaySorryIfThereAreNoAnimalsToDisplay() throws Exception{
        when(animalService.findAllAnimals()).thenReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("sorry fren, no aminals here")));
    }

    @Test
    void shouldSaySorryWhenNoAnimalRequestedById() throws Exception {
        when(animalService.findAnimalById(7)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/animals/7")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(equalTo("sorry, dat aminal no exist")))
                .andExpect(redirectedUrl("http://localhost/animals"));
    }

    @Test
    void shouldReturnSpecificAnimalWhenRequestedById() throws Exception {
        Animal cat = new Animal("Cat");
        when(animalService.findAnimalById(2)).thenReturn(cat);

        mvc.perform(MockMvcRequestBuilders.get("/animals/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("cat")));
    }

    @Test
    void shouldAllowToCreateANewAnimal() throws Exception {
        Animal savedNarwhal = new Animal("Narwhal");
        savedNarwhal.setId(7L);
        when(animalService.save(any())).thenReturn(savedNarwhal);
        // any() porque el narwhal que se guarda y el que entra en la request
        // nunca van a ser el mismo objeto en memoria

        mvc.perform(MockMvcRequestBuilders.post("/animals")
                .content("{ \"name\": \"Narwhal\"}")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/animals"));
    }

    // TODO: create custom error mapping cause default one is ugly
}