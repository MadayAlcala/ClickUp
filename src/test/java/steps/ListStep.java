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
    @When("The user creates a new list with the following name {string}")
    public void createNewSpace(final String nameList) {
        applicationPage = new ApplicationPage();
        context.getList().setName(nameList);
        applicationPage.getListPanel().createList(nameList);
    }

    /**
     * Checks that the list has been created.
     */
    @Then("The user should see the new list appear in the panel successfully")
    public void name() {
        String actual = applicationPage.getListPanel().nameList(context.getList().getName());
        String expected = context.getList().getName();
        Assert.assertEquals(actual, expected);
    }
}
