package com.winkball.whiteboard.jetty;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Starts a jetty server on port 8080 running the WallCaster
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        int port = 9081;

        Server server = new Server(port);
        server.setHandlers(new Handler[] {
                getWhiteboardHandler(server)
        });

        server.start();
        server.join();
    }

    private static Handler getWhiteboardHandler(Server server) {
        // N.B.  If adding new servers in future, add the src/main/resources
        // directory to the maven project.build.resources list in the pom
        // of this project
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setWar("src/main/webapp");
        context.setVirtualHosts(new String[] {"local.whiteboard.com"});
        context.setServer(server);

        return context;
    }
}
