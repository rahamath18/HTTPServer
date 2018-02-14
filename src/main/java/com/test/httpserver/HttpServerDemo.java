package com.test.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerDemo {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/welcome", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
        	
        	BufferedReader in = null;
        	//System.out.println(t.getRequestHeaders());
        	in = new BufferedReader(new InputStreamReader(t.getRequestBody()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				String response = line + ", RESPONSE_MESSAGE=I am from HTTP Server";
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
			}
        }
    }
}