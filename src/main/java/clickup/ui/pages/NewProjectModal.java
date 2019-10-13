package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * NewProjectModal class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class NewProjectModal extends BasePage {
    @FindBy(css = ".cu-form__input")
    private WebElement folderNameTxtBox;

    @FindBy(className = "cu-modal__control-item cu-modal__close icon")
    private WebElement folderCloseButton;

    /**
     * Adds a name on NewProjectModal.
     *
     * @param projectName a string with the name for the folder.
     * @return an ApplicationPage instance.
     */
    public ApplicationPage addName(String projectName) {
        WebElementActions.click(folderNameTxtBox);
        WebElementActions.sendKeys(folderNameTxtBox, projectName);
        WebElementActions.enter(folderNameTxtBox);
        return new ApplicationPage();
    }
}

