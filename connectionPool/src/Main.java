import java.sql.Connection;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        System.out.println("App started...");
        ConnectionPool.getConnectionPoolInstance().createConnections(10);
        System.out.println("Total Connections: " + ConnectionPool.getConnectionPoolInstance().getConnectionPoolSize());

        Optional<Connection> optionalConnection = ConnectionPool.getConnectionPoolInstance().getConnectionFromPool();
        System.out.println("Now Total Connections: " + ConnectionPool.getConnectionPoolInstance().getConnectionPoolSize());

        if(optionalConnection.isPresent()) {
            Connection connection = optionalConnection.get();
            boolean isGivenBack = ConnectionPool.getConnectionPoolInstance()
                    .giveConnectionBackToConnectionPool(connection);
            System.out.println("Connection is given back: " + isGivenBack);
        } else {
            System.out.println("No Connection Available In Connection Pool");
        }
        System.out.println("Now Total Connections: " + ConnectionPool.getConnectionPoolInstance().getConnectionPoolSize());
        System.out.println("App closed...");
    }
}
