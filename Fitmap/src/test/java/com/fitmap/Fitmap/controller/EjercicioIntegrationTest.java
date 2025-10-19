package com.fitmap.Fitmap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EjercicioIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEjercicioById() throws Exception {
        mockMvc.perform(get("/api/ejercicios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Sentadillas"));
    }

    @Test
    void testCreateEjercicio() throws Exception {
        String nuevoEjercicio = """
            {
              "nombre": "Press Banca",
              "categoria": "Pecho",
              "dificultad": "4"
            }
            """;

        mockMvc.perform(post("/api/ejercicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoEjercicio))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Press Banca"));
    }
}

