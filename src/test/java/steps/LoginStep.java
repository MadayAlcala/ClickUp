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

import clickup.ui.entities.User;
import clickup.ui.pages.LoginPage;
import clickup.ui.pages.PageTransporter;
import core.utils.CredentialDeserializer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * LoginStep class.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class LoginStep {
    private LoginPage loginPage;
    private User user;

    /**
     * Navigates through pages.
     *
     * @param login represents the specific page.
     */
    @Given("The user goes to {string} page")
    public void navigatePage(final String login) {
        PageTransporter.goToUrl(login);
    }

    /**
     * Fills email and password in the login page.
     *
     * @param userType    represents the type of user, i.e. user or admin.
     */
    @When("The {string} fills the form with email and password")
    public void fillingForm(final String userType) {
        loginPage = new LoginPage();
        user = CredentialDeserializer.getInstance().getUser(userType);
        loginPage.authenticate(user.getEmail(), user.getPassword());
    }

    /**
     * Verifies login information.
     */
    @Then("Username should appear in the panel")
    public void usernameShouldAppear() {
        Assert.assertEquals(loginPage.getTitleName(), user.getFullName());
    }
}
