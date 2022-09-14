/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe globale de gestion des exceptions.
 *
 * @author l.duperron
 */
@ControllerAdvice(basePackages = {"com.example.springbootapplication"})
public abstract class GlobalHandlerControllerException extends ResponseEntityExceptionHandler {

    /**
     * Constructeur par défaut.
     */
    public GlobalHandlerControllerException() {
    }

    /**
     * Initialisation des données.
     *
     * @param pBinder binder.
     */
    @InitBinder
    public void dataBinding(final WebDataBinder pBinder) {
    }

    /**
     * La variable "technicalError" pourra être exploitée n'importe où dans
     * l'application.
     *
     * @param pModel model.
     */
    @ModelAttribute
    public void globalAttributes(final Model pModel) {
        pModel.addAttribute("technicalError", "Une erreur technique est survenue !");
    }

    /**
     * Interception d'une technicalErrorException.
     *
     * @param pException exception.
     * @return modelAndView.
     */
    @ExceptionHandler(TechnicalErrorException.class)
    public ModelAndView technicalErrorException(final Exception pException) {
        final ModelAndView vModelAndView = new ModelAndView();
        vModelAndView.addObject("exception", pException.getMessage());
        vModelAndView.setViewName("error");
        return vModelAndView;
    }

    /**
     * Toutes les autres erreurs non nominales sont interceptées ici.
     *
     * @param pRequete requête.
     * @param pException exception.
     * @return réponse.
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BusinessResourceExceptionResponse> unknowError(final HttpServletRequest pRequete, final Exception pException) {
        final BusinessResourceExceptionResponse vReponse = new BusinessResourceExceptionResponse();
        vReponse.setErrorCode("Technical Error");
        vReponse.setErrorMessage(pException.getMessage());
        vReponse.setRequestURL(pRequete.getRequestURL().toString());
        return new ResponseEntity<>(vReponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Cas de ressource non trouvée.
     *
     * @param pRequete requête.
     * @param pException exception.
     * @return Reponse.
     */
    @ExceptionHandler(BusinessResourceException.class)
    protected ResponseEntity<BusinessResourceExceptionResponse> resourceNotFound(final HttpServletRequest pRequete, final BusinessResourceException pException) {
        final BusinessResourceExceptionResponse vReponse = new BusinessResourceExceptionResponse();
        vReponse.setStatus(pException.getStatus());
        vReponse.setErrorCode(pException.getErrorCode());
        vReponse.setErrorMessage(pException.getMessage());
        vReponse.setRequestURL(pRequete.getRequestURL().toString());
        return new ResponseEntity<>(vReponse,
                pException.getStatus());
    }
}
