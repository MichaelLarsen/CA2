/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Michael
 */
public class app {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("KA_InheritancePU").createEntityManager();
        Person person = new Person("Michael", "Larsen", "12345678", "mla@mla.dk");
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();

    }
}
