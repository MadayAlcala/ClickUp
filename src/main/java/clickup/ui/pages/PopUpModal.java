package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * PopUpModal class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class PopUpModal extends BasePage {
    @FindBy(css = "div.toast__undo.ng-tns-c0-0.ng-star-inserted")
    private WebElement informationPopUp;

    @FindBy(css = ".toast__undo-content")
    private WebElement copyConfirmationMessage;

    @FindBy(css = "div.toast__close-button-block")
    private WebElement closeButton;

    /**
     * Closes the modal that appears after the copy of a project.
     *
     * @return an ApplicationPage instance.
     */
    private ApplicationPage closeModal() {
        getWait().until(ExpectedConditions.elementToBeClickable(closeButton));
        WebElementActions.click(closeButton);
        return new ApplicationPage();
    }

    /**
     * Returns the message on the pop up modal that appears after a List or folder is copied.
     *
     * @return a String containing the message on the pop up modal that appears after a some actions is realized.
     */
    public String getCopyConfirmationMessage() {
        getWait().until(ExpectedConditions.visibilityOf(informationPopUp));
        String result = copyConfirmationMessage.getText();
        closeModal();
        return result;
    }
}
