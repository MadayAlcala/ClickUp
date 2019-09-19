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

import clickup.ui.pages.LoginPage;
import clickup.ui.pages.PageTransporter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * LoginStep class.
 */
public class LoginStep {
    private LoginPage loginPage;

    /**
     * Navigates through pages.
     *
     * @param login represents the specific page.
     */
    @Given("The user goes to {string} page")
    public void navigatePage(final String login) {
        PageTransporter.goToUrl("https://app.clickup.com/".concat(login));
    }

    /**
     * Fills email and password in the login page.
     *
     * @param email    represents an email.
     * @param password represents a password.
     */
    @When("The user fills the form with {string} and {string}")
    public void fillingForm(final String email, final String password) {
        loginPage = new LoginPage();
        loginPage.getEmailField("madayalcala@gmail.com");
        loginPage.getPasswordField("tresconejos");
        loginPage.getLoginField();
    }

    /**
     * Verifies login information.
     */
    @Then("Username should appear in the panel")
    public void usernameShouldAppear() {
        loginPage.getAvatar();
        Assert.assertEquals(loginPage.getTitleName(), "Maday Alcala");
    }
}
