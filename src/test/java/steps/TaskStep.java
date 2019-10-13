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
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import clickup.ui.pages.LoginPage;
import clickup.ui.pages.NotificationsPage;
import clickup.ui.pages.TaskModalPage;
import core.utils.CredentialDeserializer;
import core.utils.WebElementActions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.codec.DecoderException;
import org.openqa.selenium.WebElement;
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
     * @throws GeneralSecurityException .
     * @throws IOException .
     * @throws DecoderException .
     */
    public TaskStep(final Context context) throws GeneralSecurityException, IOException, DecoderException {
        this.context = context;
    }

    /**
     * Creates new task in a list.
     *
     * @param taskName A String containing the name of the Task to be created.
     * @throws IOException                .
     * @throws UnsupportedFlavorException .
     */
    @When("the user creates a new task with the following name {string}")
    public void createNewTask(final String taskName) throws IOException, UnsupportedFlavorException {
        applicationPage = new ApplicationPage();
        context.getTask().setName(taskName);
        applicationPage.getContentPanel().createTask(taskName);
        context.getTask().setId(applicationPage.getContentPanel().extractTaskId());
        context.getUserMap().put("creator", context.getUser());
    }

    /**
     * Confirms the message thrown by application after a Task is created.
     *
     * @throws UnsupportedFlavorException .
     * @throws IOException                .
     */
    @Then("the user should see the task creation success message")
    public void getModalMessage() throws UnsupportedFlavorException, IOException {
        String confirmationMessage = applicationPage.getContentPanel().getConfirmationMessage();
        applicationPage.getContentPanel().closeModal();
        Assert.assertEquals(confirmationMessage, context.getTask().getName(),
                "The task \"" + context.getTask().getName() + "\" has not been created!");
    }

    /**
     * Asserts if the a given title is listed in the body of the application page.
     */
    @Then("the user should see the new task appear in the panel")
    public void taskShouldBeListed() {
        String taskTitle = applicationPage.getContentPanel().getTaskTitleById();
        //TODO assertion pending.
    }

    /**
     * Creates multiple tasks.
     *
     * @param tasksList that contains the names of the tasks to create.
     */
    @Given("the user creates the following tasks:")
    public void theUserCreatesTheFollowingTasks(final List tasksList) {
        applicationPage = new ApplicationPage();
        String listName = context.getList().getName();
        applicationPage.getContentPanel().createListTasks(tasksList, listName);
    }

    /**
     * Visits a Task page by its id.
     */
    @When("the user goes to page of the new task")
    public void goToNewTaskPage() {
        taskModalPage = PageTransporter.goToTaskPageById(context.getTask().getId());
    }

    /**
     * A task creator assigns task to a user.
     *
     * @param userType a String containing the user type that the task is going to assigned to.
     */
    @When("the admin user assigns the task to a (.*) user")
    public void amdinAssignsTaskToUser(final String userType) {
        context.setUser(CredentialDeserializer.getInstance().getUser(userType));
        taskModalPage.assignTaskToUser(context.getUser().getFullName());
        taskModalPage.close();
    }

    /**
     * Logs a user out of the application.
     */
    @When("the admin user logs out")
    public void userLogsOut() {
        applicationPage.getSideMenu().logOut();
        new LoginPage();
    }

    /**
     * Visits to Notifications page for a given workplace.
     *
     * @param userType a String containing the user type that the task is going to assigned to.
     */
    @When("the user goes to notifications page for (.*) workplace")
    public void userSwitchWorkplace(final String userType) {
        String ownerId = context.getUserMap().get(userType).getTeamId();
        notificationsPage = PageTransporter.goToNotificationsPage(ownerId);
        // TODO Refactor
        notificationsPage.waitForPageLoading();
    }

    /**
     * Drags a Task and Drops it over a List.
     *
     * @param order a String specifying whih of the created list will receive the task.
     */
    @When("the user moves the task to the (.*) list")
    public void dragTask(final String order) {
        //TODO Refactor: It shouldn't rely on close()
        //applicationPage.getContentPanel().closeModal();
        applicationPage.dragTask(context.getTask(), context.getListMap().get(order));
    }

    /**
     * Visits a List entity page from the side panel.
     *
     * @param order in which a particular List in a series of Lists was created.
     */
    @When("^the user goes to the ([[first][second][third]]+) list$")
    public void userGoesToNthList(final String order) {
        WebElementActions.click(applicationPage.getListPanel().getListElementByName(context.getListMap().get(order)
                .getName()));
        applicationPage = new ApplicationPage();
        applicationPage.getListPanel().waitForHeaderElementTextEqualsCreatedListName(context.getListMap().get(order)
                .getName());
    }

    /**
     * Confirms the message thrown by application after a Task is moved.
     *
     * @throws UnsupportedFlavorException .
     * @throws IOException .
     */
    @Then("the user should see the task movement success message")
    public void getMovementModalMessage() throws UnsupportedFlavorException, IOException {
        applicationPage = new ApplicationPage();
        String actual = applicationPage.getContentPanel().getConfirmationMessage();
        //String expected = "Moved " + context.getTask().getName() + " to " + context.getListMap().get("first").getName();
        String expected = context.getTask().getName();
        applicationPage.getContentPanel().closeModal();
        Assert.assertEquals(actual, expected, context.getTask().getName() + " has not been moved!");
    }

    /**
     * Searches for a newly created task inside a listing.
     */
    @Then("the user should see the task listed")
    public void isTaskListedInNotifications() {
        notificationsPage = new NotificationsPage();
        String listedTaskName = notificationsPage.searchTaskByIdAndGetName(context.getTask().getId());
        Assert.assertEquals(listedTaskName, context.getTask().getName(), context.getTask()
                .getName() + " is not listed!");
    }

    /**
     * Checks if there are no assignees yet.
     *
     * @throws GeneralSecurityException .
     * @throws IOException .
     * @throws DecoderException .
     */
    @Then("the task should not have any assignees")
    public void isTaskWithAssignee() throws GeneralSecurityException, IOException, DecoderException {
        taskApi = new TaskApi(context.getUser());
        Response response = taskApi.findTaskById(context.getTask().getId());
        List<Map> asigneeList = response.jsonPath().get("assignees");
        Assert.assertTrue(asigneeList.isEmpty(), "Task " + context.getTask().getName() + " has already been assigned!");
    }

    /**
     * Compares the actual asignee with expected asignee user name.
     *
     * @throws GeneralSecurityException .
     * @throws IOException .
     * @throws DecoderException .
     */
    @Then("the user should be the asignee")
    public void isUserAssignee() throws GeneralSecurityException, IOException, DecoderException {
        taskApi = new TaskApi(context.getUser());
        Response response = taskApi.findTaskById(context.getTask().getId());
        List<Map> asigneeList = response.jsonPath().get("assignees");
        Assert.assertEquals(asigneeList.get(0).get("username"), context.getUserMap().get("guest").getFullName());
    }

    /**
     * Returns false if the Task is not present in the page.
     */
    @Then("the user should not see the task listed")
    public void userShouldNotSeeTheTaskListed() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elementsList = applicationPage.getContentPanel().collectWebElementsByTaskId(context.getTask().getId());
        Assert.assertTrue(elementsList.isEmpty());
    }

    /**
     * Changes the view to board view.
     */
    @When("the user selects the board view")
    public void selectBoardView() {
        applicationPage.getContentPanel().setBoardView();
    }
}
