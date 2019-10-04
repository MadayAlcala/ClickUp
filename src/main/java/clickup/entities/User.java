/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.entities;

import core.utils.Hasher;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * User entity class.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class User {
    private String id;
    private String email;
    private String password;
    private String personalToken;
    private String fullName;

    /**
     * Getter method.
     *
     * @return the id associated to a user registered at ClickUp application.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method.
     *
     * @param id associated to a user registered at ClickUp application.
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * @throws GeneralSecurityException .
     * @throws DecoderException .
     * @throws IOException .
     */
    public String getPassword() throws GeneralSecurityException, DecoderException, IOException {
        return Hasher.decrypt(password);
    }

    /**
     * Getter method.
     *
     * @return a String containing the API personal token associated to this user.
     * @throws GeneralSecurityException .
     * @throws DecoderException .
     * @throws IOException .
     */
    public String getPersonalToken() throws GeneralSecurityException, DecoderException, IOException {
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
