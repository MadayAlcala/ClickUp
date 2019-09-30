package core.utils;

import clickup.entities.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.spi.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Deserializes the credentials information provided as a json file to a POJO entity class.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public final class CredentialDeserializer {
    private static CredentialDeserializer instance;
    private static JsonObject credentials;
    private static final String USER_RSRC_CONFIG_FILE = "user.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialDeserializer.class);
    private static final String LOGGER_MESSAGE = "Specified resource file not found!";

    /**
     * Utility class private constructor.
     */
    private CredentialDeserializer() {
        initialize();
    }

    /**
     * Returns the same and only instance of this class.
     *
     * @return the same instance of CredentialDeserializer class.
     */
    public static CredentialDeserializer getInstance() {
        if ((instance == null) || (credentials == null)) {
            instance = new CredentialDeserializer();
        }
        return instance;
    }

    /**
     * Deserializes the credentials information provided as a json file to a POJO entity class.
     *
     * @param userType a String containing the type of user to retrieve, i.e. user or admin.
     * @return a POJO entity instanceaccording to the value set for userType in the resource file.
     */
    public static User getUser(final String userType) {
        PropertyReader.loadFile(USER_RSRC_CONFIG_FILE);
        String user = PropertyReader.retrieveField(userType);
        JsonElement jsonUser = credentials.getAsJsonObject(userType).getAsJsonObject(user);
        Gson gson = new Gson();
        User userObject = gson.fromJson(jsonUser, User.class);
        return userObject;
    }

    /**
     * Sets the initial value of credentials attribute of this class.
     */
    private void initialize() {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = null;
        PropertyReader.loadFile(USER_RSRC_CONFIG_FILE);
        String jsonDirectory = PropertyReader.retrieveField("dir.json");
        try {
            jsonElement = parser.parse(new FileReader(jsonDirectory.concat(System.getProperty("file.separator"))
                    .concat("credentials.json")));
        } catch (FileNotFoundException fnfex) {
            LOGGER.error(LOGGER_MESSAGE, fnfex, ErrorCode.FILE_OPEN_FAILURE);
            throw new RuntimeException(LOGGER_MESSAGE);
        }
        credentials = jsonElement.getAsJsonObject();
    }
}
