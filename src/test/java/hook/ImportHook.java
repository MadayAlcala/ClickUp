package hook;

import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.ImportPage;
import cucumber.api.java.After;

public class ImportHook {

    @After("@deleteImport")
    public void deleteImport(){
        ImportPage importPage=new ImportPage();
        importPage.deleImportedTask();
    }
}
