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

import clickup.entities.Context;
import clickup.ui.pages.PageTransporter;
import clickup.ui.pages.SpaceMenu;
import cucumber.api.java.After;

/**
 * Contains after actions for do in a account.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class SpaceHook {
    private Context context;
    private final int third = 3;

    /**
     * Allows to receive the variable context.
     *
     * @param context - set the class context.
     */
    public SpaceHook(final Context context) {
        this.context = context;
    }

    /**
     * Deletes a space.
     */
    @After(order = third, value = "@deleteSpace")
    public void deleteSpace() {
        SpaceMenu spaceMenu = new SpaceMenu();
        spaceMenu.deleteSpace(context.getSpace().getTitle());
        PageTransporter.goToUrl("login");
    }
}
