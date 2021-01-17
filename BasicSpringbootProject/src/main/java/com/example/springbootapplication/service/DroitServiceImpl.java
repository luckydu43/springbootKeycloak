/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbootapplication.dao.IDroitDAO;
import com.example.springbootapplication.model.Droit;

/**
 * Implémentation de IDroitService.
 *
 * @author l.duperron
 */
@Component
public class DroitServiceImpl implements IDroitService {

    /**
     * DAO de l'entité User.
     */
    @Autowired
    private IDroitDAO droitDAO;

    /**
     * Constructeur par défaut.
     */
    public DroitServiceImpl() {
    }

    /**
     * READ droit.
     *
     * @param pNomDroit nom du droit.
     * @return droit identifié par nom.
     */
    @Override
    public Droit findByDroitName(final String pNomDroit) {
        return this.droitDAO.findByDroitNom(pNomDroit);
    }

    /**
     * READ Droit.
     *
     * @return tous les droits.
     */
    @Override
    public Stream<Droit> getAllDroitsStream() {
        return this.droitDAO.getAllDroitsStream();
    }
}
