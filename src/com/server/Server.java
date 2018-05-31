package com.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class Server {

	public static void main(String[] args) throws IOException {
        System.out.println("Starting Embedded server...\n");
        HttpServer server = createHttpServer();
        server.start();
        System.out.println(getURI());
        System.out.println("Started Embedded server Successfully !!!");
    }
 
    private static HttpServer createHttpServer() throws IOException {
        ResourceConfig resourceConfig = new PackagesResourceConfig("com.transfer.controller");
        return HttpServerFactory.create(getURI(), resourceConfig);
    }
 
    private static URI getURI() {
        return UriBuilder.fromUri("http://" + getLocalHostName() + "/").port(8085).build();
    }
 
    private static String getLocalHostName() {
        String hostName = "localhost";
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }

}
