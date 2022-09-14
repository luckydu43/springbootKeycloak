/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entité droit, équivalente au "rôle" tel qu'on le rencontre habituellement.
 *
 * @author l.duperron
 */
@Entity
@Table(name = "droit")
@XmlRootElement(name = "droit")
public class Droit implements Serializable {

    /**
     * Id généré.
     */
    private static final long serialVersionUID = 4471131379500951517L;

    /**
     * ID du droit.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "droit_id", updatable = false, nullable = false)
    private Long droitId;

    /**
     * Nom.
     */
    @Column(name = "droit_nom", unique = true, insertable = true, updatable = true, nullable = false)
    private String droitNom;

    /**
     * Constructeur vide.
     */
    public Droit() {
        super();
    }

    /**
     * Constructeur avec id.
     *
     * @param pDroitId id du droit.
     */
    public Droit(final Long pDroitId) {
        this.droitId = pDroitId;
    }

    /**
     * Constructeur avec nom.
     *
     * @param pDroitNom nom.
     */
    public Droit(final String pDroitNom) {
        this.droitNom = pDroitNom;
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
