package org.example;

import org.example.model.Game;
import org.example.model.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaCreate {
    public static void main(String[] args) {
        // create games
        Game game1 = new Game();
        game1.setTitle("Cave Escape");
        game1.setGenre("Survival");

        Game game2 = new Game();
        game2.setTitle("Holiday Crossword");
        game2.setGenre("Puzzle");

        // create reviews
        Review review1 = new Review();
        review1.setScore(3);
        review1.setComment("Too easy");

        Review review2 = new Review();
        review2.setScore(7);
        review2.setComment("Fun and challenging");

        Review review3 = new Review();
        review3.setScore(10);
        review3.setComment("Great puzzle!");

        // create game -< reviews associations
        // ADD YOUR CODE HERE


        // create EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // access transaction object
        EntityTransaction transaction = entityManager.getTransaction();

        // create and use transactions
        transaction.begin();

        //persist the games
        //ADD YOUR CODE HERE


        //persist the reviews
        //ADD YOUR CODE HERE


        //commit the transaction
        transaction.commit();

        //close entity manager and factory
        entityManager.close();
        entityManagerFactory.close();
    }
}
