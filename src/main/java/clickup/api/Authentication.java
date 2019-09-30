package clickup.api;

import clickup.ui.entities.User;
import clickup.ui.pages.PageTransporter;
import core.utils.CredentialDeserializer;
import core.utils.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Authentication
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class Authentication {
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
     * @return RequestSpecification instance with the necessary personal token and base uri.
     */
    public static RequestSpecification requestSpecification(final String userType) throws GeneralSecurityException, IOException, DecoderException {
        PropertyReader.loadFile(APP_CONFIG_FILE);
        String apiUrl = PropertyReader.retrieveField(API_URI);
        User user = CredentialDeserializer.getInstance().getUser(userType);
        return new RequestSpecBuilder()
                .setBaseUri(apiUrl)
                .addHeader("Authorization", user.getPersonalToken())
                .build();
    }
}
