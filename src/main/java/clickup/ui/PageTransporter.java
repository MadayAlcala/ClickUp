/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.ui;

import clickup.ui.pages.TaskModalPage;
import core.selenium.WebDriverManager;
import core.utils.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * PageTransporter class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public final class PageTransporter {
    private static final Map<String, String> map = new HashMap<>();
    private static final String APP_CONFIG_FILE = "app.properties";
    private static final String URL_BASE = "url";
    private static final WebDriver webDriver = WebDriverManager.getInstance().getWebDriver();

    static {
        map.put("login", "login");
        map.put("space", "https://app.clickup.com/3004860/v/l/s/3007916");
        map.put("task", "t/");
    }

    /**
     * This is the empty constructor according to checkstyle.
     */
    private PageTransporter() {
    }

    /**
     * This method is used for go to a page.
     *
     * @param url The parameter url defines a input url.
     */
    public static void goToUrl(final String url) {
        webDriver.navigate().to(getBaseUrl().concat(map.get(url)));
    }

    /**
     * Returns a String containing the base url of the web application.
     *
     * @return
     */
    private static String getBaseUrl() {
        PropertyReader.loadFile(APP_CONFIG_FILE);
        return PropertyReader.retrieveField(URL_BASE);
    }

    /**
     * Visits a Task's page by its id.
     *
     * @param taskId a String containing the id of a given Task.
     */
    public static TaskModalPage goToTaskPageById(final String taskId) {
        webDriver.navigate().to(getBaseUrl().concat(map.get("task")).concat(taskId));
        return new TaskModalPage();
    }
}
