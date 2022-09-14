/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.beans.flux;

import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 * Bean en charge des flux de navigation.
 *
 * @author l.duperron
 */
@Named
@FlowScoped("customer")
public class BeanFlux implements Serializable {

    /**
     * UUID généré.
     */
    private static final long serialVersionUID = 3002979182621663559L;

    /**
     * Constructeur sans paramètres.
     */
    public BeanFlux() {
    }

    /**
     * Redirection vers la page index.xhtml.
     *
     * @return string clé de la page index.xhtml.
     */
    public String getHomeAction() {
        return "/index";
    }
}
