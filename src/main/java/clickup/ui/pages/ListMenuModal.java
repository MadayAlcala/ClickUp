package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ListMenuModal extends BasePage {

    @FindBy(xpath = "//a[@cutooltip='Delete']")
    private WebElement deleteBtn;

    public DeleteModal deleteBtn(){
        deleteBtn.click();
        return new DeleteModal();
    }


}
