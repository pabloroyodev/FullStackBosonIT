package com.example.ex7;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Clase3 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for (String s:args) {
            System.out.println(s);
        }
    }
}
