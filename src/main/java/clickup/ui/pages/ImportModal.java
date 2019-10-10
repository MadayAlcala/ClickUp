package clickup.ui.pages;

import clickup.ui.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImportModal extends BasePage {
    @FindBy(xpath = "//div[contains(@class,'cu-user-settings-menu__link ng-star-inserted')][contains(.,'Import/Export')]")
    private WebElement importExportMenuButton;
    public void Menu(){
        importExportMenuButton.click();
    }
}
