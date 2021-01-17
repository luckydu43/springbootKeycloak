package com.example.springbootapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée de l'application.
 *
 * @author l.duperron
 */
@SpringBootApplication
public class BasicApplication {

    /**
     * Méthode principale du point d'entrée de l'appli.
     *
     * @param pArgs arguments du main.
     */
    public static void main(final String[] pArgs) {
        SpringApplication.run(BasicApplication.class, pArgs);
    }
}
