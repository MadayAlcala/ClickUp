/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package hook;

import clickup.api.TaskApi;
import clickup.entities.Context;
import clickup.entities.User;
import cucumber.api.java.After;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Task Hook class.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskHook {
    private final int eleventh = 11;
    private Context context;
    private TaskApi taskApi;

    /**
     * Constructor for dependency injection.
     *
     * @param context an Context instance containing entities to be shared across step definitions.
     */
    public TaskHook(Context context) {
        this.context = context;
    }

    /**
     * Deletes a task in order to keep the environment clean.
     *
     * @throws GeneralSecurityException .
     * @throws IOException .
     * @throws DecoderException .
     */
    @After(order = eleventh, value = "@deleteTask")
    public void deleteTask() throws GeneralSecurityException, IOException, DecoderException {
        taskApi = new TaskApi(context.getUserMap().get("creator"));
        taskApi.deleteTaskById(context.getTask().getId());
    }
}
