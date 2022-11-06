package persistence.SQL;

import org.jasypt.util.text.AES256TextEncryptor;
import persistence.UserDAO;
import business.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The SQLUserDAO implements the {@link UserDAO} interface to persist the data in
 * a database.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-04-29
 */
public class SQLUserDAO implements UserDAO {

    @Override
    public void addUser(User user){
        String pass = user.getPassword();
        String hashed = encryptPassword(pass);

        String query = "INSERT INTO user(email, username, password) VALUES ('" +
                user.getEmail() + "', '" +
                user.getUsername() + "', '" +
                hashed + "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public boolean nameExist(String name) {
        String query = "SELECT username FROM user WHERE username = '" + name + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            return result.next(); // checks if there is any row
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * NO ENTIENDO LA LOGICA
     *
     */
    @Override
    public String getPassword(String name, boolean isUsername) {
        String query = "SELECT password FROM user WHERE username = '" + name + "';";
        if (!isUsername) {
            query = "SELECT password FROM User WHERE email = '" + name + "';";
        }
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            if(result.next()){
                return decryptPassword(result.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUser(String username) {
        String query = "DELETE FROM playlist_user WHERE user = '" + username +"';";
        SQLConnector.getInstance().deleteQuery(query);
        query = "DELETE FROM user WHERE username = '" + username +"';";
        return SQLConnector.getInstance().deleteQuery(query);
    }

    private String encryptPassword(String password)
    {
        AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
        aesEncryptor.setPassword("mypassword");
        return aesEncryptor.encrypt(password);
    }

    private String decryptPassword(String passwordFromConfigFile)
    {
        AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
        aesEncryptor.setPassword("mypassword");
        return aesEncryptor.decrypt(passwordFromConfigFile);
    }

}
