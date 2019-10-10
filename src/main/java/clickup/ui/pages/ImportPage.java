package clickup.ui.pages;

import clickup.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ImportPage extends BasePage {
    private Boolean isSuccessfully;
    @FindBy(css = ".cu-avatar-container")
    private WebElement spaceBarButton2;

    @FindBy(xpath = "//*[contains(text(), 'Import/Export')]")
    private WebElement importExportMenuButton;

    @FindBy(css = ".cu-integration-btn__title")
    private WebElement csvFileButton;

    @FindBy(css = ".cu-btn_fw")
    private WebElement importFromCsvButton;

    @FindBy(id = "continue")
    private WebElement scrollBlock;

    @FindBy(css = ".flatfile-component iframe")
    private WebElement block;

    @FindBy(css = ".icon-upload")
    private WebElement uploadButton;

    @FindBy(css = ".ReactModalPortal")
    private WebElement modalyesOrFalse;

    @FindBy(css = ".cu-btn.ng-star-inserted")
    private WebElement selectSpaceButton;


    @FindBy(xpath = "//div[contains(@class,'user-list-item__name')][contains(.,'ads')]")
    private WebElement selectSpaceMenu;

    @FindBy(css = ".cu-nav-sts__back")
    private WebElement backButton;

    @FindBy(xpath = "//div[contains(@class,'col-required')][contains(.,'Task name')]")
    private WebElement taskNameTitleTable;


    @FindBy(css = ".cdk-overlay-container")
    private WebElement containerMenuModal;


    public void importTable() {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        csvFileButton.click();
        importFromCsvButton.click();
        WebDriver driver_1 = getDriver().switchTo().frame(block);
        WebElement sentfile = driver_1.findElement(By.cssSelector(".icon-upload"));
        sentfile.click();


        StringSelection ss = new StringSelection("/home/pepillo/csvtest.csv");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(500);

        WebElement yesButton = driver_1.findElement(By.id("headerConfirm"));
        yesButton.click();

        WebElement button = driver_1.findElement(By.id("continue"));
        button.click();
        WebElement button2 = driver_1.findElement(By.id("continue"));
        button2.click();

        WebElement yesButtonSubmit = driver_1.findElement(By.id("final-close-include"));
        yesButtonSubmit.click();
        getDriver().switchTo().defaultContent();
        selectSpaceButton.click();
        robot.delay(500);

        selectSpaceMenu.click();

        //verify if exist
//        cu-banner-popup__button cu-banner-popup__button_purple
//        .cu-banner-popup__button.cu-banner-popup__button_purple
        WebElement bannerButton = driver_1.findElement(By.cssSelector(".cu-banner-popup__button.cu-banner-popup__button_purple"));
        bannerButton.click();

        WebElement pendingStatus = driver_1.findElement(By.cssSelector(".cu-import-progress__data-status.importPending"));
//        getWait().until(ExpectedConditions.elementToBeClickable(pendingStatus));
        isSuccessfully = pendingStatus.isEnabled();
//        isSuccessfully=bannerButton.
        //div[contains(@class,'cu-import-progress__data-status importPending')]
        System.out.println("---------------------------------------");
    }

    public boolean isSuccessfullyImport() {
        return isSuccessfully;
    }

    public void logout() {
        backButton.click();
    }


    public void importManually() {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        csvFileButton.click();
        importFromCsvButton.click();

        WebDriver driver_1 = getDriver().switchTo().frame(block);
        //div[contains(@class,'col-required')][contains(.,'Task name')]
//        WebElement dataTable = driver_1.findElement(By.xpath("//div[contains(@class,'col-required')][contains(.,'Task name')]"));
//        dataTable.click();
//        taskNameTitleTable.click();


        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(5000);
        robot.mouseMove(100, 700);

        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        StringSelection ss = new StringSelection("task1");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(5000);
    }
}

