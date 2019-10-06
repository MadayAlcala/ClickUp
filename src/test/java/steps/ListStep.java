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
import clickup.ui.pages.ProjectModalPage;
import cucumber.api.java.en.And;
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
    private ProjectModalPage projectModalPage;

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
        applicationPage.getListPanel().createList(nameList);
    }

    /**
     * Checks that the list has been created.
     */
    @Then("the user should see the new list appear in the panel successfully")
    public void name() {
        String expected = context.getList().getName();
        String actual = applicationPage.getListPanel().nameList(context.getList().getName());
        Assert.assertEquals(actual, expected, "The list has not been created.");
    }

    @When("the user updates a list with the following name {string}")
    public void updateList(String nameList) {
        applicationPage = new ApplicationPage();
        context.getList().setName(nameList);
        applicationPage.getListPanel().updateList(nameList);
    }

    @When("the user deletes the list")
    public void deleteList() {
        applicationPage = new ApplicationPage();
        String listsName = context.getList().getName();
        applicationPage.getListPanel().deleteList(listsName);
    }

    /**
     * Searches a task in the list view.
     */
    @And("the user searches a task with {string} keyword")
    public void searchTask(String key) {
        applicationPage.getContentPanel().searchTask(key);
    }

    @Then("the user should see displayed {string} at the bottom of the list")
    public void verifyTasksQuantity(String quantity) {
        Assert.assertEquals(applicationPage.getContentPanel().getTasksQuantity(), quantity.toUpperCase());
    }

    @And("the user should see the name of the list on content Task")
    public void verifyNameListOnContentPanel() {
        String actual = applicationPage.getContentPanel().getContentListHeader(context.getList().getName());
        String expected = context.getList().getName();
        Assert.assertEquals(expected, actual);
    }

    @And("the user should see the name of the list on the Bar title of content panel")
    public void verifyNameListOnContentPanelBarTitle() {
        String actual = applicationPage.getContentPanel().getBarTitleListName(context.getList().getName());
        String expected = context.getList().getName();
        Assert.assertEquals(expected, actual);
    }

    @And("the user creates a new project with the following name {string}")
    public void createProject(String projectName) {
        applicationPage = new ApplicationPage();
        applicationPage.getListPanel().addNewFolder(projectName);
//        applicationPage.getListPanel().assignNewFolderName(projectName);
    }

    @Then("the user should see the copy success message: {string}")
    public void successCopyMessage(String copyMessage) {
        String expected = copyMessage;
        String actual = applicationPage.getListPanel().getCopyConfirmationMessage();
        Assert.assertEquals(expected, actual, "The copy does not exist.");
    }
}
