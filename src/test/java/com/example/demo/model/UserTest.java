package com.example.demo.model;

import org.junit.Assert;
import org.junit.jupiter.api.*;


class UserTest {

    @BeforeAll
    static void before() {
        // Exécutée une seule fois avant le lancement de tous les tests
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void setUp() {
        // Exécutée avant le lancement de chaque test de la classe
        System.out.println("@BeforeEach");
    }

    @AfterEach
    void tearDown() {
        // Exécutée après le lancement de chaque test de la classe
        System.out.println("@AfterEach");
    }

    @AfterAll
    static void after() {
        // Exécutée une seule fois après le lancement de tous les tests de la classe
        System.out.println("@AfterAll");
    }

    @Test
    void testExample() {
        int value = 100;
        Assertions.assertEquals(100, value);
        // Assertions est une classe définie par JUnit
        // Assert equals permet de vérifier qu'une valeur est égale à une autre.
    }

    // GIVEN - WHEN - THEN
    @Test
    void testConstructor() {
        // GIVEN
        int id = 1;
        String username = "John DOE";
        String password = "azerty";

        // WHEN
        User user = new User(id, username, password);

        // THEN
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertEquals(password, user.getPassword());
    }

    @Test
    void getId() {
    }

    @Test
    void getUsername() {
    }

    @Test
    void getPassword() {
    }
}