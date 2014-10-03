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
 * @author Michael, Sebastian, Emil og Andreas
 */
class WelcomeHandler implements HttpHandler {
    
    private final String contentFolder = "public/";

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
