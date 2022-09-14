/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootapplication.model.User;

/**
 * DAO de l'User.
 *
 * ELLE N'EST PAS IMPLEMENTEE EXPLICITEMENT. EH OUAIS ! ________________________
 * SpringData va automatiquement créer une implémentation de cette interface à
 * l'exécution. Allez savoir comment... Y a une IA là-bas dedans j'vous dis ____
 * Mais faut quand même hériter de l'interface JpaRepository, au moins pour
 * profiter du CRUD auto-généré, lui aussi.
 *
 * @author l.duperron
 */
public interface IUserDAO extends JpaRepository<User, Long> {

    /**
     * Retourne un utilisateur pas son login.
     *
     * @param pUserLogin login de l'utilisateur.
     * @return Utilisateur identifié par son login.
     */
    User findByUserLogin(String pUserLogin);

}
