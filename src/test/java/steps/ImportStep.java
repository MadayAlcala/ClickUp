package steps;

import clickup.ui.pages.ImportPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class ImportStep {
    private ImportPage importPage;

    public ImportStep() {
        importPage = new ImportPage();;
    }

    @When("the user go to import and import CSV file")
    public void ImportFillFields() {
        importPage.importTable();
    }

    @Then("the import is succesfully")
    public void theImportIsSuccesfully() {
        Assert.assertEquals(true,importPage.isSuccessfullyImport());
    }
}
