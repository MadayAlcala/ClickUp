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

public class ListStep {
    private ListPage listPage;

    /**
     * This method let create in the web page new list.
     *
     * @param nameList parameter type string.
     */
    @When("The user create a new list with the following name (.*)")
    public void createNewSpace(String nameList) {
        listPage = new ListPage();
        listPage.createList(nameList);
    }

    /**
     * This method let compare if name space was create.
     *
     * @param nameSpace type string.
     */
    @Then("The list name with the name (.*) appear in the panel successfully")
    public void IfExistName(String nameSpace) {
        Assert.assertTrue(listPage.isFoundListName(nameSpace));
    }
}
