/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Seb
 */
class WelcomeHandler implements HttpHandler {
    
    private String contentFolder = "src/htmlFiles/";

    @Override
    public void handle(HttpExchange he) throws IOException {
        String url = he.getRequestURI().toString();
        System.out.println("URL: " + url);
        File file = new File(contentFolder + url);
        byte[] bytesToSend = new byte[(int) file.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        bis.read(bytesToSend, 0, bytesToSend.length);
        he.sendResponseHeaders(200, bytesToSend.length);
        try (OutputStream os = he.getResponseBody())
        {
            os.write(bytesToSend, 0, bytesToSend.length);
        }
    }

}
