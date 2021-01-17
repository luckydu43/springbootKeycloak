package com.example.springbootapplication.controller.defaut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur utilitaire qui renseigne automatiquement dès le démarrage du
 * client si le serveur REST est à l'écoute grâce à la réponse renvoyée par
 * HttpStatus (cas nominal = 200). Le cas d'erreur entraîne l'affichage d'une
 * page d'erreur.
 *
 * @author l.duperron
 */
@Controller
@RequestMapping("*")
public class DefaultController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    /**
     * Constructeur vide.
     */
    public DefaultController() {
    }

    /**
     * Action à la connexion à l'application.
     *
     * @return ResponseEntity Réponse de l'application.
     */
    @GetMapping(value = "/")
    public ResponseEntity<String> pong() {
        LOGGER.info("Démarrage des services OK .....");
        return new ResponseEntity<>("Réponse du serveur: " + HttpStatus.OK.name(),
                HttpStatus.OK);
    }

}
