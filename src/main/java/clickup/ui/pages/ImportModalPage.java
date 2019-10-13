package clickup.ui.pages;

import clickup.ui.BasePage;
import core.utils.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class ImportModalPage extends BasePage {
    @FindBy(css = ".icon-upload")
    private WebElement uploadButton;

    @FindBy(id = "headerConfirm")
    private WebElement yesButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "final-close-include")
    private WebElement yesDialogButton;

    public void uploadFile(String fileImport) throws AWTException {
        uploadButton.click();
        StringSelection stringSelection = new StringSelection(fileImport);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        yesButton.click();
        continueButton.click();
        continueButton.click();
        yesDialogButton.click();
    }
}
