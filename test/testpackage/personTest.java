/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import com.google.gson.Gson;
import entities.Person;
import entities.RoleSchool;
import entities.Student;
import facades.Facade;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Michael
 */
public class personTest {

    Facade facade;
    Gson gson = new Gson();

    @Before
    public void setUp() {
        facade = Facade.getFacade(true);
    }

    @After
    public void tearDown() {
        facade.dropTables();
    }

    @Test
    public void testPerson() {
        Person person = new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com");
        assertEquals(person.getFirstName(), "Sherlock");
        assertEquals(person.getLastName(), "Holmes");
        assertEquals(person.getPhone(), "12345678");
        assertEquals(person.getEmail(), "sherlock@detective.com");
    }

    @Test
    public void addPersonToDB() {
        Person person = facade.addPersonFromGSON(gson.toJson(new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com")));
        String expectedJsonString = gson.toJson(person);
        String actual = facade.getPersonAsJSON(person.getId());
        assertEquals(expectedJsonString, actual);
    }

    @Test
    public void getPersonFromDB() {
        addPersonToDB();
    }

    @Test
    public void getPersonsFromDB() {
        Person p1 = new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com");
        Person person1 = facade.addPersonFromGSON(gson.toJson(p1));
        Person p2 = new Person("Mark", "Zuckerberg", "88888888", "M_ZuckerbergCEO@facebook.com");
        Person person2 = facade.addPersonFromGSON(gson.toJson(p2));

        //Make the Expected String
        Map<Long, Person> test = new HashMap();
        test.put(person1.getId(), person1);
        test.put(person2.getId(), person2);
        String expected = gson.toJson(test.values());
        String result = facade.getPersonsAsJSON();
        assertEquals(expected, result);
    }

    @Test
    public void addRoleToPerson() {
        Person michael = new Person("Michael", "Larsen", "12345678", "studerende@cphbusiness.dk");
        RoleSchool student = new Student("3. semester datamatiker");
        Person person = facade.addPersonFromGSON(gson.toJson(michael));
        RoleSchool role = facade.addRoleFromGSON(gson.toJson(student), person.getId());
        assertEquals(student.getRoleName(), role.getRoleName());
    }
    
    @Test
    public void deletePerson(){
        Person p1 = new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com");
        Person person1 = facade.addPersonFromGSON(gson.toJson(p1));
        Person p2 = new Person("Mark", "Zuckerberg", "88888888", "M_ZuckerbergCEO@facebook.com");
        facade.addPersonFromGSON(gson.toJson(p2));
        String beforeDelete = facade.getPersonsAsJSON();
        Person deletedPerson = facade.delete(person1.getId());
        String afterDelete = facade.getPersonsAsJSON();
        
        assertEquals(deletedPerson, person1);
        assertFalse(beforeDelete.equals(afterDelete));
    }
}
