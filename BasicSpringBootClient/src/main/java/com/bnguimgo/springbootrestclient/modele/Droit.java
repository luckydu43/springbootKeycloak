/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.modele;

/**
 * Modèle de l'entité JPA Droit du serveur distant.
 *
 * @author l.duperron
 */
public class Droit {

    /**
     * Id.
     */
    private Long droitId;

    /**
     * Nom.
     */
    private String droitNom;

    /**
     * Constructeur sans paramètres.
     */
    public Droit() {
    }

    /**
     * Getter de la propriété {@link #droitId}.
     *
     * @return {@link #droitId}
     */
    public Long getDroitId() {
        return this.droitId;
    }

    /**
     * Setter de la propriété {@link #droitId}.
     *
     * @param pDroitId {@link #droitId}
     */
    public void setDroitId(final Long pDroitId) {
        this.droitId = pDroitId;
    }

    /**
     * Getter de la propriété {@link #droitNom}.
     *
     * @return {@link #droitNom}
     */
    public String getDroitNom() {
        return this.droitNom;
    }

    /**
     * Setter de la propriété {@link #droitNom}.
     *
     * @param pDroitNom {@link #droitNom}
     */
    public void setDroitNom(final String pDroitNom) {
        this.droitNom = pDroitNom;
    }

}
