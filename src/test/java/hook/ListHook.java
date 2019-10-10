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
import clickup.ui.pages.DeleteModal;
import clickup.ui.pages.ListMenuModal;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * ListHook class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListHook {
    private Context context;
    private ApplicationPage applicationPage;
    private ListMenuModal listMenuModal;
    private DeleteModal deleteModal;
    private final int fourth = 4;

    /**
     * Allows to receive the variable context.
     *
     * @param context - set the class context.
     */
    public ListHook(final Context context) {
        this.context = context;
    }

    /**
     * Deletes a list.
     */
    @After(order = fourth, value = "@deleteList")
    public void deleteList() {
        applicationPage = new ApplicationPage();
        listMenuModal = applicationPage.getListPanel().displayListMenu(context.getProject().getName());
        deleteModal = listMenuModal.deleteBtn();
        applicationPage = deleteModal.confirmDelete();
    }

    /**
     * Creates a new list.
     */
    @Before(value = "@addNewList")
    public void createList() {
        ApplicationPage applicationPage = new ApplicationPage();
        applicationPage.getListPanel().addNewList("ListTest");
    }
}
