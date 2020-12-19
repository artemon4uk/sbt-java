package ru.sbt.course.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class CurrencyControllerTest {
    @Autowired
    private CurrencyController currencyController;

    @Test
    public void getCurrencyHistory() throws Exception {
        MockMvc mockMvc = standaloneSetup(currencyController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/currency?n=3"))
                .andExpect(status().isOk());
    }
}