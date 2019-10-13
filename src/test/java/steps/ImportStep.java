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

    @When("the user imports CSV file {string}")
    public void ImportFillFields(String fileImport) {
        importPage.importTable(fileImport);
    }

    @Then("the import should show that it was done successfully")
    public void theImportIsSuccesfully() {
        Assert.assertEquals(true, importPage.isSuccessfullyImport());
    }

    @When("deletetask")
    public void deletetask() {
        importPage.deleImportedTask();
    }

    @Then("the task import should be created in a space list")
    public void theTaskImportShouldBeCreatedInASpaceList() {

    }
}
