package clickup.ui.components;

import clickup.ui.BasePage;
import clickup.ui.pages.LoginPage;
import core.utils.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * SideMenu class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class SideMenu extends BasePage {
    @FindBy(css = "[class='cu-user-settings-menu__link cu-user-settings-menu__link_logout']")
    private WebElement logOutButton;

    @FindBy(css = ".cu-avatar-container")
    private WebElement spaceBarButton;

    @FindBy(css = ".cu-user-settings-menu__column > .cu-user-settings-menu__title .cu-user-settings-menu__title-name")
    private WebElement titleNameTxt;

    /**
     * Clicks on the account avatar.
     */
    private void displayUserMenu() {
        Actions.click(spaceBarButton);
    }

    /**
     * Clicks on the account avatar.
     */
    private void logOutBtn() {
        Actions.click(logOutButton);
    }

    /**
     * Gets the account title name.
     *
     * @return a String with the title name.
     */
    public String getTitleName() {
        displayUserMenu();
        String titleName = Actions.getText(titleNameTxt);
        displayUserMenu();
        return titleName;
    }

    /**
     * Lets log out from the main page.
     */
    public void logOut() {
        spaceBarButton.click();
        logOutButton.click();
        LoginPage loginPage = new LoginPage();
        getWait().until(ExpectedConditions.visibilityOf(loginPage.getEmailWebElement()));
    }
}
