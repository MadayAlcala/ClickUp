package clickup.ui.pages;

import clickup.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ImportPage extends BasePage {

    @FindBy(css = ".cu-avatar-container")
    private WebElement spaceBarButton2;

    @FindBy(xpath = "//div[contains(@class,'cu-user-settings-menu__link ng-star-inserted')][contains(.,'Import/Export')]")
    private WebElement importExportMenuButton;

    @FindBy(css = ".cu-integration-btn__title")
    private WebElement csvFileButton;

    @FindBy(css = ".cu-btn_fw")
    private WebElement importFromCsvButton;

    @FindBy(xpath= "//button[@id='continue']//ancestor::html")
    private WebElement scrollBlock;

    @FindBy(css = ".flatfile-component iframe")
    private WebElement block;


    @FindBy(xpath = "div[@id='hot']/div/div/div/div/table/tbody/tr/td")
    private WebElement fillFieldTaskName;

    public void importTable() {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        csvFileButton.click();
        importFromCsvButton.click();
//        getWait().until(ExpectedConditions.visibilityOf(block));
        WebDriver driver_1 = getDriver().switchTo().frame(block);
        WebElement button = driver_1.findElement(By.id("continue"));
        button.click();
//        scrollBlock.click();
//        getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(scrollBlock);
//        fillFieldTaskName.click();
//        fillFieldTaskName.sendKeys("dsadfasdf");
    }
}
