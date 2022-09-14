/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springbootapplication.dao;

import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.springbootapplication.model.Droit;

/**
 * DAO du droit.
 *
 * ELLE N'EST PAS IMPLEMENTEE EXPLICITEMENT. EH OUAIS ! ________________________
 * SpringData va automatiquement créer une implémentation de cette interface à
 * l'exécution. Allez savoir comment... Y a une IA là-bas dedans j'vous dis ____
 * Mais faut quand même hériter de l'interface JpaRepository, au moins pour
 * profiter du CRUD auto-généré, lui aussi.
 *
 * ... l'annotation @Query doit permettre de jouer plus finement avec la chose.
 *
 * @author l.duperron
 */
public interface IDroitDAO extends JpaRepository<Droit, Long> {

    /**
     * Retourne un droit par son nom.
     *
     * @param pDroitNom Nom du droit.
     * @return rôle identifié par son nom.
     */
    Droit findByDroitNom(String pDroitNom);

    /**
     * Retourne la liste de tous les droits.
     *
     * @return Liste des droits dans un conteneur Stream Java 8.
     */
    @Query("select droit from Droit droit")
    Stream<Droit> getAllDroitsStream();

}
