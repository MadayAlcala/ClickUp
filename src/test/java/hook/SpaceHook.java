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

import clickup.ui.entities.Context;
import clickup.ui.pages.SpacePage;
import core.selenium.WebDriverManager;
import core.utils.Log;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.log4j.spi.ErrorCode;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Contains after actions for do in a account.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class SpaceHook {
    private Context context;
    private WebDriver webDriver;
    /**
     * Allows to receive the variable context.
     *
     * @param context - set the class context.
     */
    public SpaceHook(final Context context) {
        this.context = context;
        this.webDriver = WebDriverManager.getInstance().getWebDriver();
    }

    /**
     * Lets logout from to web page.
     */
    @After(order = 1, value = "@logout")
    public void logout(Scenario scenario) {
        try {
            SpacePage starPage = new SpacePage();
            starPage.logOut();
        }
        catch (Exception ex){
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            Log.getInstance().getLog().error(ex);
            throw new NullPointerException("File not found" + ex);
        }
    }
}
