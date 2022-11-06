package persistence.SQL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class SQLConnector {
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String IP_KEY = "IP";
    private static final String PORT_KEY = "port";
    private static final String DATABASE_KEY = "database";
    private static SQLConnector instance = null;

    private final String username;
    private final String password;
    private final String url;
    private Connection connection;

    public static SQLConnector getInstance() {
        JsonParser parser = new JsonParser();
        if (instance == null) {
            String resourceName = "save/config.json";
            JsonElement jsonElement = new JsonObject();
            try {
                jsonElement = parser.parse(new FileReader(resourceName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (jsonElement.isJsonNull()) {
                throw new NullPointerException("Cannot find resource file " + resourceName);
            }

            JsonObject object = jsonElement.getAsJsonObject();

            instance = new SQLConnector(object.get(USERNAME_KEY).getAsString(), object.get(PASSWORD_KEY).getAsString(), object.get(IP_KEY).getAsString(), object.get(PORT_KEY).getAsInt(), object.get(DATABASE_KEY).getAsString());
            instance.connect();
        }
        return instance;
    }

    private SQLConnector(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
    }


    /**
     * Method that starts the inner connection to the database.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            int c = 0;
        } catch (SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        }
    }

        public void insertQuery (String query){
            try {
                Statement s = connection.createStatement();
                s.executeUpdate(query);
            } catch (SQLException e) {
                System.err.println(query);
                System.err.println("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            }
        }

        public void updateQuery (String query){
            try {
                Statement s = connection.createStatement();
                s.executeUpdate(query);
            } catch (SQLException e) {
                System.err.println(query);
                System.err.println("Problem when updating --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            }
        }

        public boolean deleteQuery (String query){
            try {
                Statement s = connection.createStatement();
                s.executeUpdate(query);
                return true;
            } catch (SQLException e) {
                System.err.println(query);
                System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
                return false;
            }

        }

        public ResultSet selectQuery (String query){
            ResultSet rs = null;
            try {
                Statement s = connection.createStatement();
                rs = s.executeQuery(query);
            } catch (SQLException e) {
                System.err.println(query);
                System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            }
            return rs;
        }


        /**
         * Method that closes the inner connection to the database.
         */
        public void disconnect () {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            }
        }
    }
