package clickup.ui.pages;

import clickup.ui.BasePage;
import clickup.ui.components.SideMenu;
import clickup.ui.components.TopMenu;

/**
 * ApplicationBasePage class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ApplicationBasePage extends BasePage {
    private TopMenu topMenu;
    private SideMenu sideMenu;

    /**
     * Class' constructor.
     */
    public ApplicationBasePage() {
        super();
        topMenu = new TopMenu();
        sideMenu = new SideMenu();
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(TopMenu topMenu) {
        this.topMenu = topMenu;
    }

    public SideMenu getSideMenu() {
        return sideMenu;
    }

    public void setSideMenu(SideMenu sideMenu) {
        this.sideMenu = sideMenu;
    }
}
