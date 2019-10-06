package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectModalPage extends BasePage {
    @FindBy(className = "cu-form__input ng-pristine ng-valid ng-touched")
    private WebElement folderNameTxtBox;

    @FindBy(className = "cu-modal__control-item cu-modal__close icon")
    private WebElement folderCloseButton;

    /**
     * Writes a name for create a new Folder.
     *
     * @param folderName that is the new name for the folder.
     */
    public void assignNewFolderName(final String folderName) {
        Actions.sendKeys(folderNameTxtBox, folderName);
        Actions.enter(folderNameTxtBox);
    }

    /**
     * Closes a given Folder modal window.
     */
    public void close() {
        Actions.click(folderCloseButton);
    }
}
