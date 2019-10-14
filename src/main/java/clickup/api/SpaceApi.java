package clickup.api;

import clickup.entities.User;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import static clickup.api.Endpoints.SPACE_SUFFIX;
import static clickup.api.Endpoints.TEAM_SUFFIX;

/**
 * API Client for interacting with ClickUp Spaces.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class SpaceApi {
    private ApiClient apiClient;

    /**
     * Constructor method.
     *
     * @param user an instance of a User entity.
     * @throws GeneralSecurityException .
     * @throws IOException .
     * @throws DecoderException .
     */
    public SpaceApi(final User user) throws GeneralSecurityException, IOException, DecoderException {
        apiClient = new ApiClient(user);
    }

    /**
     * Add a deserialized json content to the body of 'request' Requestpecification attribute of this class.
     *
     * @param taskBody a flat deserialized String containing the key/value data to be provided to a POST or
     *      PATCH request.
     */
    public void setContent(final String taskBody) {
        apiClient.buildSpec(taskBody);
    }

    /**
     * Add a json content to the body of 'request' Requestpecification attribute contained in a Map format.
     *
     * @param taskBody a Map structure containing the key/value data to be provided to a POST or PATCH request.
     */
    public void setContent(final Map taskBody) {
        apiClient.buildSpec(taskBody);
    }

    /**
     * Adds content to the body of 'request' Requestpecification attribute contained within a JSONObject.
     *
     * @param taskBody a JSONObject containing the key/value data to be provided to a POST or PATCH request.
     */
    public void setContent(final JsonObject taskBody) {
        apiClient.buildSpec(taskBody);
    }

    /**
     * Returns a RestAssured Response as a result of a successful POST request.
     *
     * @param teamId uniquely identifies the Task to be updated.
     * @return Returns a RestAssured Response as a result of a successful POST request.
     */
    public Response postContent(final String teamId) {
        return apiClient.post(TEAM_SUFFIX.concat("/").concat(teamId).concat("/").concat(SPACE_SUFFIX));
    }
}
