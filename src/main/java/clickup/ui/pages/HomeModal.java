package clickup.ui.pages;

import clickup.ui.BasePage;
import clickup.ui.PageTransporter;
import core.utils.Log;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * HomeModal class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class HomeModal extends BasePage {
    @FindBy(css = "[class='cu-user-settings-menu__link cu-user-settings-menu__link_logout']")
    @CacheLookup
    private WebElement logOutButton;

    @FindBy(css = ".cu-user-settings-menu__column > .cu-user-settings-menu__title .cu-user-settings-menu__title-name")
    @CacheLookup
    private WebElement titleNameTxt;

    /**
     * Gets the account title name.
     *
     * @return a String with the title name.
     */
    public String getTitleName() {
        String titleName = WebElementActions.getText(titleNameTxt);
        PageTransporter.goToUrl("login");
        return titleName;
    }

    /**
     * Lets log out from the main page.
     */
    public LoginPage logOut() {
        WebElementActions.click(logOutButton);
        LoginPage loginPage = new LoginPage();
        getWait().until(ExpectedConditions.visibilityOf(loginPage.getEmailWebElement()));
        return new LoginPage();
    }
}
