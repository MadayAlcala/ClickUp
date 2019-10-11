package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CopyListModal extends BasePage {
    @FindBy(css = ".cu-form__input")
    private WebElement folderNameTxtBox;

    @FindBy(css = ".category-copy__button-block > .cu-btn")
    private WebElement copyFolderBtn;

    @FindBy(css = ".category-list__folderless > .cu-checkbox__label")
    private WebElement folderlessListCheckBox;

    public void changeName(String copyListName){
        WebElementActions.sendKeysWithDeleteText(folderNameTxtBox, copyListName);
    }

    public void clickfolderlessList(){
        WebElementActions.click(folderlessListCheckBox);
    }

    public ApplicationPage confirmCopy(){
        WebElementActions.click(copyFolderBtn);
        return new ApplicationPage();
    }
}
