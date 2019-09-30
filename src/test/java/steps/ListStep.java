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
import clickup.ui.pages.ListMenu;
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
    private ListMenu listMenu;
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
    @When("The user creates a new list with the following name {string}")
    public void addNewList(final String nameList) {
        listMenu = new ListMenu();
        context.getList().setName(nameList);
        listMenu.createList(nameList);
    }

    /**
     * Checks that the list has been created.
     */
    @Then("The user should see the new list appear in the panel successfully")
    public void assertListName() {
        Assert.assertEquals(listMenu.nameList(context.getList().getName()), context.getList().getName());
    }
//
//    @And("the user creates a task with the following name {string}")
//    public void theUserCreatesATaskWithTheFollowingName(String taskName) {
//        context.getTask().setName(taskName);
//        listMenu.addNewTask(context.getList().getName(), taskName);
//    }

    /**
     * Searches a task in the list view.
     */
    @And("the user searches the task")
    public void theUserSearchesTheTask() {
        listMenu.searchTask(context.getTask().getName());
    }

    /**
     * Finds in the dashboard the task searched.
     */
    @Then("the user should see the task listed in the search result.")
    public void theUserShouldSeeTheTaskListedInTheSearchResult() {
        String actual = listMenu.findTask(context.getTask().getName());
        String expected = context.getTask().getName();
        Assert.assertEquals(actual, expected);
    }
}
