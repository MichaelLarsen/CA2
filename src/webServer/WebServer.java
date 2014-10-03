package webServer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import entities.AssistantTeacher;
import entities.Person;
import entities.RoleSchool;
import entities.Student;
import entities.Teacher;
import facades.Facade;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author Michael, Sebastian, Emil og Andreas
 */
public class WebServer {
    
    private static int port = 8080;
    private static String ip = "127.0.0.1";
    private static final Gson gson = new Gson();
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        if (args.length == 2)
        {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        
        Facade facade = Facade.getFacade(true);
        
        Person michael = new Person("Michael", "Larsen", "12345678", "studerende@cphbusiness.dk");
        facade.addPersonFromGSON(gson.toJson(michael));
        Person emiiiil = new Person("Emiiil", "Fra løndeberg", "12345678", "studerende@cphbusiness.dk");
        facade.addPersonFromGSON(gson.toJson(emiiiil));
        Person elmo = new Person("Elmo", "Fra skogshoged", "888888", "studerende@iness.dk");
        facade.addPersonFromGSON(gson.toJson(elmo));
        RoleSchool student = new Student("3. semester datamatiker");
        RoleSchool teacher = new Teacher("MATEMATIK");
        RoleSchool assTeacher = new AssistantTeacher();
        facade.addRoleFromGSON(gson.toJson(student), 1);
        facade.addRoleFromGSON(gson.toJson(assTeacher), 1);
        facade.addRoleFromGSON(gson.toJson(teacher), 1);
        System.out.println("PERSONSFSAFDFAS: " + michael.getRoles());
        
        
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/", new WelcomeHandler());
        server.createContext("/Person", new PersonHandler());
        server.createContext("/Role", new RoleHandler());
        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Started the server, listening on:");
        System.out.println("port: " + port);
        System.out.println("ip: " + ip);
    }
}
