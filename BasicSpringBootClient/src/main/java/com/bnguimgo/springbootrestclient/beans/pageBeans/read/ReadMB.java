/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.beans.pageBeans.read;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.bnguimgo.springbootrestclient.beans.utils.BeanUtilREST;
import com.bnguimgo.springbootrestclient.modele.User;
import com.bnguimgo.springbootrestclient.modele.UserREAD;

/**
 * Contrôleur de la page read.
 *
 * @author l.duperron
 */
@Named("readmb")
@ViewScoped
public class ReadMB implements Serializable {

    /**
     * UUID généré.
     */
    private static final long serialVersionUID = -1401740282813205550L;

    /**
     * Bean permettant la communication avec le serveur REST distant.
     */
    @Inject
    private BeanUtilREST beanUtilRest;
    /**
     * Url de lecture.
     */
    private final String urlRead = "http://localhost:8080/user/readall";

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadMB.class);

    /**
     * Constructeur sans paramètres.
     */
    public ReadMB() {
    }

    /**
     * Methode appelée par la page, permettant d'afficher la liste des users.
     *
     * @return liste des users.
     */
    public List<UserREAD> getAllYourDataBaseAreBelongToUs() {
        return this.transformerObjetEnListe(this.beanUtilRest.get(this.urlRead, User[].class));
    }

    /**
     * Methode qui transforme la liste des users sortie brute du serveur en un
     * truc joli pour les yeux.
     *
     * @param pListeUser liste de Users.
     * @return truc joli.
     */
    private List<UserREAD> transformerObjetEnListe(final Object pListeUser) {
        List<UserREAD> vRetour = new LinkedList<>();
        if (Objects.isNull(pListeUser)) {
            LOGGER.error("Problème avec la requête REST GET dans le READ, l'objet retourné est null");
        } else if (!(pListeUser instanceof UserREAD[])) {
            LOGGER.error("Problème avec la requête REST GET dans le READ, l'objet retourné n'est pas un tableau d'User");
        } else {
            vRetour = Arrays.asList((UserREAD[]) pListeUser);
        }
        return vRetour;
    }
}
