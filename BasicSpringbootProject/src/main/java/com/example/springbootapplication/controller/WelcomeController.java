/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contrôleur dont le rôle est d'envoyer un ping au serveur pour vérifier s'il
 * est dispo. Le cas d'erreur entraîne l'affichage d'une page d'erreur.
 *
 * @author l.duperron
 */
@RequestMapping("/")
public class WelcomeController {

    /**
     * Constructeur par défaut.
     */
    public WelcomeController() {
    }

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    /**
     * #########################################################################
     * L'injection avec Autowired permet de construire les requêtes HTTP pour la
     * connexion avec le serveur.
     * #########################################################################
     */
    /**
     * RestTemplate.
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * PropertiesConfigurationService.
     */
    @Autowired
    private PropertiesConfigurationService configurationService;

    /**
     * Code HTTP ok.
     */
    private static final int HTTP_OK = 200;

    /**
     * Méthode principale du service.
     *
     * La classe modelAndVue fournie par Spring permet de construire la vue
     * welcome.jsp.
     *
     * @param pModelAndView modèle et vue.
     * @return ModelAndView modèle et vue.
     */
    @GetMapping(value = "/welcome")
    final ModelAndView ping(final ModelAndView pModelAndView) {
        final ResponseEntity<String> vReponseServeur = this.restTemplate.getForEntity(this.configurationService.getUrl(), String.class);
        final int vCodeReponseServeur = vReponseServeur.getStatusCodeValue();
        String vReponsePing = "";
        if (vCodeReponseServeur != HTTP_OK) {

            LOGGER.error("Réponse du serveur: "
                    + vCodeReponseServeur
                    + " ==> Serveur indisponible, votre application ne fonctionnera pas"
                    + this.configurationService.getPingServeurKo());
        } else {
            vReponsePing = this.configurationService.getPingServeurOk();
            LOGGER.info(this.configurationService.getPingServeur(), vReponsePing);
        }
        //construction de la vue
        pModelAndView.setViewName("welcome");
        pModelAndView.addObject("urlServeur", this.configurationService.getUrl());
        pModelAndView.addObject("pingServeur", vReponsePing);
        pModelAndView.addObject("profileActif", this.configurationService.getProfileActif());
        return pModelAndView;
    }

    /**
     * Continue la requête, ALLES GUT.
     *
     * @param pModelAndView modelAndView.
     * @return modelAndView modelAndView.
     */
    @GetMapping("/next")
    ModelAndView next(final ModelAndView pModelAndView) {
        pModelAndView.setViewName("next");
        pModelAndView.addObject("message", this.configurationService.getMessage());
        return pModelAndView;
    }
}
