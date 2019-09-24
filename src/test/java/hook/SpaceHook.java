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
import cucumber.api.java.After;

/**
 * Contains after actions for do in a account.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class SpaceHook {
    private Context context;

    /**
     * Allows to receive the variable context.
     *
     * @param context - set the class context.
     */
    public SpaceHook(Context context){
        this.context=context;
    }

    /**
     * Lets logout from to web page.
     */
    @After("@logout")
    public void logout() {
        SpacePage starPage =new SpacePage();
        starPage.logOut();
    }
}
