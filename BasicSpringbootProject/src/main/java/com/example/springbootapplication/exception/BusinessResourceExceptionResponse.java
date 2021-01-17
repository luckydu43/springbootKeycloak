/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.exception;

import org.springframework.http.HttpStatus;

/**
 * Reponse de l'exception business resource.
 *
 * @author l.duperron
 */
class BusinessResourceExceptionResponse {

    /**
     * Code d'erreur.
     */
    private String errorCode;

    /**
     * Message.
     */
    private String errorMessage;

    /**
     * URL de la requête.
     */
    private String requestURL;

    /**
     * Statut.
     */
    private HttpStatus status;

    /**
     * Constructeur vide.
     */
    public BusinessResourceExceptionResponse() {
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
     * @param pErrorCode code d'erreur.
     */
    public void setErrorCode(final String pErrorCode) {
        this.errorCode = pErrorCode;
    }

    /**
     * Getter message.
     *
     * @return this.errorMessage.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Setter message.
     *
     * @param pErrorMessage message.
     */
    public void setErrorMessage(final String pErrorMessage) {
        this.errorMessage = pErrorMessage;
    }

    /**
     * Setter url de la requete.
     *
     * @param pUrl url.
     */
    public void setRequestURL(final String pUrl) {
        this.requestURL = pUrl;

    }

    /**
     * Getter url de la requête.
     *
     * @return this.requestURL.
     */
    public String getRequestURL() {
        return this.requestURL;
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
     * @param pStatus statut.
     */
    public void setStatus(final HttpStatus pStatus) {
        this.status = pStatus;
    }
}
