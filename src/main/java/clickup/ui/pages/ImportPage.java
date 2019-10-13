package clickup.ui.pages;

import clickup.entities.Context;
import clickup.ui.BasePage;
import core.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ImportPage extends BasePage {
    private Context context;
    private Boolean isSuccessfully;
    private static final String SPACE_MENU = "//div[@class='user-list-item ng-star-inserted']//div[./text()='%s']";
    @FindBy(css = ".cu-avatar-container")
    private WebElement spaceBarButton2;

    @FindBy(xpath = "//*[contains(text(), 'Import/Export')]")
    private WebElement importExportMenuButton;

    @FindBy(css = ".cu-integration-btn__title")
    private WebElement csvFileButton;

    @FindBy(css = ".cu-btn_fw")
    private WebElement importFromCsvButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = ".flatfile-component iframe")
    private WebElement block;

    @FindBy(css = ".ReactModalPortal")
    private WebElement modalyesOrFalse;

    @FindBy(css = ".cu-btn.ng-star-inserted")
    private WebElement selectSpaceButton;

//    @FindBy(xpath = "//div[contains(@class,'user-list-item__name')][contains(.,'ads')]")
//    private WebElement selectSpaceMenu;

    @FindBy(css = ".cu-nav-sts__back")
    private WebElement backButton;

    @FindBy(css = ".cu-banner-popup__button.cu-banner-popup__button_purple")
    private WebElement bannerPurpleButton;

    @FindBy(css = ".cu-import-progress__data-status.importPending")
    private WebElement imputProgressButton;

    @FindBy(xpath = "//div[contains(@class,'col-required')][contains(.,'Task name')]")
    private WebElement taskNameTitleTable;

    @FindBy(css = ".cdk-overlay-container")
    private WebElement containerMenuModal;

    @FindBy(xpath = "//*[contains(text(), 'Delete imported')]")
    private WebElement deleteImportedButton;

    @FindBy(xpath = "//*[contains(text(), 'PAST')]")
    private WebElement pastImportButton;

    @FindBy(xpath = "//*[contains(text(), 'Delete tasks')]")
    private WebElement deleteTaskButton;

    @FindBy(xpath = "//*[contains(text(), 'ads')]")
    private WebElement spaceAds;

    @FindBy(xpath = "//*[contains(text(), 'pepito')]")
    private WebElement task1;

    @FindBy(xpath = "//*[contains(text(), 'Next')]")
    private WebElement continueButtonImport;

    @FindBy(xpath = "//div[contains(@class,'cu-settings-section cu-settings-section_open ng-star-inserted')]//*[contains(text(), 'Next')]")
    private WebElement continueButtonImport2;

    @FindBy(xpath = "//button[contains(@class,'cu-btn')][contains(text(), 'Start Import')]")
    private WebElement startImportButton;

    /**
     * Constructor.
     * @param context parameter.
     */
    public ImportPage(Context context) {
        this.context = context;
    }

    public void importTable(String fileImport) {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        csvFileButton.click();
        importFromCsvButton.click();
        getDriver().switchTo().frame(block);
        ImportModalPage importModalPage = new ImportModalPage();
        try {
            importModalPage.uploadFile(fileImport);
        } catch (AWTException e) {
            e.printStackTrace();
            Log.getInstance().getLog().error(e);
        }
        getDriver().switchTo().defaultContent();
        selectSpaceButton.click();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(500);
        getDriver().findElement(By.xpath(String.format(SPACE_MENU,context.getSpace().getTitle()))).click();
        continueButtonImport.click();
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);
        robot.delay(50);
        continueButtonImport2.click();
        startImportButton.click();
        pastImportButton.click();
        imputProgressButton.click();
        isSuccessfully = imputProgressButton.isEnabled();
        backButton.click();
    }

    /**
     * Returns a space webElement.
     *
     * @param spaceName that is the name of the space to find.
     * @return WebElement 'space'.
     */
    private WebElement getSpaceElementByName(final String spaceName) {
        return getDriver().findElement(By.xpath(String.format(SPACE_MENU, spaceName)));
    }

    public boolean isSuccessfullyImport() {
        return isSuccessfully;
    }

    public void deleImportedTask() {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        pastImportButton.click();
        deleteImportedButton.click();
        getWait().until(ExpectedConditions.elementToBeClickable(deleteTaskButton));
        deleteTaskButton.click();
        backButton.click();
    }

    public boolean isCreateTask(){
        spaceAds.click();
        boolean asdf;
        getWait().until(ExpectedConditions.elementToBeClickable(task1));
        asdf = task1.isDisplayed();
        return asdf;
    }
}