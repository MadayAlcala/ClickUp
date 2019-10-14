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
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.ImportPage;
import cucumber.api.java.After;

/**
 * ImportHooks.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class ImportHook {
    private Context context;
    private ApplicationPage applicationPage;
    private ImportPage importPage;

    /**
     * Lets set the constructor.
     *
     * @param context variable.
     */
    public ImportHook(Context context) {
        this.context = context;
    }

    /**
     * Lets delete import.
     */
    @After("@deleteImport")
    public void deleteImport() {
        ImportPage importPage = new ImportPage(context);
        importPage.deleImportedTask();
    }
}
