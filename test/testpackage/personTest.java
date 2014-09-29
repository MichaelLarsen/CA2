/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import com.google.gson.Gson;
import facades.Facade;
import org.junit.After;
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
        
    }

    @Test
    public void addPerson() {

    }

    @Test
    public void getPerson() {

    }
    
    @Test
    public void getPersons() {

    }
}
