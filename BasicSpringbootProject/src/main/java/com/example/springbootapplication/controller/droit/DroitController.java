/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.controller.droit;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootapplication.model.Droit;
import com.example.springbootapplication.service.IDroitService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.transaction.Transactional;

/**
 * Services CRUD de l'entité Droit.
 *
 * @author l.duperron
 */
@Controller
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/droit/*")
public class DroitController {

    /**
     * Constructeur par défaut.
     */
    public DroitController() {
    }

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DroitController.class);

    /**
     * Service de l'entité Droit.
     */
    @Autowired
    private IDroitService droitService;

    /**
     * READ users.
     *
     * @return Stream de users.
     */
    @GetMapping(value = "/readall")
    @Transactional
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ResponseEntity<Stream<Droit>> getAllDroits() {
        final Stream<Droit> vDroits = this.droitService.getAllDroitsStream();
        LOGGER.warn("liste des droits : " + vDroits.toString());
        return new ResponseEntity<>(vDroits, HttpStatus.FOUND);
    }
}
