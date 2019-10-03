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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.Map;

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
}
