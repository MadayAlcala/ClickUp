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

import clickup.entities.List;
import clickup.entities.Task;
import clickup.ui.components.ContentPanel;
import clickup.ui.components.ListPanel;
import clickup.ui.components.SpacePanel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * ApplicationPage class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ApplicationPage extends ApplicationBasePage {
    private SpacePanel spacePanel;
    private ListPanel listPanel;
    private ContentPanel contentPanel;

    /**
     * Class' constructor.
     */
    public ApplicationPage() {
        super();
        spacePanel = new SpacePanel();
        listPanel = new ListPanel();
        contentPanel = new ContentPanel();
    }

    /**
     * Getter method.
     *
     * @return a SpacePanel' object.
     */
    public SpacePanel getSpacePanel() {
        return spacePanel;
    }

    /**
     * Getter method.
     *
     * @return a ListPanel' object.
     */
    public ListPanel getListPanel() {
        return listPanel;
    }

    /**
     * Getter method.
     *
     * @return a ContentPanel' object.
     */
    public ContentPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Drags & Drops task from ContentPanel to ListPanel.
     *
     * @param task an instance of a Task entity.
     * @param destinationList an instance of a List entity.
     */
    public void dragTask(final Task task, final List destinationList) {
        WebElement from = contentPanel.getAnchorElementByTask(task);
        WebElement to = listPanel.getListElementByName(destinationList.getName());
        Actions actions = new Actions(getDriver());
        actions.clickAndHold(from).moveToElement(to).release().build().perform();
        contentPanel.waitUntilMessagePops();
    }
}
