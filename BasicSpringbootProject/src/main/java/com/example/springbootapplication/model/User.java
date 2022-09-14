package com.example.springbootapplication.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.springbootapplication.dto.UserDTO;

/**
 * Entité Utilisateur.
 *
 * @author l.duperron
 */
@Entity
@Table(name = "utilisateur")
@XmlRootElement(name = "user")
public class User implements Serializable {

    /**
     * UUID.
     */
    private static final long serialVersionUID = -7381063329103766386L;

    /**
     * ID de l'utilisateur.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userId;

    /**
     * Login.
     */
    @Column(name = "user_login", unique = true, insertable = true, updatable = true, nullable = false)
    private String userLogin;

    /**
     * Etat d'activité. Répond à la question "L'user est-il actif ?".
     */
    @Column(name = "user_etat_activite", insertable = true, updatable = true, nullable = false)
    private Integer etatActivite;

    /**
     * Droits.
     */
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "droits_par_utilisateur", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "droit_id"))
    private Set<Droit> droits;

    /**
     * Constructeur sans paramètres.
     */
    public User() {
        super();
        this.droits = new HashSet<>();
    }

    /**
     * Constructeur avec userLogin, mdp et état d'activité.
     *
     * @param pLogin userLogin.
     * @param pActive état d'activité.
     */
    public User(final String pLogin, final Integer pActive) {
        this.droits = new HashSet<>();
        this.userLogin = pLogin;
        this.etatActivite = pActive;
    }

    /**
     * Constructeur avec Id et userLogin.
     *
     * @param pId userId.
     * @param pLogin userLogin.
     */
    public User(final Long pId, final String pLogin) {
        this.droits = new HashSet<>();
        this.userId = pId;
        this.userLogin = pLogin;
    }

    /**
     * Constructeur avec userLogin.
     *
     * @param pLogin userLogin.
     */
    public User(final String pLogin) {
        this.droits = new HashSet<>();
        this.userLogin = pLogin;
    }

    /**
     * Constructeur avec UserDTO.
     *
     * @param pUserDTO userDTO.
     */
    public User(final UserDTO pUserDTO) {
        this.droits = new HashSet<>();
        this.userId = pUserDTO.getUserId();
        this.userLogin = pUserDTO.getUserLogin();
    }

    /**
     * COnstructeur avec userId, userLogin, mdp et état d'activité.
     *
     * @param pId userId.
     * @param pLogin userLogin.
     * @param pEtatActivite état d'activité.
     */
    public User(final Long pId, final String pLogin, final Integer pEtatActivite) {
        this.droits = new HashSet<>();
        this.userId = pId;
        this.userLogin = pLogin;
        this.etatActivite = pEtatActivite;
    }

    /**
     * Getter userId.
     *
     * @return this.userId.
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Setter Id.
     *
     * @param pId userId.
     */
    @XmlElement
    public void setUserId(final Long pId) {
        this.userId = pId;
    }

    /**
     * Getter userLogin.
     *
     * @return this.userLogin.
     */
    public String getUserLogin() {
        return this.userLogin;
    }

    /**
     * Setter userLogin.
     *
     * @param pLogin userLogin.
     */
    @XmlElement
    public void setUserLogin(final String pLogin) {
        this.userLogin = pLogin;
    }

    /**
     * Getter de Etat Activite.
     *
     * @return this.etatActivite.
     */
    public Integer getEtatActivite() {
        return this.etatActivite;
    }

    /**
     * Setter de Etat Activite.
     *
     * @param pEtatActivite Etat Activite.
     */
    @XmlElement
    public void setEtatActivite(final Integer pEtatActivite) {
        this.etatActivite = pEtatActivite;
    }

    /**
     * Getter de droits.
     *
     * @return this.droits.
     */
    public Set<Droit> getDroits() {
        return this.droits;
    }

    /**
     * Setter de droits.
     *
     * @param pDroits Set de droits.
     */
    @XmlElement
    public void setDroits(final Set<Droit> pDroits) {
        this.droits = pDroits;
    }

    /**
     * ToString.
     *
     * @return tostring.
     */
    @Override
    public String toString() {
        return "User "
                + this.userLogin
                + "\n\tid=" + this.userId
                + "\n\tetatactivite=" + this.etatActivite
                + "\n\tdroits=" + this.droits;
    }

    /**
     * Hashcode plus propre.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        final int vNbPremier = 43;
        int vHashCode = 13;
        vHashCode = vNbPremier * vHashCode;
        if (this.etatActivite != null) {
            vHashCode += this.etatActivite.hashCode();
        }
        vHashCode = vNbPremier * vHashCode;
        if (this.userId != null) {
            vHashCode += this.userId.hashCode();
        }
        vHashCode = vNbPremier * vHashCode;
        if (this.userLogin != null) {
            vHashCode += this.userLogin.hashCode();
        }
        vHashCode = vNbPremier * vHashCode;
        if (this.droits != null) {
            vHashCode += this.droits.hashCode();
        }
        return vHashCode;
    }

    /**
     * Equals plus propre.
     *
     * @param pObj Objet à comparer.
     * @return equals.
     */
    @Override
    public boolean equals(final Object pObj) {
        // Test de cohérence du paramètre en entrée.
        if (pObj != null && this.getClass() == pObj.getClass()) {
            // Cast du paramètre.
            final User vAutreUser = (User) pObj;
            /**
             * on retourne vrai si les objets sont les mêmes, ou, ... __________
             * si pour chaque paramètre de this : ______________________________
             * s'il est nul alors on continue en restant à true_________________
             * sinon c'est qu'il doit être identique à celui de l'autre objet.
             */
            return (this == pObj)
                    || ((this.getEtatActivite() == null || (this.getEtatActivite() != null && this.getEtatActivite().equals(vAutreUser.getEtatActivite())))
                    && (this.getUserId() == null || (this.getUserId() != null && this.getUserId().equals(vAutreUser.getUserId())))
                    && (this.getUserLogin() == null || (this.getUserLogin() != null && this.getUserLogin().equals(vAutreUser.getUserLogin())))
                    && (this.getDroits() == null || (this.getDroits() != null && this.getDroits().equals(vAutreUser.getDroits()))));
        }
        // Si l'objet en paramètre n'est pas cohérent on retourne false.
        return false;
    }
}
