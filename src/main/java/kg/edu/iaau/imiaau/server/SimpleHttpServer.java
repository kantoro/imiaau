/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg.edu.iaau.imiaau.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 *
 * @author Kantoro
 */
public class SimpleHttpServer {
    public static void main(String [] args) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(12345);
        HttpServer server = HttpServer.create(addr, 0);
        
        server.createContext("/", new MyHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server is listening on port 12345" );
    }
}


class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        switch(requestMethod) {
            case "GET": Headers responseHeaders = exchange.getResponseHeaders();
                        responseHeaders.set("Content-Type", "text/plain");
                        exchange.sendResponseHeaders(200, 0);
                        try (OutputStream responseBody = exchange.getResponseBody()) {
                            Headers requestHeaders = exchange.getRequestHeaders();
                            Set<String> keySet = requestHeaders.keySet();
                            Iterator<String> iter = keySet.iterator();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                List values = requestHeaders.get(key);
                                String s = key + " = " + values.toString() + "\n";
                                responseBody.write(s.getBytes());
                            }
                        }
        }
    }
}