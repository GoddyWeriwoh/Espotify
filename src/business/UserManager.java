package business;

import business.entity.Song;
import business.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import persistence.UserDAO;
import persistence.SQL.SQLUserDAO;

/**
 * The UserManager class manages the logic related to the users from the system.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-01
 */

public class UserManager {
    private final UserDAO userDAO = new SQLUserDAO();
    User user;

    /**
     * Check if the name exists in the database.
     *
     * @param name Name to check in the database.
     * @return true if the name exists
     */
    public boolean nameExist(String name){
        return userDAO.nameExist(name);
    }

    /**
     * Check if the email address exists in the database.
     *
     * @param email Email to check in the database.
     * @return true if the email exists
     */
    public boolean emailExist(String email) {
        //
        return userDAO.nameExist(email);
    }

    /**
     * Registers the user persisting its data in the database.
     *
     * @param email Email of the user to store.
     * @param name Name of the user to store.
     * @param password Password of the user to store.
     */
    public void storeUser(String email, String name, String password) {
        user = new User(email, name, password);
        userDAO.addUser(user);
    }

    public boolean isPasswordCorrect(String name, String pass, boolean bool) {
        String passFrom = userDAO.getPassword(name, bool);
        return  passFrom.equals(pass);
        //return BCrypt.checkpw(pass, hashPassword);
    }
    /**
     * Sets the user who has logged in as the current user to manage.
     *
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public void setUser(String username, String password) {
            user = new User("", username, password);
    }

    /**
     * Deletes the account of the current user from the system.
     */
    public void deleteAccount(){
        if (userDAO.deleteUser(user.getUsername())) {
            this.user = null;
        }
        else{
            System.out.println("Something unexpected occurred while deleting the user."+
                    " Please, check the connection of the system and try again");
        }
    }

    /**
     * Returns the current user managed by the system.
     *
     * @return the current user
     */
    public User getUser() {
        return user;
    }
    public String getUserName() {
        return user.getUsername();
    }

}