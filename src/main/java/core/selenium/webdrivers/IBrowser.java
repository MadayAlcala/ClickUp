/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package core.selenium.webdrivers;

import org.openqa.selenium.WebDriver;

/**
 * IBrowser class.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public interface IBrowser {
    /**
     * Defines a method for retrieving a WebDriver object.
     *
     * @return a WebDriver object according to the browser to be used during tests.
     */
    WebDriver init();
}
