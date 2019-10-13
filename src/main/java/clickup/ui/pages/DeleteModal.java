package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * DeleteModal class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class DeleteModal extends BasePage {
    @FindBy(css = ".cu-btn_danger > .cu-btn__text")
    private WebElement confirmDeleteBtn;

    /**
     * Click on 'delete' button to confirm the delete.
     *
     * @return a new ApplicationPage instance.
     */
    public ApplicationPage confirmDelete(){
        getWait().until(ExpectedConditions.visibilityOf(confirmDeleteBtn));
        getWait().until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn));
        WebElementActions.click(confirmDeleteBtn);
        return new ApplicationPage();
    }
}
