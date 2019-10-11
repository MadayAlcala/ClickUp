package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListMenuModal extends BasePage {
    @FindBy(css = "[cutooltip='Delete']")
    private WebElement deleteBtn;

    @FindBy(css = " [cutooltip='Rename']")
    private WebElement renameBtn;

    @FindBy(css = "[icon='copy']")
    private WebElement copyLink;

    @FindBy(xpath = "[icon='move']")
    private WebElement moveLink;

    public DeleteModal deleteBtn(){
        WebElementActions.click(deleteBtn);
        return new DeleteModal();
    }

    public CopyListModal copyBtn(){
        WebElementActions.click(copyLink);
        return new CopyListModal();
    }

    public ApplicationPage renameBtn(){
        WebElementActions.click(renameBtn);
        return new ApplicationPage();
    }
}
