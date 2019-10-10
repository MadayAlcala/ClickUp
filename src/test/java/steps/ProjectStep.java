package steps;

import clickup.entities.Context;
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.DeleteModal;
import clickup.ui.pages.ListMenuModal;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

/**
 * ProjectStep class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ProjectStep {
    private ApplicationPage applicationPage;
    private ListMenuModal listMenuModal;
    private DeleteModal deleteModal;
    private Context context;

    /**
     * Class constructor.
     *
     * @param context context.
     */
    public ProjectStep(final Context context) {
        this.context = context;
    }

    /**
     * Creates a new Project.
     *
     * @param projectName that is the name of the project to create.
     */
    @When("the user creates a new project with the following name {string}")
    public void addNewProject(final String projectName) {
        applicationPage = new ApplicationPage();
        context.getProject().setName(projectName);
        context.getList().setName("List");
        applicationPage.getListPanel().addNewFolder(projectName);
    }

    /**
     * Verifies the name of the project on list Panel.
     */
    @Then("the user should see the new project appear in the panel successfully")
    public void verifyNewProjectName() {
        String expected = context.getProject().getName();
        String actual = applicationPage.getListPanel().getNameProject(context.getProject().getName());
        Assert.assertEquals(actual, expected, "The project has not been created.");
    }

    /**
     * Verifies the name of the project on the bar title of content panel.
     */
    @Then("the user should see the name of the project on the Bar title of content panel")
    public void verifyNameProjectInBarTitleOfContentPanel() {
        String actual = applicationPage.getContentPanel().getBarTitleProjectName();
        String expected = context.getProject().getName();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Copies a project.
     *
     * @param copyProject that represent the name for the project to copy.
     */
    @When("the user copies the project and gives it the name {string}")
    public void copyProject(final String copyProject) {
        String actualProjectName = context.getProject().getName();
        applicationPage.getListPanel().copyProject(actualProjectName, copyProject);
    }

    @After
    public void afterClass() {
        applicationPage.getSideMenu().logOut();
    }
}
