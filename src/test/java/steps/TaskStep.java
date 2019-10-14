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

import clickup.api.SpaceApi;
import clickup.api.TaskApi;
import clickup.entities.Context;
import clickup.ui.PageTransporter;
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.HomeModal;
import clickup.ui.pages.LoginPage;
import clickup.ui.pages.NotificationsPage;
import clickup.ui.pages.TaskModalPage;
import core.utils.CredentialDeserializer;
import core.utils.WebElementActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.codec.DecoderException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
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
    private HomeModal homeModal;
    private LoginPage loginPage;
    private NotificationsPage notificationsPage;
    private TaskApi taskApi;
    private SpaceApi spaceApi;
    private Response response;
    private static final int SLEEP_DURATION = 5000;

    /**
     * Constructor for dependency injection.
     *
     * @param context A Context instance to be instantiated by pico-container library.
     * @throws GeneralSecurityException .
     * @throws IOException              .
     * @throws DecoderException         .
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
     * Creates a space inside a workplace (Team).
     *
     * @throws GeneralSecurityException .
     * @throws IOException              .
     * @throws DecoderException         .
     */
    @Given("the user is at an existing space")
    public void createExistingSpace() throws GeneralSecurityException, IOException, DecoderException {
        spaceApi = new SpaceApi(context.getUser());
        Map<String, String> content = new HashMap<>();
        content.put("name", "Precondition Space");
        content.put("multiple_assignees", "true");
        spaceApi.setContent(content);
        Response response = spaceApi.postContent(context.getUser().getTeamId());
        String newSpaceTitle = response.jsonPath().get("name");
        String newSpaceId = response.jsonPath().get("id");
        context.getSpace().setTitle(newSpaceTitle);
        context.getSpace().setId(newSpaceId);
        ApplicationPage applicationPage = PageTransporter.goToSpacePageById(context.getUser().getTeamId(),
                context.getSpace().getId());
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
    @When("the user assigns the task to a (.*) user")
    public void ownerAssignsTaskToUser(final String userType) {
        context.setUser(CredentialDeserializer.getInstance().getUser(userType));
        taskModalPage.assignTaskToUser(context.getUser().getFullName());
        taskModalPage.close();
    }

    /**
     * Logs a user out of the application.
     *
     */
    @When("the user logs out")
    public void userLogsOut() {
        ApplicationPage applicationPage = new ApplicationPage();
        homeModal = applicationPage.getSideMenu().displayUserMenu();
        loginPage = homeModal.logOut();
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
     * Searches an existing id.
     *
     * @throws GeneralSecurityException .
     * @throws IOException              .
     * @throws DecoderException         .
     */
    @When("the user makes an API request for the task")
    public void userSearchesExistingTaskById() throws GeneralSecurityException, IOException, DecoderException {
        taskApi = new TaskApi(context.getUser());
        response = taskApi.findTaskById(context.getTask().getId());
        response.prettyPrint();
    }

    /**
     * Associates a file to the Context Task.
     *
     * @param fileName a String containing the name of the file to be uploaded and attaches to a Task.
     */
    @When("the user attaches the file {string} from the computer to the new task")
    public void userAttachesFile(final String fileName) {
        context.getTask().setAttachmentFile(fileName);
        taskModalPage.attachFile(fileName);
        System.out.println("Hold on!");
    }

    /**
     * Asserts via API if the response contains the expected assignee.
     */
    @Then("the user should see that he is assigned to the new task")
    public void isUserAssignedToTask() {
        List<Map> assigneeList = response.jsonPath().get("assignees");
        Assert.assertEquals(assigneeList.get(0).get("username"), context.getUserMap().get("guest").getFullName(),
                "The task is currently assigned to " + assigneeList.toString());
    }

    /**
     * Confirms the message thrown by application after a Task is moved.
     *
     * @throws UnsupportedFlavorException .
     * @throws IOException                .
     */
    @Then("the user should see the task movement success message")
    public void getMovementModalMessage() throws UnsupportedFlavorException, IOException {
        applicationPage = new ApplicationPage();
        String actual = applicationPage.getContentPanel().getConfirmationMessage();
        //String expected = "Moved " + context.getTask().getName() + " to " + context.getListMap().get("first")
        // .getName();
        String expected = context.getTask().getName();
        applicationPage.getContentPanel().closeModal();
        applicationPage = new ApplicationPage();
        Assert.assertEquals(actual, expected, context.getTask().getName() + " has not been moved!");
    }

    /**
     * Searches for a newly created task inside a listing.
     */
    @Then("the user should see the new task listed in notifications")
    public void isTaskListedInNotifications() {
        notificationsPage = new NotificationsPage();
        notificationsPage.waitForPageLoading();
        String listedTaskName = notificationsPage.searchTaskByIdAndGetName(context.getTask().getId());
        Assert.assertEquals(listedTaskName, context.getTask().getName(), context.getTask()
                .getName() + " is not listed!");
    }

    /**
     * Checks if there are no assignees yet.
     *
     * @throws GeneralSecurityException .
     * @throws IOException              .
     * @throws DecoderException         .
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
     * @throws IOException              .
     * @throws DecoderException         .
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
            Thread.sleep(SLEEP_DURATION);
        } catch (InterruptedException e) {

        }
        List<WebElement> elementsList = applicationPage.getContentPanel().collectWebElementsByTaskId(context.getTask()
                .getId());
        Assert.assertTrue(elementsList.isEmpty());
    }

    /**
     * Searches a task in the list view.
     *
     * @param key that represent the keyword for search a task.
     */
    @When("the user searches a task with {string} keyword")
    public void searchTask(final String key) {
        applicationPage.getContentPanel().searchTask(key);
    }

    /**
     * Asserts if the last message appended to the task effectively states that a task was assigned to the current user.
     */
    @Then("the user should see the message that the task was assigned to him")
    public void checkAssignationMessage() {
        String actual = taskModalPage.readLastTaskHistory().replaceAll("\n", " ");
        String expected = context.getUserMap().get("creator").getFullName().concat(" ").concat("assigned to: You");
        taskModalPage.close();
        Assert.assertEquals(actual, expected, "Task \"" + context.getTask().getName()
                + "hasn't been assigned to you yet!");
    }

    /**
     * Searches for a newly created task inside a listing.
     */
    @Then("the user should see the new task listed$")
    public void isTaskListed() {
        applicationPage = new ApplicationPage();
        String listedTaskName = applicationPage.getContentPanel().searchTaskByIdAndGetName(context.getTask().getId());
        //applicationPage.getContentPanel().waitForPopUpToGo();
        Assert.assertEquals(listedTaskName, context.getTask().getName(), context.getTask()
                .getName() + " is not listed!");
    }

    /**
     * Asserts if a uploaded file is listed in the attachment section of a Task Modal Page.
     */
    @Then("the user should see the file in the attachments section")
    public void userSeeAttachment() {
        String actual = taskModalPage.getAttachmentFileName();
        String expected = context.getTask().getAttachmentFile();
        taskModalPage.close();
        Assert.assertEquals(actual, expected, "File upload failed!");
    }

    /**
     * Verifies the quantity of the tasks in a list in content panel.
     *
     * @param quantity that represent the quantity of tasks to find.
     * @throws InterruptedException for the sleep.
     */
    @Then("the user should see displayed {string} at the bottom of the list")
    public void verifyTasksQuantity(final String quantity) throws InterruptedException {
        Assert.assertEquals(applicationPage.getContentPanel().getTasksQuantity(), quantity.toUpperCase());
    }

    /**
     * Realizes the drag and drop to complete status of a task.
     */
    @When("the user drags the task to Complete status")
    public void dragTaskToCompleteStatus() {
        applicationPage.getContentPanel().setBoardView();
        applicationPage.getContentPanel().moveTask(context.getTask().getName());
    }

    /**
     * Verifies if the task is in complete status.
     */
    @Then("the user user should see the task in complete status")
    public void verifyTaskInCompleteStatus() {
        Assert.assertTrue(applicationPage.getContentPanel().containsTask(context.getTask().getName()));
    }
}
