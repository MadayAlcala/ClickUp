package steps;

import clickup.entities.Context;
import clickup.ui.pages.TaskPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * TaskStep
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class TaskStep {
    private Context context;
    private TaskPage taskPage;

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
        taskPage = new TaskPage();
        context.getTask().setName(taskName);
        taskPage.createTask(taskName);
    }

    /**
     * Confirms the message thrown by application after a Task is created.
     *
     * @throws UnsupportedFlavorException .
     * @throws IOException .
     */
    @Then("The user should see the success message")
    public void getModalMessage() throws UnsupportedFlavorException, IOException {
        String confirmationMessage = taskPage.getCreationConfirmationMessage();
        context.getTask().setUrl(taskPage.extractTaskId());
        Assert.assertEquals(confirmationMessage, context.getTask().getName() + " Created!",
                "The task has not been created!");
    }

    /**
     *
     *
     */
    @Then("The user should see the new task appear in the panel")
    public void taskShouldBeListed() {
        String taskTitle = taskPage.getTaskTitleById();
        Assert.assertEquals(taskTitle, context.getTask().getName(), "The task has not been created!");
    }
}
