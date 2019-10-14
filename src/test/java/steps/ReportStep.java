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

import clickup.ui.pages.ListPanelModal.ReportingPage;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Report steps.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class ReportStep {
    @Then("the task should be displayed as Completed.")

    /**
     * Creates a new report.
     */
    public void theTaskShouldBeDisplayedAsCompleted() {
        ReportingPage reportingPage = new ReportingPage();
        reportingPage.verifyTaskDone();
        Assert.assertEquals( reportingPage.isDoneTaskUserTest(), true);
    }
}
