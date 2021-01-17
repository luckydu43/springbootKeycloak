/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.service;

import java.util.stream.Stream;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.example.springbootapplication.exception.BusinessResourceException;
import com.example.springbootapplication.exception.UserNotFoundException;
import com.example.springbootapplication.model.User;

/**
 * Service de l'entité Utilisateur. On y retrouve au moins le CRUD.
 *
 * @author l.duperron
 */
@Configuration
@ComponentScan("com.baeldung.autowire.sample")
public interface IUserService {

    /**
     * Retourne la liste de tous les utilisateurs.
     *
     * @return la liste des utilisateurs dans un conteneur Stream Java 8.
     */
    Stream<User> getAllUsersStream();

    /**
     * Retourne un utilisateur par son id.
     *
     * @param pId id de l'utilisateur.
     * @return utilisateur associé à l'id en paramètre.
     * @throws BusinessResourceException exception.
     */
    User getUserById(Long pId) throws BusinessResourceException;

    /**
     * Retourne un utilisateur par son login.
     *
     * @param pLogin login de l'utilisateur.
     * @return utilisateur associé au login en paramètre.
     * @throws BusinessResourceException exception.
     */
    User findByLogin(String pLogin) throws BusinessResourceException;

    /**
     * Persiste un utilisateur.
     *
     * @param pUser utilisateur à persister.
     * @throws BusinessResourceException exception.
     */
    void saveOrUpdateUser(User pUser) throws BusinessResourceException;

    /**
     * Supprime un utilisateur.
     *
     * @param pUser id de l'utilisateur à supprimer.
     * @throws UserNotFoundException exception.
     */
    void deleteUser(User pUser) throws UserNotFoundException;

}
