package hook;

import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.ImportPage;
import cucumber.api.java.After;

public class ImportHook {

    @After("@logoutImport")
    public void logoutImport(){
        ImportPage importPage=new ImportPage();
        importPage.logout();
        ApplicationPage applicationPage = new ApplicationPage();
        applicationPage.getSideMenu().logOut();
    }
}
