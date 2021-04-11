package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void caseSens() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=Hello")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=hello").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("does not exist")));

    }

    @Test
    void userCheck() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=Test")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=Test")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("Test"))));
    }
}