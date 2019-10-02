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
 * Allows to execute some steps for create a space.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class SpaceStep {
    private ApplicationPage applicationPage;
    private Context context;

    /**
     * Initializes the class setting the context.
     *
     * @param context type Context class.
     */
    public SpaceStep(final Context context) {
        this.context = context;
    }

    /**
     * Creates a new name space.
     *
     * @param nameSpace parameter type string.
     */
    @When("the user creates a new space with the following name {string}")
    public void createNewSpace(final String nameSpace) {
        applicationPage = new ApplicationPage();
        context.getSpace().setTitle(nameSpace);
        applicationPage.getSpacePanel().addNewSpace(nameSpace);
    }

    /**
     * Compares if name space was create.
     *
     * @param nameSpace type string.
     */
    @Then("the space name with the name {string} appear in the panel successfully")
    public void ifExistName(final String nameSpace) {
        Assert.assertTrue(applicationPage.getSpacePanel().isFoundNameSpace(nameSpace));
    }
}
