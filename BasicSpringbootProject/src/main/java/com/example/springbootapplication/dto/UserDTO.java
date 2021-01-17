/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.dto;

import java.io.Serializable;

/**
 * DTO de l'entité User.
 *
 * @author l.duperron
 */
public class UserDTO implements Serializable {

    /**
     * UUID généré.
     */
    private static final long serialVersionUID = -443589941665403890L;

    /**
     * Id.
     */
    private Long userId;

    /**
     * Login.
     */
    private String userLogin;

    /**
     * Type d'User.
     */
    private String userType;

    /**
     * Constructeur vide.
     */
    public UserDTO() {
    }

    /**
     * Constructeur avec userLogin.
     *
     * @param pUserLogin userLogin.
     */
    public UserDTO(final String pUserLogin) {
        this.userLogin = pUserLogin;
    }

    /**
     * Constructeur avec userLogin et type.
     *
     * @param pUserLogin userLogin.
     * @param pUserType type.
     */
    public UserDTO(final String pUserLogin, final String pUserType) {
        this.userLogin = pUserLogin;
        this.userType = pUserType;
    }

    /**
     * Constructeur avec id et userLogin.
     *
     * @param pUserId id.
     * @param pUserLogin userLogin.
     */
    public UserDTO(final Long pUserId, final String pUserLogin) {
        this.userId = pUserId;
        this.userLogin = pUserLogin;
    }

    /**
     * Constructeur avec id, userLogin et type.
     *
     * @param pUserId id.
     * @param pUserLogin userLogin.
     * @param pUserType type.
     */
    public UserDTO(final Long pUserId, final String pUserLogin, final String pUserType) {
        this.userId = pUserId;
        this.userLogin = pUserLogin;
        this.userType = pUserType;
    }

    /**
     * Getter de id.
     *
     * @return this.userId.
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * setter de id.
     *
     * @param pUserId id.
     */
    public void setUserId(final Long pUserId) {
        this.userId = pUserId;
    }

    /**
     * Getter de userLogin.
     *
     * @return this.userLogin.
     */
    public String getUserLogin() {
        return this.userLogin;
    }

    /**
     * Setter de userLogin.
     *
     * @param pUserLogin userLogin.
     */
    public void setUserLogin(final String pUserLogin) {
        this.userLogin = pUserLogin;
    }

    /**
     * Getter de type.
     *
     * @return this.type.
     */
    public String getUserType() {
        return this.userType;
    }

    /**
     * Setter de type.
     *
     * @param pUserType type.
     */
    public void setUserType(final String pUserType) {
        this.userType = pUserType;
    }

    /**
     * ToString.
     *
     * @return toString.
     */
    @Override
    public String toString() {
        return String.format("[id=%s, mail=%s, userType=%s]",
                this.userId,
                this.userLogin,
                this.userType
        );
    }
}
