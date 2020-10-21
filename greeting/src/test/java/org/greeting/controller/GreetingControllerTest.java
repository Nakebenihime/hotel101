package org.greeting.controller;

import org.greeting.model.Greeting;
import org.greeting.service.GreetingService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerTest {

    private MockMvc mvc;

    @Autowired
    public void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }

    @MockBean
    private GreetingService service;

    @DisabledIf(expression = "#{systemProperties['spring.profiles.active'].contains('qualification')}")
    @Test
    void greeting_master_environment() throws Exception {
        final String URI = "/api/v1/greetings";
        final String PAYLOAD = "Welcome message from property file! Welcome to production";

        when(service.greeting()).thenReturn(PAYLOAD);

        mvc.perform(MockMvcRequestBuilders.get(URI))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.payload").value(PAYLOAD));
    }

    @DisabledIf(expression = "#{systemProperties['spring.profiles.active'].contains('master')}")
    @Test
    void greeting_qualification_environment() throws Exception {
        final String URI = "/api/v1/greetings";
        final String PAYLOAD = "¡Mensaje de bienvenida del archivo de propiedades! Bienvenido a qualification !";

        when(service.greeting()).thenReturn(PAYLOAD);

        mvc.perform(MockMvcRequestBuilders.get(URI))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.payload").value(PAYLOAD));
    }
}