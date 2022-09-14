/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.controller.user;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootapplication.model.Droit;
import com.example.springbootapplication.model.User;
import com.example.springbootapplication.service.IDroitService;
import com.example.springbootapplication.service.IUserService;

/**
 * Services CRUD de l'entité User.
 *
 * @author l.duperron
 */
@Controller
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/user/*")
public class UserController {

    /**
     * Constructeur par défaut.
     */
    public UserController() {
    }

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Service de l'entité User.
     */
    @Autowired
    private IUserService userService;

    /**
     * Service de l'entité Droit.
     */
    @Autowired
    private IDroitService droitService;

    /**
     * CREATE/UPDATE user.
     *
     * @param pUser user à sauvegarder.
     * @return User.
     */
    @PostMapping(value = "/saveuser")
    @Transactional
    public ResponseEntity<User> saveUser(final @RequestBody User pUser) {

        /**
         * Définition des droits à mettre.
         */
        final Set<Droit> vDroits = new HashSet<>();
        final Droit vDroitUser = new Droit("DROIT_USER");
        vDroits.add(vDroitUser);
        pUser.setDroits(vDroits);

        /**
         * Récupération des droits selon la BDD.
         */
        final Set<Droit> vDroitFromDB = this.extractDroit(pUser.getDroits(), this.droitService.getAllDroitsStream());
        pUser.getDroits().removeAll(pUser.getDroits());

        /**
         * Affectation des droits et persistance de l'utilisateur.
         */
        pUser.setDroits(vDroitFromDB);
        this.userService.saveOrUpdateUser(pUser);
        LOGGER.warn("SaveUser : " + pUser.toString());
        return new ResponseEntity<>(pUser, HttpStatus.CREATED);
    }

    /**
     * CREATE/UPDATE user.
     *
     * @param pUser user à sauvegarder.
     * @return User.
     */
    @PostMapping(value = "/saveadmin")
    @Transactional
    public ResponseEntity<User> saveAdmin(final @RequestBody User pUser) {

        /**
         * Définition des droits à mettre.
         */
        final Set<Droit> vDroits = new HashSet<>();
        final Droit vDroitAdmin = new Droit("DROIT_ADMIN");
        final Droit vDroitUser = new Droit("DROIT_USER");
        vDroits.add(vDroitAdmin);
        vDroits.add(vDroitUser);
        pUser.setDroits(vDroits);

        /**
         * Récupération des droits selon la BDD.
         */
        final Set<Droit> vDroitFromDB = this.extractDroit(pUser.getDroits(), this.droitService.getAllDroitsStream());
        pUser.getDroits().removeAll(pUser.getDroits());

        /**
         * Affectation des droits et persistance de l'utilisateur.
         */
        pUser.setDroits(vDroitFromDB);
        this.userService.saveOrUpdateUser(pUser);
        LOGGER.warn("SaveAdmin : " + pUser.toString());
        return new ResponseEntity<>(pUser, HttpStatus.CREATED);
    }

    /**
     * méthode privée, READ des droits.
     *
     * @param pSetDroitsFromUser Droits d'après l'User.
     * @param pStreamDroitsFromDB Droits d'après la BDD.
     * @return Set de droits.
     */
    private Set<Droit> extractDroit(final Set<Droit> pSetDroitsFromUser, final Stream<Droit> pStreamDroitsFromDB) {
        return pStreamDroitsFromDB
                .filter((Droit pDroitFromDB) -> pSetDroitsFromUser.stream()
                        .map(pDroitFromUser -> pDroitFromUser.getDroitNom())
                        .anyMatch(pDroitFromUser -> pDroitFromDB.getDroitNom().equals(pDroitFromUser))
                )
                .collect(Collectors.toSet());
    }

    /**
     * READ users.
     *
     * @return Stream de users.
     */
    @GetMapping(value = "/readall")
    public ResponseEntity<Stream<User>> getAllUsers() {
        final Stream<User> vUsers = this.userService.getAllUsersStream();
        LOGGER.warn("liste des utilisateurs : " + vUsers.toString());
        return new ResponseEntity<>(vUsers, HttpStatus.FOUND);
    }

    /**
     * READ User.
     *
     * @param pLogin login.
     * @return User identifié par son login et code HTTP.
     */
    @GetMapping(value = "/read{loginName}")
    public ResponseEntity<User> findUserByLogin(final @PathVariable("loginName") String pLogin) {
        final User vUser = this.userService.findByLogin(pLogin);
        LOGGER.warn("Utilisateur trouvé avec " + pLogin + " : " + vUser);
        return new ResponseEntity<>(vUser, HttpStatus.FOUND);
    }

    /**
     * UPDATE User.
     *
     * @param pId id User.
     * @param pUser User.
     * @return User mis à jour avec code HTTP.
     */
    @PutMapping(value = "/update{id}")
    public ResponseEntity<User> updateUser(
            final @PathVariable(value = "id") Long pId,
            final @RequestBody User pUser) {
        StringBuilder vSb = new StringBuilder();
        LOGGER.warn("Voilà ce qu'on récupère :");
        vSb.append("Etat : ");
        if (pUser.getEtatActivite() != null) {
            vSb.append(pUser.getEtatActivite()
                    .toString());
        } else {
            vSb.append("null");
        }
        vSb.append(", Login : ")
                .append(pUser.getUserLogin())
                .append(", Id : ")
                .append(pUser.getUserId())
                .append(", Droits : ");
        if (pUser.getDroits() != null) {
            vSb.append(pUser.getDroits()
                    .toString());
        } else {
            vSb.append("null");
        }
        LOGGER.warn(vSb.toString());
        final User vUserToUpdate = this.userService.getUserById(pId);
        if (vUserToUpdate == null) {
            LOGGER.warn("L'utilisateur avec l'identifiant "
                    + pId
                    + " n'existe pas");
            return new ResponseEntity<>(pUser, HttpStatus.NOT_FOUND);
        }
        LOGGER.warn(
                "UPDATE ROLE: "
                + vUserToUpdate.getDroits().toString());
        vUserToUpdate.setUserLogin(pUser.getUserLogin());
        vUserToUpdate.setEtatActivite(pUser.getEtatActivite());
        this.userService.saveOrUpdateUser(vUserToUpdate);
        return new ResponseEntity<>(vUserToUpdate, HttpStatus.OK);
    }

    /**
     * DELETE User.
     *
     * @param pUser id de l'User à supprimer.
     * @return void.
     */
    @DeleteMapping(value = "/delete{id}")
    public ResponseEntity<Void> deleteUser(final @PathVariable(value = "id") User pUser) {
        LOGGER.warn("userDelete : " + pUser.toString());
        this.userService.deleteUser(pUser);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
