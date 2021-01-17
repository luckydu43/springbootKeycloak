/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.service;

import java.util.stream.Stream;
import com.example.springbootapplication.model.Droit;

/**
 * Service de l'entité Droit.
 *
 * @author l.duperron
 */
public interface IDroitService {

    /**
     * Retourne un droit par son nom.
     *
     * @param pNomDroit nom du droit.
     * @return Droit associé au nom donné en paramètre.
     */
    Droit findByDroitName(String pNomDroit);

    /**
     * Retourne la liste de tous les droits.
     *
     * @return Liste des droits dans un conteneur Stream java 8.
     */
    Stream<Droit> getAllDroitsStream();
}
