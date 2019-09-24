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
import clickup.ui.pages.SpacePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Class contain the method to drive space .
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class SpaceStep {
    private SpacePage starPage;
    private Context context;

    /**
     * Constructor class.
     * @param context type Context class.
     */
    public SpaceStep(Context context) {
        this.context = context;
    }

    /**
     * This method let create in the web page new name space.
     *
     * @param nameSpace parameter type string.
     */
    @When("The user create a new space with the following name (.*)")
    public void createNewSpace(String nameSpace) {
        starPage = new SpacePage();
        starPage.addNewSpace(nameSpace);
        context.getSpace().setTitle(nameSpace);
    }

    /**
     * This method let compare if name space was create.
     *
     * @param nameSpace type string.
     */
    @Then("The space name with the name (.*) appear in the panel successfully")
    public void IfExistName(String nameSpace) {
        starPage.isFoundNameSpace(nameSpace);
    }
}
