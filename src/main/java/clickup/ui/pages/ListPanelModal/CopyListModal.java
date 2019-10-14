package clickup.ui.pages.ListPanelModal;

import clickup.ui.BasePage;
import clickup.ui.pages.AlertModal;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * CopyListModal class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class CopyListModal extends BasePage {
    @FindBy(css = ".cu-form__input")
    private WebElement folderNameTxtBox;

    @FindBy(css = ".category-copy__button-block > .cu-btn")
    private WebElement copyFolderBtn;

    @FindBy(css = ".category-list__folderless > .cu-checkbox__label")
    private WebElement folderlessListCheckBox;

    @FindBy(xpath = "//label/div[contains(.,'Copy everything')]")
    private WebElement copyEverythingRadioBtn;

    /**
     * Changes the name of the list or folder.
     *
     * @param copyListName that is the new name for the list.
     */
    public void changeName(final String copyListName) {
        WebElementActions.click(folderNameTxtBox);
        WebElementActions.sendKeysWithDeleteText(folderNameTxtBox, copyListName);
    }

    /**
     * Clicks on 'Folderless list' option.
     */
    public void clickfolderlessList() {
        WebElementActions.click(folderlessListCheckBox);
    }

    /**
     * Clicks on copy button to confirm de copy.
     *
     * @return a PopUp modal with the success message.
     */
    public AlertModal confirmCopy() {
        WebElementActions.click(copyFolderBtn);
        return new AlertModal();
    }

    /**
     * Clicks on 'Copy Everyting button'.
     */
    public void copyEverything() {
        WebElementActions.click(copyEverythingRadioBtn);
    }
}
