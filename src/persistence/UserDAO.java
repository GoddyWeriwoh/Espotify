package persistence;

import business.entity.Song;
import business.entity.User;

import java.util.ArrayList;

/**
 * The UserDAO interface defines the design that the DAO must follow to manage the
 * persisted data from the Playlists in the system.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-04-29
 */
public interface UserDAO {

    /**
     * Registers a user in the databse saving its information.
     *
     * @param user The user to register
     */
    void addUser(User user);

    /**
     * Checks if a usernames exists in the database.
     *
     * @param name The name to check
     * @return true if the name exists
     */
    boolean nameExist(String name);

    String getPassword(String name, boolean bool);

    /**
     * Deletes a user from the database.
     *
     * @param username The username of the user to delete
     * @return true if the deletion was successful
     */
    boolean deleteUser(String username);

}
