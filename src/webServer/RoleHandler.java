/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entities.Person;
import entities.RoleSchool;
import entities.Teacher;
import facades.Facade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author Seb
 */
class RoleHandler implements HttpHandler {

    private Facade facade;
    private Gson gson;

    public RoleHandler() {
        facade = Facade.getFacade(true);
    }

    @Override
    public void handle(HttpExchange he) throws IOException {

        String response = "";

        System.out.println("requestbody: " + he.getRequestBody().toString());
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String jsonQuery = br.readLine();
        System.out.println("JsonQuery: " + jsonQuery);

        RoleSchool teacher = new Teacher("Jeg har studeret matematik");
        String teacherGson = gson.toJson(teacher);
        System.out.println("TeacherGson: " + teacherGson);
        
        
        if (jsonQuery.contains("<") || jsonQuery.contains(">")) {
            //Simple anti-Martin check :-)
            throw new IllegalArgumentException("Illegal characters in input");
        }
        RoleSchool role = facade.addRoleFromGSON(teacherGson, 1);
        System.out.println("Role: " + role);
//        if (person.getPhone().length() > 50 || person.getFirstName().length() > 50 || person.getLastName().length() > 70) {
//            //Simple anti-Martin check :-)
//            throw new IllegalArgumentException("Input contains to many characters");
//        }
        response = gson.toJson(role);
        
        System.out.println("Response: " + response);

        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(200, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
//    catch (IllegalArgumentException iae
//
//    
//        ) {
//                    status = 400;
//        response = iae.getMessage();
//    }
//    catch (IOException e
//
//    
//        ) {
//                    status = 500;
//        response = "Internal Server Problem";
//    }

//    }

