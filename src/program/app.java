/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import entities.Person;
import entities.RoleSchool;
import entities.Student;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Michael
 */
public class app {

    
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("KA_InheritancePU").createEntityManager();
        em.getTransaction().begin();
        
        Person michael = new Person("Michael", "Larsen", "12345678", "studerende@cphbusiness.dk");
        RoleSchool student = new Student("3. semester datamatiker");
//        em.persist(michael);
        em.persist(student);
        student.setPerson(michael);
        
//        em.flush();
        em.getTransaction().commit();
        em.close();

    }
}
