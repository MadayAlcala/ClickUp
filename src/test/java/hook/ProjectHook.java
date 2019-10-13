package hook;

import clickup.entities.Context;
import clickup.ui.pages.ApplicationPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * ProjectHook class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ProjectHook {
    private Context context;
    private final int third = 3;

    /**
     * Allows to receive the variable context.
     *
     * @param context - set the class context.
     */
    public ProjectHook(final Context context) {
        this.context = context;
    }

    /**
     * Deletes a project.
     */
    @After(order = third, value = "@deleteProject")
    public void deleteProject() {
        ApplicationPage applicationPage = new ApplicationPage();
        applicationPage.getListPanel().deleteProject(context.getProject().getName());
    }

    /**
     * Creates a new project.
     */
    @Before(value = "@addNewProject")
    public void createList() {
        ApplicationPage applicationPage = new ApplicationPage();
        applicationPage.getListPanel().addNewFolder("ProjectTest");
    }
}
