/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.beans.pageBeans.readupdelete;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bnguimgo.springbootrestclient.beans.utils.BeanUtilREST;
import com.bnguimgo.springbootrestclient.modele.User;
import javax.faces.application.FacesMessage.Severity;

/**
 * Contrôleur de la page RUD.
 *
 * @author l.duperron
 */
@Named("readupdeletemb")
@ViewScoped
public class ReadUpDeleteMB implements Serializable {

    /**
     * UUID généré.
     */
    private static final long serialVersionUID = -2636238162395569163L;

    /**
     * Bean permettant la communication avec le serveur REST distant.
     */
    @Inject
    private BeanUtilREST beanUtilRest;

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadUpDeleteMB.class);

    /**
     * Url de lecture.
     */
    private String urlRead;

    /**
     * Url de mise à jour.
     */
    private String urlUpdate;

    /**
     * Url de suppression.
     */
    private String urlDelete;

    /**
     * Liste des utilisateurs.
     */
    private List<User> listeUtilisateurs;

    /**
     * Utilisateur sélectionné.
     */
    private User userSelectionne;

    /**
     * id du formulaire contenant le composant messages.
     */
    private final String ID_FORM_MESSAGES = "formCUD";

    /**
     * id du composant messages.
     */
    private final String ID_MESSAGES = "messages";

    /**
     * Constructeur sans paramètres.
     */
    public ReadUpDeleteMB() {
    }

    /**
     * Méthode d'initialisation après la construction du bean.
     */
    @PostConstruct
    private void init() {
        this.urlRead = BeanUtilREST.URL_SERVEUR_DISTANT_USER + "readall";
        this.urlUpdate = BeanUtilREST.URL_SERVEUR_DISTANT_USER + "update";
        this.urlDelete = BeanUtilREST.URL_SERVEUR_DISTANT_USER + "delete";
    }

    /**
     * Afficher un message dans la page, via le composant p:message.
     *
     * @param pMessage message.
     * @param pSeveriteDuMessage Sévérité du message = INFO, WARN, ERROR, FATAL.
     */
    public void afficherMessage(final String pMessage, final Severity pSeveriteDuMessage) {
        FacesContext.getCurrentInstance().addMessage(this.ID_FORM_MESSAGES, new FacesMessage(pSeveriteDuMessage, this.ID_MESSAGES, pMessage));
    }

    /**
     * Méthode permettant de mettre à jour la liste des utilisateurs.
     */
    private void updateListeUsers() {
        this.listeUtilisateurs = this.transformerObjetEnListeUsers(this.beanUtilRest.get(this.urlRead, User[].class));
        LOGGER.warn("get");
        this.logUsers();
    }

    /**
     * Méthode appelée par la vue pour en sauvegarder un.
     */
    public void saveUser() {
        if (this.userSelectionne != null) {
            StringBuilder vSb = new StringBuilder();
            LOGGER.warn("on envoie ça");
            vSb.append("Etat : ")
                    .append(this.userSelectionne.getEtatActivite()
                            .toString())
                    .append(", Login : ")
                    .append(this.userSelectionne.getUserLogin());
            LOGGER.warn(vSb.toString());
            User vUserRenvoye = this.beanUtilRest.updaterUnTruc(
                    this.userSelectionne,
                    this.urlUpdate
                    + this.userSelectionne.getUserId().toString(),
                    User.class
            );
            vSb = new StringBuilder();
            if (Objects.nonNull(vUserRenvoye)) {
                vSb.append(vUserRenvoye.getEtatActivite().toString())
                        .append(vUserRenvoye.getUserLogin());
                LOGGER.warn("on me renvoie ça");
                LOGGER.warn(vSb.toString());
                this.afficherMessage("Les modifications ont bien été prises en compte.", FacesMessage.SEVERITY_INFO);
            } else {
                /**
                 * Tant que le problème "Unrecognized field not marked as
                 * ignorable" n'est pas réglé, ça affichera ça. Du coup tant pis
                 * pour les contrôles post-envoi
                 */
                LOGGER.warn("on me renvoie que dalle");

                this.afficherMessage("Rien ne nous permet de vous dire qu'il a été sauvegardé --'", FacesMessage.SEVERITY_WARN);
            }
        }
        this.updateListeUsers();
    }

    /**
     * Méthode appelée par la vue pour en supprimer un.
     */
    public void supprimer() {
        LOGGER.warn("On supprime ! Du moins on tente");
        StringBuilder vSb = new StringBuilder();
        if (this.userSelectionne != null) {
            vSb.append("On supprime le User avec l'id ")
                    .append(this.userSelectionne.getUserId())
                    .append(", le login ")
                    .append(this.userSelectionne.getUserLogin())
                    .append(" et l'état de validité à ");
            if (this.userSelectionne.getEtatActivite() != null) {
                vSb.append(this.userSelectionne.getEtatActivite().toString());
            }
            LOGGER.warn(vSb.toString());
            this.beanUtilRest.delete(
                    this.urlDelete
                    + this.userSelectionne.getUserId().toString());
        }
        this.updateListeUsers();
        this.afficherMessage("L'utilisateur a bien été supprimé.", FacesMessage.SEVERITY_INFO);
    }

    /**
     * On check voir ce qu'il y a dans la liste lors du clic envoi formulaire.
     */
    public void logUsers() {
        StringBuilder vSb = new StringBuilder();
        this.listeUtilisateurs.stream()
                .filter((pUser) -> pUser.getUserLogin() != null)
                .map((pUser) -> {
                    if (pUser.getEtatActivite() != null) {
                        vSb.append(pUser.getEtatActivite().toString());
                    }
                    return pUser;
                })
                .forEach((pUser) -> vSb.append(pUser.getUserLogin()));
        LOGGER.warn(vSb.toString());
    }

    /**
     * Methode appelée par la page, permettant d'afficher la liste des users.
     *
     * @return liste des users.
     */
    public List<User> getAllYourDataBaseAreBelongToUs() {
        if (this.listeUtilisateurs == null) {
            this.updateListeUsers();
        }
        return this.listeUtilisateurs;
    }

    /**
     * Methode qui transforme la liste des users sortie brute du serveur en un
     * truc joli pour les yeux.
     *
     * @param pListeUser liste de Users.
     * @return truc joli.
     */
    private List<User> transformerObjetEnListeUsers(final Object pListeUser) {
        List<User> vRetour = new LinkedList<>();
        if (Objects.isNull(pListeUser)) {
            LOGGER.error("Problème avec la requête REST GET dans le READUPDELETE, l'objet retourné est null");
        } else if (!(pListeUser instanceof User[])) {
            LOGGER.error("Problème avec la requête REST GET dans le READUPDELETE, l'objet retourné n'est pas un tableau d'User");
        } else {
            vRetour = Arrays.asList((User[]) pListeUser);
        }
        return vRetour;
    }

    /**
     * Getter de la propriété {@link #userSelectionne}.
     *
     * @return {@link #userSelectionne}
     */
    public User getUserSelectionne() {
        return this.userSelectionne;
    }

    /**
     * Setter de la propriété {@link #userSelectionne}.
     *
     * @param pUserSelectionne {@link #userSelectionne}
     */
    public void setUserSelectionne(final User pUserSelectionne) {
        this.userSelectionne = pUserSelectionne;
    }
}
