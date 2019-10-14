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

import clickup.ui.PageTransporter;
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.HomeModal;
import clickup.ui.pages.LoginPage;
import cucumber.api.java.After;

/**
 * SideMenuHook class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class SideMenuHook {

    /**
     * Lets logout from to web page.
     */
    @After(order = 1, value = "@logout")
    public void logout() {
        PageTransporter.goToUrl("login");
        ApplicationPage applicationPage = new ApplicationPage();
        LoginPage loginPage;
        HomeModal homeModal;
        homeModal = applicationPage.getSideMenu().displayUserMenu();
        loginPage = homeModal.logOut();
    }
}
