/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Implémentation du modèle de l'entité JPA User du service distant à but de
 * manipulation dans les opérations de CRUD. Sa principale différence avec le
 * modèle original est l'absence d'id.
 *
 * @author l.duperron
 */
public class UserREAD {

    /**
     * Login.
     */
    private String userLogin;

    /**
     * Etat d'activité. Répond à la question "L'user est-il actif ?".
     */
    private Integer etatActivite;

    /**
     * Droits.
     */
    private Set<Droit> droits;

    /**
     * Constructeur sans paramètres.
     */
    public UserREAD() {
    }

    /**
     * Getter de la propriété {@link #userLogin}.
     *
     * @return {@link #userLogin}
     */
    public String getUserLogin() {
        return this.userLogin;
    }

    /**
     * Setter de la propriété {@link #userLogin}.
     *
     * @param pUserLogin {@link #userLogin}
     */
    public void setUserLogin(final String pUserLogin) {
        this.userLogin = pUserLogin;
    }

    /**
     * Getter de la propriété {@link #etatActivite}.
     *
     * @return {@link #etatActivite}
     */
    public Integer getEtatActivite() {
        return this.etatActivite;
    }

    /**
     * Setter de la propriété {@link #etatActivite}.
     *
     * @param pEtatActivite {@link #etatActivite}
     */
    public void setEtatActivite(final Integer pEtatActivite) {
        this.etatActivite = pEtatActivite;
    }

    /**
     * Getter de la propriété {@link #droits}.
     *
     * @return {@link #droits}
     */
    public List<Droit> getDroits() {
        List<Droit> vListeDroits = new ArrayList<>();
        vListeDroits.addAll(this.droits);
        return vListeDroits;
    }

    /**
     * Setter de la propriété {@link #droits}.
     *
     * @param pDroits {@link #droits}
     */
    public void setDroits(final Set<Droit> pDroits) {
        this.droits = pDroits;
    }
}
