package no.toreb;

import lombok.experimental.UtilityClass;
import org.hsqldb.Server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@UtilityClass
public class DatabaseServerRunner {

    public static void main(final String[] args) throws IOException {
        startHsqlDbServer();
    }

    public static void startHsqlDbServer() throws IOException {
        final Server server = new Server();
        server.setDatabasePath(0, "file:./build/application");
        server.setDatabaseName(0, "application");
        server.setPort(9090);
        server.setLogWriter(new PrintWriter(new BufferedWriter(new FileWriter("./build/hsqldb-server.log"))));
        server.start();
    }
}