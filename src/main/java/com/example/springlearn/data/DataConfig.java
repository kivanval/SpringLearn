package com.example.springlearn.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return args -> {

        };
    }
}
