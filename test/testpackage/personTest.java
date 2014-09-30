/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import com.google.gson.Gson;
import entities.Person;
import facades.Facade;
import java.util.HashMap;
import java.util.Map;
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

    @Before
    public void setUp() {
        facade = Facade.getFacade(true, false);
//        facade = Facade.getFacade(true, true);
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
        Person person = facade.addPerson(gson.toJson(new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com")));
        String expectedJsonString = gson.toJson(person);
        String actual = facade.getPerson(person.getId());
        assertEquals(expectedJsonString, actual);
    }

    @Test
    public void getPersonFromDB() {
        addPersonToDB();
    }

    @Test
    public void getPersonsFromDB() {
        Person p = new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com");
        Person person1 = facade.addPerson(gson.toJson(p));
        Person p2 = new Person("Mark", "Zuckerberg", "88888888", "M_ZuckerbergCEO@facebook.com");
        Person person2 = facade.addPerson(gson.toJson(p2));

        //Make the Expected String
        Map<Long, Person> test = new HashMap();
        test.put(person1.getId(), person1);
        test.put(person2.getId(), person2);
        String expected = gson.toJson(test.values());
        String result = facade.getPersons();
        assertEquals(expected, result);
    }
}
