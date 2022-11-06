package business.entity;

/**
 * The User class is the representation of a user managed by the system.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-04-29
 */
public class User {
    private final String username;
    private final String email;
    private final String password;

    /**
     * Default User parametrized constructor.
     */
    public User(String email, String username, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
