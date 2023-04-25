package community.redrover.mercuryit;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;


public class MercuryITSQL {

    private Connection connection;

    @SneakyThrows
    public MercuryITSQL connection(String connect, String user, String password) {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(connect, user, password);
        return this;
    }

    @SneakyThrows
    public MercuryITSQLResponse open(String query) {
        return new MercuryITSQLResponse(connection.createStatement().executeQuery(query));
    }
}
