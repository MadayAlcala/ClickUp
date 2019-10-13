/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package steps;

import clickup.entities.Context;
import clickup.entities.List;
import clickup.ui.pages.ListPanelModal.AddNewModal;
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.ListPanelModal.CopyListModal;
import clickup.ui.pages.ListPanelModal.ListMenuModal;
import clickup.ui.pages.PopUpModal;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * ListStep class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListStep {
    private ApplicationPage applicationPage;
    private ListMenuModal listMenuModal;
    private AddNewModal addNewModal;
    private CopyListModal copyListModal;
    private PopUpModal popUpModal;
    private Context context;
    private List list;

    /**
     * Class constructor.
     *
     * @param context context.
     */
    public ListStep(final Context context) {
        this.context = context;
    }

    /**
     * Creates new list in a space.
     *
     * @param nameList that is the name of the new List.
     */
    @When("the user creates a new list with the following name {string}")
    public void createNewList(final String nameList) {
        applicationPage = new ApplicationPage();
        list = new List();
        list.setName(nameList);
        context.setList(list);
        context.getListMap().put(nameList, list);
        addNewModal = applicationPage.getListPanel().addNewBtn();
        applicationPage = addNewModal.getListBox();
        applicationPage.getListPanel().newNameList(nameList);
    }

    /**
     * Creates new list in a space.
     *
     * @param order to be stored in the collection within the context.
     * @param listName that is the name of the new List.
     */
    @When("the user creates a ([[first][second][third]]+) list with the following name (.*)")
    public void createNewLists(final String order, final String listName) {
        String trimmedListName = listName.replaceAll("\"", "");
        applicationPage = new ApplicationPage();
        //Ahora este bloque de codigo se encarga de hacer la creacion de una lista:
        addNewModal = applicationPage.getListPanel().addNewBtn();
        applicationPage = addNewModal.getListBox();
        applicationPage.getListPanel().newNameList(trimmedListName);
        //**********
        List list = new List();
        list.setName(trimmedListName);
        context.setList(list);
        context.getListMap().put(order, list);
    }

    /**
     * Checks that the list has been created.
     */
    @Then("the user should see the new list appear in the panel successfully")
    public void verifyNewListName() {
        String expected = context.getList().getName();
        String actual = applicationPage.getListPanel().getNameList(context.getList().getName());
        Assert.assertEquals(actual, expected, "The list has not been created.");
    }

    /**
     * Updates the name of a list.
     *
     * @param nameList that is the new name for the list.
     */
    @When("the user updates a list with the following name {string}")
    public void updateList(final String nameList) {
        applicationPage = new ApplicationPage();
        listMenuModal = applicationPage.getListPanel().displayListMenu(context.getList().getName());
        applicationPage = listMenuModal.renameBtn();
        context.getList().setName(nameList);
        applicationPage.getListPanel().updateNameList(nameList);
    }

    /**
     * Verifies the name of the list on Bar Title in Content Panel.
     */
    @Then("the user should see the name of the list on the Bar title of content panel")
    public void verifyNameListOnContentPanelBarTitle() {
        String actual = applicationPage.getContentPanel().getBarTitleListName(context.getList().getName());
        String expected = context.getList().getName();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Copies a list.
     *
     * @param copyList that represent the name for the project to copy.
     */
    @When("the user copies the list with FOLDERLESS LIST option and gives it the name {string}")
    public void copyList(final String copyList) {
        applicationPage = new ApplicationPage();
        String actualListName = context.getList().getName();
        listMenuModal = applicationPage.getListPanel().displayListMenu(actualListName);
        copyListModal = listMenuModal.copyBtn();
        list = new List();
        list.setName(copyList);
        context.setList(list);
        context.getListMap().put(copyList, list);
        copyListModal.clickfolderlessList();
        copyListModal.changeName(copyList);
        popUpModal = copyListModal.confirmCopy();
    }

    /**
     * Verifies the success message that appear when a copy is made.
     *
     * @param copyMessage is the Message to confirm.
     */
    @Then("the user should see the copy success message: {string}")
    public void successCopyMessage(final String copyMessage) {
        popUpModal = new PopUpModal();
        String expected = copyMessage;
        String actual = popUpModal.getCopyConfirmationMessage();
        Assert.assertEquals(expected, actual, "The message was not displayed.");
    }

    /**
     * Verifies the name of the list in Content Panel.
     */
    @Then("the user should see the name of the list on content Task")
    public void verifyNameListOnContentPanel() throws InterruptedException {
        applicationPage = new ApplicationPage();
        String expected = context.getList().getName();
        String actual = applicationPage.getContentPanel().getContentListHeader(context.getList().getName());
        Assert.assertEquals(expected, actual);
    }
}
