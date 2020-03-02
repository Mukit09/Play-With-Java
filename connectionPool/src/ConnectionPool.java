import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Optional;

public class ConnectionPool {
    private ArrayList<Connection> connections = new ArrayList<>();
    private static ConnectionPool connectionPool;
    private static int MAX_CONNECTION;

    private ConnectionPool() {}

    public static ConnectionPool getConnectionPoolInstance() {
        if(connectionPool == null) {
            synchronized (ConnectionPool.class) {
                if(connectionPool == null) {
                    connectionPool = new ConnectionPool();
                }
            }
        }
        return connectionPool;
    }

    public void createConnections(int n) {
        try {
            MAX_CONNECTION = n;
            Class.forName("com.mysql.jdbc.Driver");
            for(int i = 0;i<n; i++) {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/billing","root","");
                connections.add(connection);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getConnectionPoolSize() {
        return connections.size();
    }

    public Optional<Connection> getConnectionFromPool() {
        Connection connection = null;
        synchronized (this) {
            if (connections.size() > 0) {
                connection = connections.get(connections.size() - 1);
                connections.remove(connection);
            }
        }
        return Optional.ofNullable(connection);
    }

    public boolean giveConnectionBackToConnectionPool(Connection connection) {
        synchronized (this) {
            if (connections.size() < MAX_CONNECTION) {
                connections.add(connection);
                return true;
            }
        }
        return false;
    }
}
