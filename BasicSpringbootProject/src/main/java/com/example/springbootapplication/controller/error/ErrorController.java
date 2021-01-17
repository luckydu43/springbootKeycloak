package com.example.springbootapplication.controller.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur utilitaire qui renseigne automatiquement dès le démarrage du
 * client si le serveur REST est en erreur grâce à la réponse renvoyée par
 * HttpStatus (cas nominal = 500). Ce cas d'erreur entraîne l'affichage d'une
 * page d'erreur.
 *
 * @author l.duperron
 */
@Controller
@RequestMapping("/error/*")
public class ErrorController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    /**
     * Constructeur vide.
     */
    public ErrorController() {
    }

    /**
     * Action à la connexion à l'application.
     *
     * @return ResponseEntity Réponse de l'application.
     */
    @GetMapping(value = "/")
    public ResponseEntity<String> pong() {
        LOGGER.info("Y a rien qui va");
        return new ResponseEntity<>("Y A RIEN QUI VA",
                HttpStatus.I_AM_A_TEAPOT);
    }

}
