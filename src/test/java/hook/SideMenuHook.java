package hook;

import clickup.ui.pages.SpaceMenu;
import cucumber.api.java.After;

/**
 * SideMenuHook class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class SideMenuHook {

    /**
     * Lets logout from to web page.
     */
    @After(order = 1, value = "@logout")
    public void logout() {
        SpaceMenu starPage = new SpaceMenu();
        starPage.getSideMenu().logOut();
    }
}
