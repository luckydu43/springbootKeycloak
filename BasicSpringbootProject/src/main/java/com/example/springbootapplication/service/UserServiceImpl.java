/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.service;

import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.springbootapplication.dao.IUserDAO;
import com.example.springbootapplication.exception.BusinessResourceException;
import com.example.springbootapplication.exception.UserNotFoundException;
import com.example.springbootapplication.model.User;

/**
 * Implémentation de UserService.
 *
 * @author l.duperron
 */
@Component
public class UserServiceImpl implements IUserService {

    /**
     * DAO de l'entité User.
     */
    @Autowired
    private IUserDAO UserDAO;

    /**
     * Constructeur par défaut.
     */
    public UserServiceImpl() {
    }

    @Override
    public User findByLogin(final String pLogin) throws BusinessResourceException {
        return this.UserDAO.findByUserLogin(pLogin);
    }

    @Override
    public Stream<User> getAllUsersStream() {
        return this.UserDAO.findAll().stream();
    }

    @Override
    public User getUserById(final Long pId) throws BusinessResourceException {
        return this.UserDAO.getOne(pId);
    }

    @Override
    @Transactional
    public void saveOrUpdateUser(final User pUser) {
        this.UserDAO.save(pUser);
    }

    @Override
    @Transactional
    public void deleteUser(final User pUser) throws UserNotFoundException {
        this.UserDAO.delete(pUser);
    }
}
