package hook;

import clickup.entities.Context;
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.ImportPage;
import cucumber.api.java.After;

public class ImportHook {
    private Context context;

    public ImportHook(Context context) {
        this.context = context;
    }

    @After("@deleteImport")
    public void deleteImport(){
        ImportPage importPage=new ImportPage(context);
        importPage.deleImportedTask();
    }
}
