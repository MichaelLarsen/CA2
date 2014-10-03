package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entities.RoleSchool;
import facades.Facade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author Michael, Sebastian, Emil og Andreas
 */
class RoleHandler implements HttpHandler {

    private final Facade facade;

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

        if (jsonQuery.contains("<") || jsonQuery.contains(">")) {
            throw new IllegalArgumentException("Illegal characters in input");
        }
        RoleSchool role = facade.addRoleFromGSON(jsonStr, id);
        response = role.toString();

        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(200, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
