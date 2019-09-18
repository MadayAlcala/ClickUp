/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package hook;

import core.selenium.WebDriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Hook class.
 */
public class Hook {
    private WebDriver webDriver;

    /**
     * Constructor of Hook.
     */
    public Hook() {
        this.webDriver = WebDriverManager.getInstance().getWebDriver();
    }

    /**
     * This method is executed after the scenarios.
     *
     * @param scenario that represent the scenarios of cucumber.
     */
    @After
    public void tearDown(final Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }
}
