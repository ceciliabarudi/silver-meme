package com.petproject.henlofren;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class HenloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shoulGetAHenloWhenPingingEndpoint() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Henlo fren")));
    }

    @Test
    public void shoulReturnExistingAnimalsWhenRequested() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/animals").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("dog")));
    }
}