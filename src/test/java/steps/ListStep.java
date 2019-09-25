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

import clickup.ui.entities.Context;
import clickup.ui.pages.ListPage;
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
    private ListPage listPage;
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
    @When("The user create a new list with the following name (.*)")
    public void createNewSpace(final String nameList) {
        listPage = new ListPage();
        context.getList().setName(nameList);
        listPage.createList(nameList);
    }

    /**
     * Checks that the list has been created.
     *
     */
    @Then("The user should see the new list appear in the panel successfully")
    public void name() {
        Assert.assertEquals(listPage.nameTxtBox(context.getList().getName()), context.getList().getName());
    }
}
