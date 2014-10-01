/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import com.google.gson.Gson;
import entities.AssistantTeacher;
import entities.Person;
import entities.RoleSchool;
import entities.Student;
import entities.Teacher;
import facades.Facade;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Michael
 */
public class personTest {

    Facade facade;
    Gson gson = new Gson();
//    EntityManager em;

    @Before
    public void setUp() {
        facade = Facade.getFacade(true);
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KA_InheritancePU");
//        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
//        facade.dropTables();
    }

//    @Test
//    public void testPerson() {
//        Person person = new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com");
//        assertEquals(person.getFirstName(), "Sherlock");
//        assertEquals(person.getLastName(), "Holmes");
//        assertEquals(person.getPhone(), "12345678");
//        assertEquals(person.getEmail(), "sherlock@detective.com");
//    }
//
//    @Test
//    public void addPersonToDB() {
//        Person person = facade.addPerson(gson.toJson(new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com")));
//        String expectedJsonString = gson.toJson(person);
//        String actual = facade.getPerson(person.getId());
//        assertEquals(expectedJsonString, actual);
//    }
//
//    @Test
//    public void getPersonFromDB() {
//        addPersonToDB();
//    }
//
//    @Test
//    public void getPersonsFromDB() {
//        Person p1 = new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com");
//        Person person1 = facade.addPerson(gson.toJson(p1));
//        Person p2 = new Person("Mark", "Zuckerberg", "88888888", "M_ZuckerbergCEO@facebook.com");
//        Person person2 = facade.addPerson(gson.toJson(p2));
//
//        //Make the Expected String
//        Map<Long, Person> test = new HashMap();
//        test.put(person1.getId(), person1);
//        test.put(person2.getId(), person2);
//        String expected = gson.toJson(test.values());
//        String result = facade.getPersons();
//        assertEquals(expected, result);
//    }

    @Test
    public void roleSchool() {
        EntityManager em = Persistence.createEntityManagerFactory("KA_InheritancePU").createEntityManager();
        em.getTransaction().begin();
        
        
        Person michael = new Person("Michael", "Larsen", "12345678", "studerende@cphbusiness.dk");
        RoleSchool student = new Student("3. semester datamatiker");
        student.setPerson(michael);
//        Person michael2 = facade.addPerson(gson.toJson(michael));
        em.persist(student);
        
        
        
        
//        RoleSchool student2 = facade.addRole(gson.toJson(student));
//        System.out.println(michael2);
//        System.out.println(student2);
//        
//        
//        student2.setPerson(michael2);
//        
//        System.out.println(michael2);
        
//        em.flush();
        
//        System.out.println(michael.getId());
        
//        em.flush();
        em.getTransaction().commit();
        em.close();
        
        
        
        
    }
}
