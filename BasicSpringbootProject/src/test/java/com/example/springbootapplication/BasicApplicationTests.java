package com.example.springbootapplication;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Tests de base de l'appli.
 *
 * @author l.duperron
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class BasicApplicationTests {

    /**
     * Constructeur vide.
     */
    public BasicApplicationTests() {
    }

    /**
     * Test de bon fonctionnement de l'application tout court.
     */
    //@Ignore
    @Test
    public void contextLoads() {
    }

}
