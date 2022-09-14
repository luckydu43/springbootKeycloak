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
public class UserNotFoundException extends TechnicalErrorException {

    /**
     * UUID généré.
     */
    private static final long serialVersionUID = 4653123397728354113L;

    /**
     * Constructeur vide.
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Constructeur avec message.
     *
     * @param pMessage message.
     */
    public UserNotFoundException(final String pMessage) {
        super(pMessage);
    }

    /**
     * Constructeur avec cause Throwable.
     *
     * @param pCause cause.
     */
    public UserNotFoundException(final Throwable pCause) {
        super(pCause);
    }

    /**
     * Constructeur avec message et cause Throwable.
     *
     * @param pMessage message.
     * @param pThrowable cause.
     */
    public UserNotFoundException(final String pMessage, final Throwable pThrowable) {
        super(pMessage, pThrowable);
    }

    /**
     * Constructeur avec id.
     *
     * @param pId id.
     */
    public UserNotFoundException(final Long pId) {
        super(pId);
    }
}
