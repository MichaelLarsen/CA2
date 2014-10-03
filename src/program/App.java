package program;

import com.google.gson.Gson;
import entities.Person;
import entities.RoleSchool;
import entities.Student;
import facades.Facade;

/**
 * Class for testing/debugging
 * @author Michael, Sebastian, Emil og Andreas
 */
public class App {

    private static final Facade facade = Facade.getFacade(true);
    private static final Gson gson = new Gson();
    
    public static void main(String[] args) {
        Person michael = new Person("Michael", "Larsen", "12345678", "studerende@cphbusiness.dk");
        facade.addPersonFromGSON(gson.toJson(michael));
        Person emiiiil = new Person("Emiiil", "Fra løndeberg", "12345678", "studerende@cphbusiness.dk");
        facade.addPersonFromGSON(gson.toJson(emiiiil));
        RoleSchool student = new Student("3. semester datamatiker");
        
        System.out.println(facade.getPersonsAsJSON());
        System.out.println("------------------------");
        System.out.println(facade.getPersonAsJSON(1));
        System.out.println("------------------------");
        System.out.println(facade.addPersonFromGSON(gson.toJson(new Person("Andreas", "Løvehjerte", "12345678", "studerende@cphbusiness.dk"))));
        System.out.println("------------------------");
        System.out.println(facade.addRoleFromGSON(gson.toJson(student), 1));
        System.out.println("------------------------");
        System.out.println(facade.delete(2));
        System.out.println("------------------------");
        System.out.println(facade.getPersonsAsJSON());
    }
}
