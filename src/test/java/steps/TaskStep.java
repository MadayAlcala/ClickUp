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

import clickup.api.TaskApi;
import clickup.entities.Context;
import clickup.ui.PageTransporter;
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.LoginPage;
import clickup.ui.pages.NotificationsPage;
import clickup.ui.pages.TaskModalPage;
import core.utils.CredentialDeserializer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.codec.DecoderException;
import org.testng.Assert;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

/**
 * TaskStep.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskStep {
    private Context context;
    private ApplicationPage applicationPage;
    private TaskModalPage taskModalPage;
    private NotificationsPage notificationsPage;
    private TaskApi taskApi;

    /**
     * Constructor for dependency injection.
     *
     * @param context A Context instance to be instantiated by pico-container library.
     */
    public TaskStep(final Context context) throws GeneralSecurityException, IOException, DecoderException {
        this.context = context;
    }

    /**
     * Creates new task in a list.
     *
     * @param taskName A String containing the name of the Task to be created.
     * @throws IOException .
     * @throws UnsupportedFlavorException .
     */
    @When("The user creates a new task with the following name {string}")
    public void createNewTask(final String taskName) throws IOException, UnsupportedFlavorException {
        applicationPage = new ApplicationPage();
        context.getTask().setName(taskName);
        applicationPage.getContentPanel().createTask(taskName);
        context.getTask().setId(applicationPage.getContentPanel().extractTaskId());
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
        applicationPage.getContentPanel().closeModal();
        Assert.assertEquals(confirmationMessage, context.getTask().getName() + " Created!",
                "The task " + context.getTask().getName() + " has not been created!");
    }

    /**
     * Asserts if the a given title is listed in the body of the application page.
     */
    @Then("The user should see the new task appear in the panel")
    public void taskShouldBeListed() {
        String taskTitle = applicationPage.getContentPanel().getTaskTitleById();
        //TODO assertion pending.
    }

    /**
     * Visits a Task page by its id.
     */
    @When("The user goes to page of the new task")
    public void goToNewTaskPage() {
        taskModalPage = PageTransporter.goToTaskPageById(context.getTask().getId());
    }

    /**
     * A task creator assigns task to a user.
     *
     * @param userType a String containing the user type that the task is going to assigned to.
     */
    @When("The admin user assigns the task to a (.*) user")
    public void amdinAssignsTaskToUser(final String userType) {
        context.setUser(CredentialDeserializer.getInstance().getUser(userType));
        taskModalPage.assignTaskToUser(context.getUser().getFullName());
        taskModalPage.close();
    }

    /**
     * Logs a user out of the application.
     */
    @When("The admin user logs out")
    public void userLogsOut() {
        applicationPage.getSideMenu().logOut();
        new LoginPage();
    }

    /**
     * Visits to Notifications page for a given workplace.
     *
     * @param userType a String containing the user type that the task is going to assigned to.
     */
    @When("The user goes to notifications page for (.*) workplace")
    public void userSwitchWorkplace(final String userType) {
        String ownerId = context.getUserMap().get(userType).getTeamId();
        notificationsPage = PageTransporter.goToNotificationsPage(ownerId);
        // TODO Refactor
        notificationsPage.waitForPageLoading();
    }

    /**
     * Searches for a newly created task inside a listing.
     */
    @Then("The user should see the task listed")
    public void isTaskListed() {
        String listedTaskName = notificationsPage.searchTaskByIdAndGetName(context.getTask().getId());
        Assert.assertEquals(listedTaskName, context.getTask().getName(), context.getTask()
                .getName() + " is not listed!");
    }

    @Then("The task should not have any assignees")
    public void isTaskWithAssignee() throws GeneralSecurityException, IOException, DecoderException {
        taskApi = new TaskApi(context.getUser());
        Response response = taskApi.findTaskById(context.getTask().getId());
        List<Map> asigneeList = response.jsonPath().get("assignees");
        Assert.assertTrue(asigneeList.isEmpty(), "Task " + context.getTask().getName() + " has already been assigned!");
    }

    @Then("The user should be the asignee")
    public void isUserAssignee() throws GeneralSecurityException, IOException, DecoderException {
        taskApi = new TaskApi(context.getUser());
        Response response = taskApi.findTaskById(context.getTask().getId());
        List<Map> asigneeList = response.jsonPath().get("assignees");
        Assert.assertEquals(asigneeList.get(0).get("username"), context.getUserMap().get("guest").getFullName());
    }
}
