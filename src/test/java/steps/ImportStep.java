/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package steps;

import clickup.entities.Context;
import clickup.ui.pages.ImportPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.List;

/**
 * Import steps.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class ImportStep {
    private Context context;
    private ImportPage importPage;

    /**
     * Sets the constructor.
     *
     * @param context variable.
     */
    public ImportStep(Context context) {
        this.context = context;
        importPage = new ImportPage(this.context);
    }

    /**
     * Lets import a file csv.
     *
     * @param fileImport variable.
     */
    @When("the user imports CSV file {string}")
    public void ImportFillFields(String fileImport) {
        importPage.importTable(fileImport);
    }

    /**
     * Lets asserts if the import was correct.
     */
    @Then("the import should show that it was done successfully")
    public void theImportIsSuccesfully() {
        Assert.assertEquals(true, importPage.isSuccessfullyImport());
    }

    /**
     * Lets delete a task.
     */
    @When("deletetask")
    public void deletetask() {
        importPage.deleImportedTask();
    }

    /**
     * Lets import a file csv manually.
     *
     * @param informationList variable.
     */
    @When("the user imports CSV file manually")
    public void theUserImportsCSVFileManually(List<String> informationList) {
        importPage.manualImport(informationList);
    }

    /**
     * Trys to import a file csv with out data.
     */
    @When("the user imports CSV file without filling in the data")
    public void theUserImportsCSVFileWithoutFillingInTheData() {
        importPage.negativeTest();

    }

    /**
     * Lets verify the message.
     */
    @Then("the web applications must show there is no data pasted to be submitted")
    public void theWebApplicationsMustShowThereIsNoDataPastedToBeSubmitted() {
        Assert.assertEquals(true, importPage.isMessageShow());
    }
}
