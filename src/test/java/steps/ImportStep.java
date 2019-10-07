package steps;

import clickup.ui.pages.ImportPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class ImportStep {
    private ImportPage importPage;

    public ImportStep() {
        importPage = new ImportPage();
    }

    @When("the user import CSV file")
    public void ImportFillFields() {
        importPage.importTable();
    }

    @Then("the import is successfully")
    public void theImportIsSuccesfully() {
        Assert.assertEquals(true,importPage.isSuccessfullyImport());
    }

    @When("the user fill the fields requirement")
    public void theUserFillTheFieldsRequirement() {
        importPage.importManually();
    }
}
