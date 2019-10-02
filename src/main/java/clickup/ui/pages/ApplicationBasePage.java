/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.ui.pages;

import clickup.ui.BasePage;
import clickup.ui.components.SideMenu;
import clickup.ui.components.TopMenu;

/**
 * ApplicationBasePage class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public abstract class ApplicationBasePage extends BasePage {
    private TopMenu topMenu;
    private SideMenu sideMenu;

    /**
     * Class' constructor.
     */
    protected ApplicationBasePage() {
        super();
        topMenu = new TopMenu();
        sideMenu = new SideMenu();
    }

    /**
     * Getter method.
     *
     * @return a TopMenu' object.
     */
    public TopMenu getTopMenu() {
        return topMenu;
    }

    /**
     * Getter method.
     *
     * @return a SideMenu' object.
     */
    public SideMenu getSideMenu() {
        return sideMenu;
    }
}
