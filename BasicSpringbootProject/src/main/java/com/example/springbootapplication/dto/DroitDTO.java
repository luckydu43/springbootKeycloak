/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.dto;

import java.io.Serializable;

/**
 * DTO de l'entité droit.
 *
 * @author l.duperron
 */
public class DroitDTO implements Serializable {

    /**
     * ID généré.
     */
    private static final long serialVersionUID = 8560293318352946228L;

    /**
     * Id droit.
     */
    private Long droitID;

    /**
     * Constructeur vide.
     */
    public DroitDTO() {
    }

    /**
     * Constructeur avec Id droit.
     *
     * @param pDroitID id droit.
     */
    public DroitDTO(final Long pDroitID) {
        this.droitID = pDroitID;
    }

    /**
     * Getter de la propriété {@link #droitID}.
     *
     * @return {@link #droitID}
     */
    public Long getDroitID() {
        return this.droitID;
    }

    /**
     * Setter de la propriété {@link #droitID}.
     *
     * @param pDroitID {@link #droitID}
     */
    public void setDroitID(final Long pDroitID) {
        this.droitID = pDroitID;
    }

}
