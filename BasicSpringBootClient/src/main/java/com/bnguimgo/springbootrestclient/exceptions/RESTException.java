/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.exceptions;

import org.slf4j.LoggerFactory;

/**
 * Exceptions rencontrées uniquement durant les appels REST.
 *
 * @author l.duperron
 */
public class RESTException {

    /**
     * Constructeur avec message.
     *
     * @param message message à loguer.
     * @param pClasseSourceDeLErreur type de la classe source.
     */
    public RESTException(String message, Class pClasseSourceDeLErreur) {
        LoggerFactory.getLogger(pClasseSourceDeLErreur).error(message);
    }
}
