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

    /**
     * Creates new list in a space.
     *
     * @param nameList that is the name of the new List.
     */
    @When("The user create a new list with the following name (.*)")
    public void createNewSpace(String nameList) {
        listPage = new ListPage();
        listPage.createList(nameList);
    }

    /**
     * Checks that the list has been created.
     *
     * @param nameList to search the specific element in 'ListPage' class.
     */
    @Then("The list name with the name (.*) appear in the panel successfully")
    public void name(String nameList) {
        Assert.assertEquals(listPage.nameTxtBox(nameList), nameList);
    }
}
