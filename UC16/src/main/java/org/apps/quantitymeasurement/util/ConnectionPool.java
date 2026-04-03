package org.apps.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class ConnectionPool {

    private List<Connection> pool = new ArrayList<>();

    public ConnectionPool() {
        try {
            int size = Integer.parseInt(ApplicationConfig.get("db.pool.size"));

            for (int i = 0; i < size; i++) {

                Connection con = DriverManager.getConnection(
                        ApplicationConfig.get("db.url"),
                        ApplicationConfig.get("db.username"),
                        ApplicationConfig.get("db.password")
                );

                // ✅ ADD THIS BLOCK HERE
                con.createStatement().execute(
                        "CREATE TABLE IF NOT EXISTS measurement (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY," +
                                "type VARCHAR(50)," +
                                "operation VARCHAR(50)," +
                                "measurement_value DOUBLE," +
                                "timestamp TIMESTAMP)"
                );

                pool.add(con);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Connection getConnection() {
        if (pool.isEmpty()) throw new RuntimeException("No connection available");
        return pool.remove(0);
    }

    public synchronized void releaseConnection(Connection con) {
        pool.add(con);
    }
}