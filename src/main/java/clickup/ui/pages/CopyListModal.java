package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CopyListModal extends BasePage {
    @FindBy(css = ".cu-form__input")
    private WebElement folderNameTxtBox;

    @FindBy(className = "cu-btn")
    private WebElement copyFolderBtn;

    @FindBy(css = ".category-list__folderless > .cu-checkbox__label")
    private WebElement folderlessListCheckBox;

    public void changeName(String copyListName){
        folderNameTxtBox.click();
        folderNameTxtBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        folderNameTxtBox.sendKeys(copyListName);
    }

    public void clickfolderlessList(){
        WebElementActions.click(folderlessListCheckBox);
    }

    public ApplicationPage confirmCopy(){
        copyFolderBtn.click();
        return new ApplicationPage();
    }
}
