package steps;

import clickup.ui.pages.ImportPage;
import cucumber.api.java.en.When;

public class ImportStep {
    private ImportPage importPage;

    @When("The user access to import and fill fields")
    public void ImportFillFields() {
        importPage = new ImportPage();
        importPage.importTable();
    }
}
