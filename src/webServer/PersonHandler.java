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
import facades.Facade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Seb
 */
class PersonHandler implements HttpHandler {

    private Facade facade;
    private Gson gson;

    public PersonHandler() {
        facade = Facade.getFacade(true);
        gson = new Gson();
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String response = "";
        int status = 200;
        
//        String url = "http://localhost:8080" + he.getRequestURI().toString();
//        System.out.println("address: " + url);
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestMethod("POST");
        
//        String method = he.getRequestMethod().toUpperCase();
        String method = "POST";
        System.out.println("Method: " + method);


        switch (method) {
            case "GET":
                try {
                    String path = he.getRequestURI().getPath();
                    System.out.println("path: " + path);
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) {  //person/id
                        String idStr = path.substring(lastIndex + 1);
                        int id = Integer.parseInt(idStr);
                        response = facade.getPersonAsJSON(id);
                    }
                    else { // person
                        response = facade.getPersonsAsJSON();
                    }
                }
                catch (NumberFormatException nfe) {
                    response = "Id is not a number";
                    status = 404;
                }
                catch (Exception e) {
                    response = e.getMessage();
                    status = 404;
                }
                break;
            case "POST":
                try {
                    System.out.println("POST nu");
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonQuery = br.readLine();
                    if (jsonQuery.contains("<") || jsonQuery.contains(">")) {
                        //Simple anti-Martin check :-)
                        throw new IllegalArgumentException("Illegal characters in input");
                    }
                    Person person = facade.addPersonFromGSON(jsonQuery);
                    if (person.getPhone().length() > 50 || person.getFirstName().length() > 50 || person.getLastName().length() > 70) {
                        //Simple anti-Martin check :-)
                        throw new IllegalArgumentException("Input contains to many characters");
                    }
                    response = gson.toJson(person);
                }
                catch (IllegalArgumentException iae) {
                    status = 400;
                    response = iae.getMessage();
                }
                catch (IOException e) {
                    status = 500;
                    response = "Internal Server Problem";
                }
                break;
            case "PUT":
                break;
            case "DELETE":
                try {
                    System.out.println("DELETE nu");
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) {  //person/id
                        int id = Integer.parseInt(path.substring(lastIndex + 1));
                        Person personDeleted = facade.delete(id);
                        response = new Gson().toJson(personDeleted);
                    }
                    else {
                        status = 400;
                        response = "<h1>Bad Request</h1>No id supplied with request";
                    }
                }
                catch (Exception e) {
                    status = 404;
                    response = e.getMessage();
                }
//                    catch (NumberFormatException nfe) {
//                        response = "Id is not a number";
//                        status = 404;
//                    }
                break;
        }
        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(status, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

}
