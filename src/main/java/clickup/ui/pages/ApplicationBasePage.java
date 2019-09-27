package clickup.ui.pages;

import clickup.ui.BasePage;
import clickup.ui.components.SideMenu;
import clickup.ui.components.TopMenu;

public class ApplicationBasePage extends BasePage {
    private TopMenu topMenu;
    private SideMenu sideMenu;

    public ApplicationBasePage() {
        super();
        this.topMenu = topMenu;
        this.sideMenu = sideMenu;
    }
}
