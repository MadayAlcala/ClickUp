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

import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.NotificationsPage;
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
    private static Map<String, String> map = new HashMap<>();
    private static final String APP_CONFIG_FILE = "app.properties";
    private static final String URL_BASE = "url";
    private static WebDriver webDriver = WebDriverManager.getInstance().getWebDriver();

    static {
        map.put("login", "login");
        map.put("space", "https://app.clickup.com/3004860/v/l/s/3007916");
        map.put("task", "t/");
        map.put("notifications", "notifications");
        map.put("reporting", "3004860/reporting");
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
     * @return a String containing the root URI of the web application under test.
     */
    public static String getBaseUrl() {
        PropertyReader.loadFile(APP_CONFIG_FILE);
        return PropertyReader.retrieveField(URL_BASE);
    }

    /**
     * Visits a Task's page by its id.
     *
     * @param taskId a String containing the id of a given Task.
     * @return a new instance of Task Modal Page Object Model.
     */
    public static TaskModalPage goToTaskPageById(final String taskId) {
        webDriver.navigate().to(getBaseUrl().concat(map.get("task")).concat(taskId));
        return new TaskModalPage();
    }

    /**
     * Visits the Notifications Page containing the task assigned to the user by the owner of a workplace.
     *
     * @param ownerId a String containing the id of the owner of the workplace where the user's task is located at.
     * @return an instance of NotificationsPage Page Object Model Class.
     */
    public static NotificationsPage goToNotificationsPage(final String ownerId) {
        webDriver.navigate().to(getBaseUrl().concat(ownerId).concat("/").concat(map.get("notifications")));
        return new NotificationsPage();
    }

    /**
     * Visits the Page of an existing Space within that belongs to a owner.
     *
     * @param teamId  a String containing the id of a given team (workplace).
     * @param spaceId a String containing the id of the space located at the
     *                workplace that belongs to user identified by it id.
     * @return an instance of NotificationsPage Page Object Model Class.
     */
    public static ApplicationPage goToSpacePageById(final String teamId, final String spaceId) {
        webDriver.navigate().to(getBaseUrl().concat(teamId).concat("/").concat("v/l/s/").concat(spaceId));
        return new ApplicationPage();
    }

    /**
     * Getter method.
     *
     * @return a map object containing the identifier/url-suffix pairs.
     */
    public static Map<String, String> getMap() {
        return map;
    }
}
