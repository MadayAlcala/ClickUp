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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Base API Client.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public final class ApiClient {
    private RequestSpecification request;

    /**
     * Constructor of rest client API.
     *
     * @throws GeneralSecurityException .
     * @throws IOException .
     * @throws DecoderException .
     */
    public ApiClient(User user) throws GeneralSecurityException, IOException, DecoderException {
        request = Authentication.requestSpecification(user);
    }

    /**
     * Returns this class' RequestSpecification request.
     *
     * @return a RequestSpecification structure containing the details to make an HTTP request.
     */
    public RequestSpecification getRequest() {
        return request;
    }

    /**
     * Sets this class' RequestSpecification request.
     *
     * @param request the RequestSpecification structure to be associated to this class' attribute.
     */
    public void setRequest(final RequestSpecification request) {
        this.request = request;
    }

    /**
     * Returns a response after requesting a get.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response get(final String endpoint) {
        return apiResponse("GET", endpoint);
    }

    /**
     * Returns a response after requesting a delete.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response delete(final String endpoint) {
        return apiResponse("DELETE", endpoint);
    }

    /**
     * Returns a response after requesting a post.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response post(final String endpoint) {
        return apiResponse("POST", endpoint);
    }

    /**
     * Returns a response after requesting a put.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response put(final String endpoint) {
        return apiResponse("PUT", endpoint);
    }

    /**
     * Returns a response after requesting a patch.
     *
     * @param endpoint to do the request.
     * @return a response.
     */
    public Response patch(final String endpoint) {
        return apiResponse("PATCH", endpoint);
    }

    /**
     * Returns a response after requesting a post.
     *
     * @param httpMethod to do the request.
     * @param endPoint URI.
     * @return a RestAssured Response structure.
     */
    public Response apiResponse(final String httpMethod, final String endPoint) {
        return given().spec(request).when().request(httpMethod, endPoint);
    }

    /**
     * Add a deserialized json content to the body of this class request attribute.
     *
     * @param taskBody A string containing plain deserialized key/value pairs.
     */
    public void buildSpec(final String taskBody) {
        request = given().spec(request).contentType(ContentType.JSON)
                .body(taskBody);
    }

    /**
     * Add a json content contained in a Map format to the body of this class request attribute.
     *
     * @param taskBody A map structure containing the key/value pairs to be passed.
     */
    public void buildSpec(final Map taskBody) {
        Gson utilGson = new GsonBuilder().setPrettyPrinting().create();
        String bodyJson = utilGson.toJson(taskBody);
        request = given().spec(request).contentType(ContentType.JSON)
                .body(bodyJson);
    }

    /**
     * Adds content to the body of this class request attribute contained within a JSONObject.
     *
     * @param taskBody A JSONObject object containing the key/value pairs to be passed.
     */
    public void buildSpec(final JsonObject taskBody) {
        request = given().spec(request)
                .contentType(ContentType.JSON)
                .body(taskBody.toString());
    }
}
