/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.beans.pageBeans.create;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.bnguimgo.springbootrestclient.beans.utils.BeanUtilREST;
import com.bnguimgo.springbootrestclient.modele.User;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Contrôleur de la page create.xhtml.
 *
 * @author l.duperron
 */
@Named("createmb")
@ViewScoped
public class CreateMB implements Serializable {

    /**
     * UUID généré.
     */
    private static final long serialVersionUID = -4337114033617103001L;

    /**
     * Bean permettant la communication avec le serveur REST distant.
     */
    @Inject
    private BeanUtilREST beanUtilRest;

    /**
     * User à créer.
     */
    private User userCree;

    /**
     * Url de redirection vers RUD.
     */
    private final String redirectRUD = "http://localhost:8080/client/readupdelete";

    /**
     * id du formulaire contenant le composant messages.
     */
    private final String ID_FORM_MESSAGES = "formCREATE";

    /**
     * id du composant messages.
     */
    private final String ID_MESSAGES = "messages";

    /**
     * Constructeur sans paramètres.
     */
    public CreateMB() {
        this.userCree = new User();
    }

    /**
     * Afficher un message dans la page, via le composant p:message.
     *
     * @param pMessage message.
     * @param pSeveriteDuMessage Sévérité du message = INFO, WARN, ERROR, FATAL.
     */
    public void afficherMessage(final String pMessage, final FacesMessage.Severity pSeveriteDuMessage) {
        FacesContext.getCurrentInstance().addMessage(this.ID_FORM_MESSAGES, new FacesMessage(pSeveriteDuMessage, this.ID_MESSAGES, pMessage));
    }

    /**
     * Enregistrer le user.
     */
    public void saveUser() {
        this.save("saveuser", "L'utilisateur a bien été créé.");
    }

    /**
     * Enregistrer l'admin.
     */
    public void saveAdmin() {
        this.save("saveadmin", "L'administrateur a bien été créé.");
    }

    /**
     * Méthode privée pour enregistrer l'utilisateur/administrateur.
     *
     * @param pSuffixeURL saveadmin ou saveuser
     */
    private void save(final String pSuffixeURL, final String pMessageCreation) {
        User pUser = this.beanUtilRest.post(this.userCree,
                BeanUtilREST.URL_SERVEUR_DISTANT_USER + pSuffixeURL,
                User.class
        );
        if (pUser == null) {
            this.afficherMessage("Une erreur est survenue durant la création, vérifiez à minima que le login n'est pas doublon.", FacesMessage.SEVERITY_ERROR);
        } else {
            this.afficherMessage(pMessageCreation, FacesMessage.SEVERITY_INFO);
        }
    }

    public String redirectRUD() {
        return this.redirectRUD;
    }

    /**
     * Getter de la propriété {@link #userCree}.
     *
     * @return {@link #userCree}
     */
    public User getUserCree() {
        return this.userCree;
    }

    /**
     * Setter de la propriété {@link #userCree}.
     *
     * @param pUserCree {@link #userCree}
     */
    public void setUserCree(final User pUserCree) {
        this.userCree = pUserCree;
    }
}
