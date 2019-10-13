package clickup.ui.pages.ListPanelModal;

import clickup.ui.BasePage;
import clickup.ui.pages.ApplicationPage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * ListPanelModal class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class AddNewModal extends BasePage {
    @FindBy(css = ".cu-dropdown-list-item__icon_new-folder")
    private WebElement folderBox;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    /**
     * Selects the option 'New List' to create a new List.
     *
     * @return an ApplicationPage instance.
     */
    public ApplicationPage getListBox() {
        WebElementActions.click(listBox);
        return new ApplicationPage();
    }

    /**
     * Selects the option 'New List' to create a new List.
     *
     * @return an NewProjectModal instance.
     */
    public NewProjectModal getProjectBox() {
        WebElementActions.click(folderBox);
        return new NewProjectModal();
    }
}
