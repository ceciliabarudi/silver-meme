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

import java.util.List;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HenloController.class)
public class HenloControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnimalService animalService;

    @Test
    void shoulGetAHenloWhenPingingEndpoint() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Henlo fren")));
    }

    @Test
    void shoulReturnExistingAnimalsWhenRequested() throws Exception {
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

}