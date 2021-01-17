/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Classe initialisant l'application. Remplace l'ancien fichier web.xml.
 *
 * @author l.duperron
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Constructeur vide.
     */
    public ServletInitializer() {
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder pApplication) {
        return pApplication.sources(BasicApplication.class);
    }

}
