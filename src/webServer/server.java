/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import entities.Person;
import entities.RoleSchool;
import entities.Student;
import facades.Facade;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author Seb
 */
public class server {
    
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
            port = Integer.parseInt(args[0]);
            ip = args[1];
        }
        
        Facade facade = Facade.getFacade(true);
        
        Person michael = new Person("Michael", "Larsen", "12345678", "studerende@cphbusiness.dk");
        facade.addPersonFromGSON(gson.toJson(michael));
        Person emiiiil = new Person("Emiiil", "Fra løndeberg", "12345678", "studerende@cphbusiness.dk");
        facade.addPersonFromGSON(gson.toJson(emiiiil));
        RoleSchool student = new Student("3. semester datamatiker");
        
        
        
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/", new WelcomeHandler());
        server.createContext("/Person", new PersonHandler());
        server.createContext("/Role", new RoleHandler());
//        server.createContext("/file", new FileHandler());
//        server.createContext("/weather", new WeatherService());
        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }
}
