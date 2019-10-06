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
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import static clickup.api.Endpoints.LIST_SUFFIX;
import static clickup.api.Endpoints.TASK_SUFFIX;

/**
 * API Client for interacting with ClickUp Tasks.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskApi {
    private ApiClient apiClient;

    /**
     * Constructor method.
     *
     * @throws GeneralSecurityException .
     * @throws IOException .
     * @throws DecoderException .
     */
    public TaskApi(User user) throws GeneralSecurityException, IOException, DecoderException {
        apiClient = new ApiClient(user);
    }

    /**
     * Add a deserialized json content to the body of 'request' Requestpecification attribute of this class.
     *
     * @param taskBody a flat deserialized String containing the key/value data to be provided to a POST or
     *      PATCH request.
     */
    public void setContent(String taskBody) {
        apiClient.buildSpec(taskBody);
    }

    /**
     * Add a json content to the body of 'request' Requestpecification attribute contained in a Map format.
     *
     * @param taskBody a Map structure containing the key/value data to be provided to a POST or PATCH request.
     */
    public void setContent(Map taskBody) {
        apiClient.buildSpec(taskBody);
    }

    /**
     * Adds content to the body of 'request' Requestpecification attribute contained within a JSONObject.
     *
     * @param taskBody a JSONObject containing the key/value data to be provided to a POST or PATCH request.
     */
    public void setContent(JsonObject taskBody) {
        apiClient.buildSpec(taskBody);
    }

    /**
     * Returns a RestAssured Response as a result of a successful POST request.
     *
     * @param listId uniquely identifies the Task to be updated.
     * @return Returns a RestAssured Response as a result of a successful POST request.
     */
    public Response postContent(String listId) {
        return apiClient.post(LIST_SUFFIX.concat("/").concat(listId).concat("/").concat(TASK_SUFFIX));
    }

    /**
     * Returns a RestAssured Response as a result of a successful PUT request.
     *
     * @param taskId uniquely identifies a given Task.
     * @return a RestAssured Response structure containing the values for all the keys associated to a given Task.
     */
    public Response putContent(String taskId) {
        return apiClient.put(TASK_SUFFIX.concat("/").concat(taskId));
    }

    /**
     * Returns a previously created Task specified by its id.
     *
     * @param id uniquely identifies a given Task.
     * @return a RestAssured Response structure containing the values for all the keys associated to a given Task.
     */
    public Response findTaskById(String id) {
        return apiClient.get(TASK_SUFFIX.concat("/").concat(id));
    }

    /**
     * Delete a previously created Task specified by its id.
     *
     * @param id uniquely identifies a given Task.
     * @return a RestAssured Response structure as a result of a successful DELETE request.
     */
    public Response deleteTaskById(String id) {
        return apiClient.delete(TASK_SUFFIX.concat("/").concat(id));
    }
}
