package src.routes;

import src.annotation.WebRoute;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by David Szilagyi on 2017. 06. 13..
 */
@WebRoute(path = "/user")
public class UserRoute extends MainRoute {

    @WebRoute(method = "GET")
    public void handleGET(HttpExchange t) throws IOException {
        String response = "\nGET method invoked on \"/user\" path";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute(method = "POST")
    public void handlePOST(HttpExchange t) throws IOException {
        String response = "\nPOST method invoked on \"/user\" path";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute(method = "PUT")
    public void handlePUT(HttpExchange t) throws IOException {
        String response = "\nPUT method invoked on \"/user\" path";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute(method = "DELETE")
    public void handleDELETE(HttpExchange t) throws IOException {
        String response = "\nDELETE method invoked on \"/user\" path";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
