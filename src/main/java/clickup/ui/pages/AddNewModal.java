package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewModal extends BasePage {
    @FindBy(css = ".cu-dropdown-list-item__icon_new-folder")
    private WebElement folderBox;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    /**
     * Selects the option 'New List' to create a new List.
     */
    public ApplicationPage getListBox() {
        WebElementActions.click(listBox);
        return new ApplicationPage();
    }

    /**
     * Selects the option 'New List' to create a new List.
     */
    public NewProjectModal getProjectBox() {
        WebElementActions.click(folderBox);
        return new NewProjectModal();
    }
}
