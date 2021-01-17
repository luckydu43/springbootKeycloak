/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Surcharge des paramètres springboot habituellement modifiables par le fichier
 * web.xml.
 *
 * @author l.duperron
 */
@Configuration
public class VueAccueil implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry pRegistry) {
        pRegistry.addViewController("/").setViewName("forward:/index.xhtml");
        pRegistry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
