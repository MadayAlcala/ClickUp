/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package steps;

import clickup.entities.Context;
import clickup.ui.pages.ApplicationPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * TaskStep.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskStep {
    private Context context;
    private ApplicationPage applicationPage;

    /**
     * Constructor for dependency injection.
     *
     * @param context A Context instance to be instantiated by pico-container library.
     */
    public TaskStep(final Context context) {
        this.context = context;
    }

    /**
     * Creates new task in a list.
     *
     * @param taskName A String containing the name of the Task to be created.
     */
    @When("The user creates a new task with the following name {string}")
    public void createNewTask(final String taskName) {
        applicationPage = new ApplicationPage();
        context.getTask().setName(taskName);
        applicationPage.getContentPanel().createTask(taskName);
    }

    /**
     * Confirms the message thrown by application after a Task is created.
     *
     * @throws UnsupportedFlavorException .
     * @throws IOException .
     */
    @Then("The user should see the success message")
    public void getModalMessage() throws UnsupportedFlavorException, IOException {
        String confirmationMessage = applicationPage.getContentPanel().getCreationConfirmationMessage();
        context.getTask().setUrl(applicationPage.getContentPanel().extractTaskId());
        Assert.assertEquals(confirmationMessage, context.getTask().getName() + " Created!",
                "The task has not been created!");
    }

    /**
     * Asserts if the a given title is listed in the body of the application page.
     */
    @Then("The user should see the new task appear in the panel")
    public void taskShouldBeListed() {
        String taskTitle = applicationPage.getContentPanel().getTaskTitleById();
        //TODO assertion pending.
    }
}
