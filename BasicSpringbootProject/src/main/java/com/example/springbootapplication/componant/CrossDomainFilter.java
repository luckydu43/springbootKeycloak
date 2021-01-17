/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.componant;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Classe utilitaire permettant d'indiquer au serveur les entêtes et les
 * requêtes HTTP à prendre en compte.
 *
 * @author l.duperron
 */
@Component
public class CrossDomainFilter extends OncePerRequestFilter {

    /**
     * Constructeur par défaut.
     */
    public CrossDomainFilter() {
    }

    /**
     * Filtre.
     *
     * @param pHttpServletRequest requête.
     * @param pHttpServletResponse réponse.
     * @param pFilterChain filtre.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest pHttpServletRequest, final HttpServletResponse pHttpServletResponse, final FilterChain pFilterChain) throws ServletException, IOException {
        pHttpServletResponse.addHeader("Access-Control-Allow-Origin", "*"); //toutes les URI sont autorisées
        pHttpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        pHttpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, contenttype,accept, x - req");
        pFilterChain.doFilter(pHttpServletRequest, pHttpServletResponse);
    }
}
