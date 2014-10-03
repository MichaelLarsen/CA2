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

        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String jsonQuery = br.readLine();
        int lastIndex = jsonQuery.lastIndexOf("}");
        String idStr = jsonQuery.substring(lastIndex + 1);
        String jsonStr = jsonQuery.substring(0, lastIndex + 1);
        int id = Integer.parseInt(idStr);

//        if (jsonQuery.contains("<") || jsonQuery.contains(">")) {
//            //Simple anti-Martin check :-)
//            throw new IllegalArgumentException("Illegal characters in input");
//        }
        RoleSchool role = facade.addRoleFromGSON(jsonStr, id);
        System.out.println("Role: " + role);
//        if (person.getPhone().length() > 50 || person.getFirstName().length() > 50 || person.getLastName().length() > 70) {
//            //Simple anti-Martin check :-)
//            throw new IllegalArgumentException("Input contains to many characters");
//        }
        response = role.toString();
//        System.out.println("RoleJSON" + gson.toJson(role));

        System.out.println("Response: " + response);

        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(200, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
