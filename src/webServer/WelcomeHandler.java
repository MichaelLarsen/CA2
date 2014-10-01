/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

/**
 *
 * @author Seb
 */
class WelcomeHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        String url = he.getRequestURI().toString();
        System.out.println(url);
    }
    
}
