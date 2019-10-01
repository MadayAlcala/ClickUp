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
    public ApplicationBasePage() {
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
     * Setter method.
     *
     * @param topMenu that is the object to change its behavior.
     */
    public void setTopMenu(final TopMenu topMenu) {
        this.topMenu = topMenu;
    }

    /**
     * Getter method.
     *
     * @return a SideMenu' object.
     */
    public SideMenu getSideMenu() {
        return sideMenu;
    }

    /**
     * Setter method.
     *
     * @param sideMenu that is the object to change its behavior.
     */
    public void setSideMenu(final SideMenu sideMenu) {
        this.sideMenu = sideMenu;
    }
}
