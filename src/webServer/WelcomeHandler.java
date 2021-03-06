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
 * @author Michael
 */
class WelcomeHandler implements HttpHandler {
    private final String contentFolder = "public/";

    public WelcomeHandler() {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        File file = new File(contentFolder + "index.html");
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
