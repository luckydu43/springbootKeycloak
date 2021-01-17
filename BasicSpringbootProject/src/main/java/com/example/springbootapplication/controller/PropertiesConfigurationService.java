/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.controller;

import org.springframework.beans.factory.annotation.Value;

/**
 * Service du fichier application.properties.
 *
 * @author l.duperron
 */
public class PropertiesConfigurationService {

    /**
     * Constructeur vide.
     */
    public PropertiesConfigurationService() {
    }

    // #########################################################################
    // #__Les valeurs ci-dessous sont alimentées par le application.properties_#
    // #########################################################################
    /**
     * Valeur url du serveur.
     */
    @Value("${app.serveur.url}")
    private final String url = "";

    /**
     * Valeur pingServeur.
     */
    @Value("${app.serveur.pingServeur}")
    private String pingServeur;

    /**
     * Valeur serveur ok (HTTP 200).
     */
    @Value("${app.serveur.ok}")
    private String pingServeurOk;

    /**
     * Valeur serveur ko (HTTP 500).
     */
    @Value("${app.serveur.ko}")
    private String pingServeurKo;

    /**
     * Message.
     */
    @Value("${nextpage.message}")
    private String message;

    /**
     * Profil actif.
     */
    @Value("${spring.profiles.active}")
    private String profileActif;

    /**
     * Getter url.
     *
     * @return this.url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Getter ping serveur.
     *
     * @return this.pingserveur.
     */
    public String getPingServeur() {
        return this.pingServeur;
    }

    /**
     * Getter pingServeurOk.
     *
     * @return this.pingServeurOk.
     */
    public String getPingServeurOk() {
        return this.pingServeurOk;
    }

    /**
     * Getter pingServeurKo.
     *
     * @return this.pingServeurKo.
     */
    public String getPingServeurKo() {
        return this.pingServeurKo;
    }

    /**
     * Getter message.
     *
     * @return this.message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Getter profileActif.
     *
     * @return this.profileActif.
     */
    public String getProfileActif() {
        return this.profileActif;
    }
}
