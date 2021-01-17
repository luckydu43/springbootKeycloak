/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.modele;

/**
 * Modèle de l'entité JPA UserREAD du service distant. Elle étend la classe
 * UserREAD en y ajoutant la propriété id.
 *
 * @author l.duperron
 */
public class User extends UserREAD {

    /**
     * ID de l'utilisateur.
     */
    private Long userId;

    /**
     * Constructeur sans paramètres.
     */
    public User() {
    }

    /**
     * Getter de la propriété {@link #userId}.
     *
     * @return {@link #userId}
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Setter de la propriété {@link #userId}.
     *
     * @param pUserId {@link #userId}
     */
    public void setUserId(final Long pUserId) {
        this.userId = pUserId;
    }
}
