package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewProjectModal extends BasePage {
    @FindBy(css = ".cu-form__input")
    private WebElement folderNameTxtBox;

    public ApplicationPage addName(String projectName) {
        WebElementActions.click(folderNameTxtBox);
        WebElementActions.sendKeys(folderNameTxtBox, projectName);
        WebElementActions.enter(folderNameTxtBox);
        return new ApplicationPage();
    }
}

