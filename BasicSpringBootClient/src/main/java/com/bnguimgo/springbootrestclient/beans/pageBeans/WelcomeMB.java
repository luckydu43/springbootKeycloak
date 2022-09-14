/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.beans.pageBeans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Contrôleur de la page index.xhtml.
 *
 * @author l.duperron
 */
@Named("welcomemb")
@ViewScoped
public class WelcomeMB implements Serializable {

    /**
     * Projet.
     */
    private final String poc = "Proof of Concept d'un projet sous architecture micro-services avec springboot";

    /**
     * Pile logicielle.
     */
    private final String pileLogicielle = "Il utilise Primefaces 6.1, XHTML5, et Hibernate.";

    /**
     * UUID Généré.
     */
    private static final long serialVersionUID = 7015373670328537726L;

    /**
     * Constructeur sans paramètres.
     */
    public WelcomeMB() {
    }

    /**
     * Getter SonHistoire.
     *
     * @return son histoire.
     */
    public String getPoc() {
        return this.poc;
    }

    /**
     * Getter de la propriété {@link #pileLogicielle}.
     *
     * @return {@link #pileLogicielle}
     */
    public String getPileLogicielle() {
        return this.pileLogicielle;
    }
}
