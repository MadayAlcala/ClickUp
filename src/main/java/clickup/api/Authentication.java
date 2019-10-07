/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.api;

import clickup.entities.User;
import core.utils.CredentialDeserializer;
import core.utils.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Authentication class.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public final class Authentication {
    private static final String APP_CONFIG_FILE = "app.properties";
    private static final String API_URI = "api";

    /**
     * Private Constructor for utility class.
     */
    private Authentication() {
    }

    /**
     * Builds a RequestSpecifiction to be used to interact via API with ClickUpby providing a Personal API Token.
     *
     * @param userType a String defining how priviliged a user is.
     * @return RequestSpecification instance with the necessary personal token and base uri.
     * @throws GeneralSecurityException .
     * @throws IOException .
     * @throws DecoderException .
     */
    public static RequestSpecification requestSpecification(final String userType) throws GeneralSecurityException,
            IOException, DecoderException {
        PropertyReader.loadFile(APP_CONFIG_FILE);
        String apiUrl = PropertyReader.retrieveField(API_URI);
        User user = CredentialDeserializer.getInstance().getUser(userType);
        return new RequestSpecBuilder()
                .setBaseUri(apiUrl)
                .addHeader("Authorization", user.getPersonalToken())
                .build();
    }
}
