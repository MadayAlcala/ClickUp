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

    /**
     * Getter method.
     *
     * @return a TopMenu' object.
     */
    public TopMenu getTopMenu() {
        return topMenu;
    }

    /**
     * Setter method.
     *
     * @param topMenu that is the object to change its behavior.
     */
    public void setTopMenu(final TopMenu topMenu) {
        this.topMenu = topMenu;
    }

    /**
     * Getter method.
     *
     * @return a SideMenu' object.
     */
    public SideMenu getSideMenu() {
        return sideMenu;
    }

    /**
     * Setter method.
     *
     * @param sideMenu that is the object to change its behavior.
     */
    public void setSideMenu(final SideMenu sideMenu) {
        this.sideMenu = sideMenu;
    }
}
