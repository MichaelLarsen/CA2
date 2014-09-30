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
        facade = Facade.getFacade(true);
    }

    @After
    public void tearDown() {
        facade.dropTables();
    }

    @Test
    public void addPerson() {
        Person person = facade.addPerson(gson.toJson(new Person("Sherlock", "Holmes", "12345678", "sherlock@detective.com")));
        String expectedJsonString = gson.toJson(person);
        String actual = facade.getPerson(person.getId());
        assertEquals(expectedJsonString, actual);

    }

    @Test
    public void getPerson() {
        addPerson();
    }

    @Test
    public void getPersons() {
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
