/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServer;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author Seb
 */
public class server {
    private static int port = 80;
    private static String ip = "127.0.0.1";
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
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        
        server.createContext("/", new WelcomeHandler());
//        server.createContext("/file", new FileHandler());
//        server.createContext("/weather", new WeatherService());
        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }
}
