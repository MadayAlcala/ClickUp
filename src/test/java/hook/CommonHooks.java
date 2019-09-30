package hook;

import clickup.ui.pages.SpaceMenu;
import cucumber.api.java.After;

public class CommonHooks {

    /**
     * Lets logout from to web page.
     */
    @After(order = 1, value = "@logout")
    public void logout() {
        SpaceMenu starPage = new SpaceMenu();
        starPage.getSideMenu().logOut();
    }
}
