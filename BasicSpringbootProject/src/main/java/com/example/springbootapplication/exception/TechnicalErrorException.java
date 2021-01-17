/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.exception;

/**
 * Exception technical error.
 *
 * @author l.duperron
 */
public class TechnicalErrorException extends RuntimeException {

    /**
     * UUID généré.
     */
    private static final long serialVersionUID = -811807278404114373L;

    /**
     * Id.
     */
    private Long id;

    /**
     * Constructeur vide.
     */
    public TechnicalErrorException() {
        super();
    }

    /**
     * Constructeur avec message.
     *
     * @param pMessage message.
     */
    public TechnicalErrorException(final String pMessage) {
        super(pMessage);
    }

    /**
     * Constructeur avec cause Throwable.
     *
     * @param pCause cause.
     */
    public TechnicalErrorException(final Throwable pCause) {
        super(pCause);
    }

    /**
     * Constructeur avec message et cause Throwable.
     *
     * @param pMessage message.
     * @param pThrowable cause.
     */
    public TechnicalErrorException(final String pMessage, final Throwable pThrowable) {
        super(pMessage, pThrowable);
    }

    /**
     * Constructeur avec id.
     *
     * @param pId id.
     */
    public TechnicalErrorException(final Long pId) {
        super(pId.toString());
        this.id = pId;
    }

    /**
     * Getter id.
     *
     * @return this.id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter id.
     *
     * @param pId id.
     */
    public void setId(final Long pId) {
        this.id = pId;
    }
}
