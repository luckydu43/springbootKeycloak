/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnguimgo.springbootrestclient.beans.utils;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.primefaces.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilitaire permettant de pouvoir taper sur un serveur REST en
 * GET/PUT/POST/DELETE à partir d'une architecture JSF.
 *
 * @author l.duperron
 */
@Named("beanutilrest")
@SessionScoped
public class BeanUtilREST implements Serializable {

    /**
     * UUID généré.
     */
    private static final long serialVersionUID = -4238237311985202585L;

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtilREST.class);

    /**
     * Url du serveur distant par le contrôleur User.
     */
    public static final String URL_SERVEUR_DISTANT_USER = "http://localhost:8080/user/";

    /**
     * Url du serveur distant par le contrôleur Droit.
     */
    public static final String URL_SERVEUR_DISTANT_DROIT = "http://localhost:8080/droit/";

    /**
     * Conteneur exploité dans les requêtes PUT et POST, notamment dans le
     * traitement récursif des attributs de la classe et de ses parentes._______
     * Il contient donc l'ensemble des attributs proposés par la classe et ses
     * parentes.
     */
    private List<Field> attributsDeClasseEtSesMeres;

    /**
     * Constructeur sans paramètres.
     */
    public BeanUtilREST() {
        this.attributsDeClasseEtSesMeres = new ArrayList<>();
    }

    /**
     * Requête HTTP GET.
     *
     * @param <TypeDonneEnParam> Type d'objet retourné par la ressource.
     * @param pTypeObjet Cf ci-dessus.
     * @param pSource adresse de la ressource.
     * @return objet retourné par la ressource.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public <TypeDonneEnParam> TypeDonneEnParam get(final String pSource,
            final Class<TypeDonneEnParam> pTypeObjet) {
        TypeDonneEnParam vObjet = null;
        try {
            CloseableHttpClient vClient = HttpClients.createDefault();

            //On crée la requête GET.
            HttpGet vRequeteGET = new HttpGet(pSource);

            // On l'exécute
            HttpResponse vReponseHTTP = (HttpResponse) vClient.execute(vRequeteGET);

            // On récupère la partie entité
            HttpEntity vDonneeBruteRecuperee = (HttpEntity) vReponseHTTP.getEntity();

            // Mapper qui permet de transformer l'entité JSON en objet JAVA.
            ObjectMapper vMapper = new ObjectMapper();
            // Permet de ne pas planter quand Jackson s'invente des propriétés dans ses entités.
            vMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // Permet de ne retourner qu'une valeur mais qu'elle soit quand même dans un tableau.
            // Bien évidemment le cas nominal (plusieurs valeurs) reste fonctionnel.
            vMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            // Exécution du mapper.
            vObjet = vMapper.readValue(
                    (EntityUtils.toString(vDonneeBruteRecuperee)),
                    pTypeObjet);
        } catch (IOException pException) {
            LOGGER.error("Erreur rencontrée dans la requête REST GET avec cette URL : "
                    + pSource
                    + " dont voici le message : "
                    + pException.getMessage());
        }
        return vObjet;
    }

    /**
     * Requête HTTP POST.
     *
     * @param <TypeDonneEnParam> Type de l'objet à envoyer à la ressource. Il
     * est aussi récupéré, la ressource étant censée le renvoyer après.
     * @param pTypeObjet Cf ci-dessus.
     * @param pContenu Objet à envoyer à la ressource.
     * @param pDestination Adresse de la ressource.
     * @return Objet renvoyé par la ressource après traitement.
     */
    public <TypeDonneEnParam> TypeDonneEnParam post(final Object pContenu,
            final String pDestination,
            final Class<TypeDonneEnParam> pTypeObjet) {
        TypeDonneEnParam vReturn = null;
        try {
            CloseableHttpClient vClient = HttpClients.createDefault();

            // On crée la requête POST.
            HttpPost vRequetePOST = new HttpPost(pDestination);

            // On efface l'eventuel contenu précédent du tableau.
            this.attributsDeClasseEtSesMeres.clear();

            //Parcours des attributs de la classe pTypeObjet en mode introspectif et récursif.
            this.classesParentesEnRecursif(pTypeObjet);

            // On prépare et on remplit le conteneur JSONObject pour l'entity.
            JSONObject vEntiteJSON = this.remplirObjetJSON(pContenu, pTypeObjet);

            // On transforme le JSONObject en entity.
            StringEntity vDonneeBruteAEnvoyer = new StringEntity(
                    vEntiteJSON.toString(),
                    "UTF-8");

            // On ajoute un header à la requête.
            vRequetePOST.addHeader(
                    "content-type",
                    "application/json;charset=UTF-8");

            // On charge l'entity dans la requête.
            vRequetePOST.setEntity(vDonneeBruteAEnvoyer);

            // On exécute la requête.
            HttpResponse vReponseHTTP = (HttpResponse) vClient.execute(vRequetePOST);

            // On récupère la partie entity de la réponse.
            HttpEntity vDonneeBruteRecuperee = (HttpEntity) vReponseHTTP.getEntity();

            // On transforme l'entity de la réponse en objet JAVA.
            vReturn = new ObjectMapper().readValue(
                    (EntityUtils.toString(vDonneeBruteRecuperee)),
                    pTypeObjet);
        } catch (IOException | IllegalArgumentException pException) {
            LOGGER.error("Erreur rencontrée dans la requête REST POST avec cette URL : "
                    + pDestination
                    + " dont voici les détails : "
                    + pException.getMessage());
        }
        return vReturn;
    }

    /**
     * Requête HTTP PUT.
     *
     * @param <TypeDonneEnParam> Type de l'objet à envoyer à la ressource. Il
     * est aussi récupéré, la ressource étant censée le renvoyer après.
     * @param pTypeObjet Cf ci-dessus.
     * @param pContenu Objet à envoyer à la ressource.
     * @param pDestination Adresse de la ressource.
     * @return Objet renvoyé par la ressource après traitement.
     */
    public <TypeDonneEnParam> TypeDonneEnParam updaterUnTruc(
            final Object pContenu,
            final String pDestination,
            final Class<TypeDonneEnParam> pTypeObjet) {
        TypeDonneEnParam vReturn = null;
        try {
            CloseableHttpClient vClient = HttpClients.createDefault();

            // On crée la requête PUT.
            HttpPut vRequetePUT = new HttpPut(pDestination);

            // On efface l'eventuel contenu précédent du tableau.
            this.attributsDeClasseEtSesMeres.clear();

            //Parcours des attributs de la classe pTypeObjet en mode introspectif et récursif.
            this.classesParentesEnRecursif(pTypeObjet);

            // On prépare et on remplit le conteneur JSONObject pour l'entity.
            JSONObject vEntiteJSON = this.remplirObjetJSON(pContenu, pTypeObjet);

            // On transforme le JSONObject en entity.
            StringEntity vDonneeBruteAEnvoyer = new StringEntity(
                    vEntiteJSON.toString(),
                    "UTF-8");

            // On ajoute un header à la requête.
            vRequetePUT.addHeader(
                    "content-type",
                    "application/json;charset=UTF-8");

            // On charge l'entity dans la requête.
            vRequetePUT.setEntity(vDonneeBruteAEnvoyer);

            // On exécute la requête.
            HttpResponse vReponseHTTP = (HttpResponse) vClient.execute(vRequetePUT);

            // On récupère la partie entity de la réponse.
            HttpEntity vDonneeBruteRecuperee = (HttpEntity) vReponseHTTP.getEntity();

            // On transforme l'entity de la réponse en objet JAVA.
            vReturn = new ObjectMapper().readValue(
                    (EntityUtils.toString(vDonneeBruteRecuperee)),
                    pTypeObjet);
        } catch (IOException | IllegalArgumentException pException) {
            LOGGER.error("Erreur rencontrée dans la requête REST PUT avec cette URL : "
                    + pDestination
                    + " dont voici les détails : "
                    + pException.getMessage());
        }
        return vReturn;
    }

    /**
     * Requête HTTP DELETE.
     *
     * @param pDestination Adresse de la ressource.
     */
    public void delete(final String pDestination) {
        // TODO : ajouter du code pour retourner quelque chose afin de gérer les
        // différents cas d'erreur levés dans cette méthode côté client.

        try {
            // On exécute la requête delete.
            HttpClients.createDefault().execute(new HttpDelete(pDestination));
        } catch (IOException pException) {
            LOGGER.warn("Erreur rencontrée dans la requête REST DELETE avec cette URL : "
                    + pDestination
                    + " dont voici les détails : "
                    + pException.getMessage());
        }
    }

    /**
     * Le but de cette méthode récursive est de simplement remonter les classes
     * dont hérite celle en paramètre afin de sortir tous les attributs
     * exploitables pour le remplissage du JSON.
     *
     * @param pClasse classe à tester.
     */
    private void classesParentesEnRecursif(Class pClasse) {
        // Si la classe à une parente
        if (pClasse.getSuperclass() != null) {
            // On ajoute tous ses attributs dans le tableau
            this.attributsDeClasseEtSesMeres.addAll(Arrays.asList(pClasse.getDeclaredFields()));
            // On relance la machine pour sa parente (récursion)
            this.classesParentesEnRecursif(pClasse.getSuperclass());
        }
        // Sinon on arrête là. Fin de la récursion.
    }

    /**
     * Pour chaque attribut (Field) identifié dans la classe et ses mères, on y
     * met son nom et sa valeur dans un JSON qui est ensuite renvoyé.
     *
     * @param pContenu Objet contenant les valeurs.
     * @param pTypeObjet classe de cet objet.
     * @return conteneur JSON.
     */
    private JSONObject remplirObjetJSON(final Object pContenu,
            final Class pTypeObjet) {
        // Conteneur JSON. Il sera chargé dans la partie entité de la requête.
        JSONObject vEntiteJSON = new JSONObject();

        // On parcours ensuite l'ensemble de ces attributs et on les met dans le JSON.
        for (Field vField : this.attributsDeClasseEtSesMeres) {
            try {
                // Evite le "can not access a member of class with modifiers private"
                vField.setAccessible(true);

                // On charge dans le conteneur JSON une ligne avec le nom de l'attribut et sa valeur.
                vEntiteJSON.put(vField.getName(), vField.get(pContenu));
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                LOGGER.warn("Levée d'exception lors de l'introspection d'une instance de "
                        + pTypeObjet.getName()
                        + " en paramètre dans la méthode pré-POST"
                        + ex.getMessage());
            }
        }
        return vEntiteJSON;
    }
}
