/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.springbootapplication.controller.PropertiesConfigurationService;
import com.example.springbootapplication.dto.UserDTO;
import com.example.springbootapplication.model.User;

/**
 *
 * @author l.duperron
 */
public class LoginController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * Template.
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Service de configuration.
     */
    @Autowired
    private PropertiesConfigurationService configurationService;

    /**
     * initialisation du login.
     *
     * @param pModel model.
     * @return model avec login.
     */
    @GetMapping(value = "/login")
    public Map<String, Object> loginView(final Map<String, Object> pModel) {
        final Map<String, Object> vModelAvecLogin = new HashMap<>();
        vModelAvecLogin.putAll(pModel);
        vModelAvecLogin.put("userForm", new UserDTO());
        return vModelAvecLogin;
    }

    /**
     * Login.
     *
     * @param pUserForm user form.
     * @param pBindingResult binding result.
     * @param pModelAndView model and view.
     * @return modelAndView.
     */
    @PostMapping(value = "/login")
    public ModelAndView login(final @Valid @ModelAttribute("userForm") UserDTO pUserForm,
            final BindingResult pBindingResult, final ModelAndView pModelAndView) {
        final ModelAndView vModelAndView = (ModelAndView) pModelAndView;
        vModelAndView.setViewName("loginForm");
        if (pBindingResult.hasErrors()) {
            return vModelAndView;
        }
        final Map<String, String> vVariables = new HashMap<>(1);
        vVariables.put("loginName", pUserForm.getUserLogin());

        final ResponseEntity<User> vUserExists = this.restTemplate.getForEntity(this.configurationService.getUrl() + "user/users/{loginName}", User.class, vVariables);
        final User vUser = vUserExists.getBody();
        if (vUser == null) {//ceci nous évite d'écrire une page de gestion des erreurs
            vModelAndView.addObject("saveError", "Aucun utilisateur trouvé avec ce compte");
            return vModelAndView;
        }
        vModelAndView.setViewName("loginSuccess");
        return vModelAndView;
    }

    /**
     * Enregistrement d'un utilisateur.
     *
     * @param pLogin login.
     * @param pBindingResult bindingResult.
     * @param pModelAndView modelAndView.
     * @return modelAndView modelAndView.
     */
    @PostMapping(value = "/registration")
    public ModelAndView saveUser(final @RequestBody String pLogin, final BindingResult pBindingResult, final ModelAndView pModelAndView) {
        pModelAndView.setViewName("registration");
        if (pBindingResult.hasErrors()) {
            return pModelAndView;
        }
        final Map<String, String> vVariables = new HashMap<>(1);
        vVariables.put("loginName", pLogin);

        final ResponseEntity<User> vUserEntity = this.restTemplate.getForEntity(this.configurationService.getUrl() + "user/users/{loginName}", User.class, vVariables);
        final User vUserExists = vUserEntity.getBody();
        if (vUserExists != null) {//ceci nous évite d'écrire une page de gestion des erreurs
            LOGGER.info("userExists", vUserExists.toString());
            pBindingResult.rejectValue("login", "error.user", "Un utilisateur est déjà enregistré avec ce compte");//Note: la 87. return modelAndView;
        }

        //return "loginSuccess";
        final ResponseEntity<User> vUserEntitySaved = this.restTemplate.postForEntity(
                this.configurationService.getUrl() + "user/users",
                new User(),
                User.class);
        //point de liaison entre le client REST et le serveur REST grace à RestTemplate
        final User vUserSaved = vUserEntitySaved.getBody();
        if (null == vUserSaved) {
            pModelAndView.addObject("saveError", "erreur de création du compte.");
            return pModelAndView;
        }

        pModelAndView.setViewName("loginSuccess");
        pModelAndView.addObject("userSaved", vUserSaved);
        return pModelAndView;
    }
}
