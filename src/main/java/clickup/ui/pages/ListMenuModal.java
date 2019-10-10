package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ListMenuModal extends BasePage {
    @FindBy(xpath = "//a[@cutooltip='Delete']")
    private WebElement deleteBtn;

    @FindBy(className = "nav-menu-item__icon nav-menu-item__icon_copy icon")
    private WebElement copyLink;

    public DeleteModal deleteBtn(){
        deleteBtn.click();
        return new DeleteModal();
    }

    public CopyListModal copyBtn(){
        copyLink.click();
        return new CopyListModal();
    }
}
