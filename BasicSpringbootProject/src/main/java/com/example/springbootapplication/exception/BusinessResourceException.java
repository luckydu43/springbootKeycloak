/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception Business resource.
 *
 * @author l.duperron
 */
public class BusinessResourceException extends RuntimeException {

    /**
     * UUID Généré.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id de la ressource.
     */
    private Long resourceId;

    /**
     * Code d'erreur.
     */
    private String errorCode;

    /**
     * Statut.
     */
    private HttpStatus status;

    /**
     * Constructeur avec message.
     *
     * @param pMessage message.
     */
    public BusinessResourceException(final String pMessage) {
        super(pMessage);
    }

    /**
     * Constructeur avec id et message.
     *
     * @param pResourceId id de la ressource.
     * @param pMessage message.
     */
    public BusinessResourceException(final Long pResourceId, final String pMessage) {
        super(pMessage);
        this.resourceId = pResourceId;
    }

    /**
     * Constructeur avec id, code d'erreur et message.
     *
     * @param pResourceId id.
     * @param pCodeErreur code d'erreur.
     * @param pMessage message.
     */
    public BusinessResourceException(final Long pResourceId, final String pCodeErreur, final String pMessage) {
        super(pMessage);
        this.resourceId = pResourceId;
        this.errorCode = pCodeErreur;
    }

    /**
     * Constructeur avec code d'erreur et message.
     *
     * @param pCodeErreur code d'erreur.
     * @param pMessage message.
     */
    public BusinessResourceException(final String pCodeErreur, final String pMessage) {
        super(pMessage);
        this.errorCode = pCodeErreur;
    }

    /**
     * Constructeur avec code d'erreur, message et statut.
     *
     * @param pCodeErreur code d'erreur.
     * @param pMessage message.
     * @param pStatut statut.
     */
    public BusinessResourceException(final String pCodeErreur, final String pMessage, final HttpStatus pStatut) {
        super(pMessage);
        this.errorCode = pCodeErreur;
        this.status = pStatut;
    }

    /**
     * Getter id de la ressource.
     *
     * @return this.resourceId.
     */
    public Long getResourceId() {
        return this.resourceId;
    }

    /**
     * Setter id de la resource.
     *
     * @param pResourceId resourceId
     */
    public void setResourceId(final Long pResourceId) {
        this.resourceId = pResourceId;
    }

    /**
     * Getter code d'erreur.
     *
     * @return this.errorCode.
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * Setter code d'erreur.
     *
     * @param pErrorCode errorCode.
     */
    public void setErrorCode(final String pErrorCode) {
        this.errorCode = pErrorCode;
    }

    /**
     * Getter statut.
     *
     * @return this.status.
     */
    public HttpStatus getStatus() {
        return this.status;
    }

    /**
     * Setter statut.
     *
     * @param pStatus status.
     */
    public void setStatus(final HttpStatus pStatus) {
        this.status = pStatus;
    }
}
