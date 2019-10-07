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
import clickup.entities.List;
import clickup.ui.pages.ApplicationPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * ListStep class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListStep {
    private ApplicationPage applicationPage;
    private Context context;

    /**
     * Class constructor.
     *
     * @param context context.
     */
    public ListStep(final Context context) {
        this.context = context;
    }

    /**
     * Creates new list in a space.
     *
     * @param nameList that is the name of the new List.
     */
    @When("the user creates a new list with the following name {string}")
    public void createNewList(final String nameList) {
        applicationPage = new ApplicationPage();
        context.getList().setName(nameList);
        applicationPage.getListPanel().addNewList(nameList);
    }

    /**
     * Creates new list in a space.
     *
     * @param order to be stored in the collection within the context.
     * @param listName that is the name of the new List.
     */
    @When("the user creates a ([[first][second][third]]+) list with the following name (.*)")
    public void createNewLists(final String order, final String listName) {
        String trimmedListName = listName.replaceAll("\"", "");
        applicationPage = new ApplicationPage();
        applicationPage.getListPanel().createList(trimmedListName);
        List list = new List();
        list.setName(trimmedListName);
        context.setList(list);
        context.getListMap().put(order, list);
    }

    /**
     * Checks that the list has been created.
     */
    @Then("the user should see the new list appear in the panel successfully")
    public void verifyNewListName() {
        String expected = context.getList().getName();
        String actual = applicationPage.getListPanel().getNameList(context.getList().getName());
        Assert.assertEquals(actual, expected, "The list has not been created.");
    }

    /**
     * Updates the name of a list.
     *
     * @param nameList that is the new name for the list.
     */
    @When("the user updates a list with the following name {string}")
    public void updateList(final String nameList) {
        applicationPage = new ApplicationPage();
        context.getList().setName(nameList);
        applicationPage.getListPanel().updateList(nameList);
    }

    /**
     * Deletes a list.
     */
    @When("the user deletes the list")
    public void deleteList() {
        applicationPage = new ApplicationPage();
        String listsName = context.getList().getName();
        applicationPage.getListPanel().deleteList(listsName);
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
     * Verifies the quantity of the tasks in a list in content panel.
     *
     * @param quantity that represent the quantity of tasks to find.
     */
    @Then("the user should see displayed {string} at the bottom of the list")
    public void verifyTasksQuantity(final String quantity) {
        Assert.assertEquals(applicationPage.getContentPanel().getTasksQuantity(), quantity.toUpperCase());
    }

    /**
     * Verifies the name of the list in Content Panel.
     */
    @Then("the user should see the name of the list on content Task")
    public void verifyNameListOnContentPanel() {
        String actual = applicationPage.getContentPanel().getContentListHeader(context.getList().getName());
        String expected = context.getList().getName();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Verifies the name of the list on Bar Title in Content Panel.
     */
    @Then("the user should see the name of the list on the Bar title of content panel")
    public void verifyNameListOnContentPanelBarTitle() {
        String actual = applicationPage.getContentPanel().getBarTitleListName(context.getList().getName());
        String expected = context.getList().getName();
        Assert.assertEquals(expected, actual);
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
        applicationPage.getListPanel().addNewFolder(projectName);
        context.getList().setName("List");
    }

    /**
     * Verifies the success message that appear when a copy is made.
     *
     * @param copyMessage is the Message to confirm.
     */
    @Then("the user should see the copy success message: {string}")
    public void successCopyMessage(final String copyMessage) {
        String expected = copyMessage;
        String actual = applicationPage.getListPanel().getCopyConfirmationMessage();
        Assert.assertEquals(expected, actual, "The message was not displayed.");
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
     * Realizes the drag and drop to complete status of a task.
     */
    @When("the user drags the task to Complete status")
    public void dragTaskToCompleteStatus() {
        applicationPage.getContentPanel().moveTask(context.getTask().getName());
    }

    /**
     * Verifies if the task is in complete status.
     */
    @Then("the user user should see the task in complete status.")
    public void verifyTaskInCompleteStatus() {
        Assert.assertTrue(applicationPage.getContentPanel().containsTask(context.getTask().getName()));
    }

    /**
     * Copies a list.
     *
     * @param copyList that represent the name for the project to copy.
     */
    @When("the user copies the list with FOLDERLESS LIST option and gives it the name {string}")
    public void copyList(final String copyList) {
        String actualListName = context.getList().getName();
        applicationPage.getListPanel().copylist(actualListName, copyList);
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
}
