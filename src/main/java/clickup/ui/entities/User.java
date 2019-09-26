package clickup.ui.entities;

import core.utils.Hasher;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * User entity class.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class User {
    private String email;
    private String password;
    private String personalToken;
    private String fullName;

    /**
     * Getter method.
     *
     * @return a String containing the email associated to this user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method.
     *
     * @param email a String containing the new value to assign as a new email for this user.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Getter method.
     *
     * @return a String containing the password associated to this user.
     */
    public String getPassword() throws GeneralSecurityException, IOException {
        return Hasher.decrypt(password);
    }

    /**
     *
     * @return
     */
    public String getPersonalToken() throws GeneralSecurityException, IOException {
        return Hasher.decrypt(personalToken);
    }

    /**
     * Getter method.
     *
     * @return a String containing the full name associated to this user.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter method.
     *
     * @param fullName a String containing the new value to assign as this user's full name.
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
