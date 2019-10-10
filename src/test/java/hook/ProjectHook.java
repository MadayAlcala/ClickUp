package hook;

import clickup.entities.Context;
import clickup.entities.Project;
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.DeleteModal;
import clickup.ui.pages.ListMenuModal;
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
    private ApplicationPage applicationPage;
    private ListMenuModal listMenuModal;
    private DeleteModal deleteModal;
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
        applicationPage = new ApplicationPage();
        listMenuModal = applicationPage.getListPanel().displayProjectMenu(context.getProject().getName());
        deleteModal = listMenuModal.deleteBtn();
        applicationPage = deleteModal.confirmDelete();
    }

    /**
     * Deletes projects.
     */
    @After(order = third, value = "@deleteAllProjects")
    public void deleteAllProjects() {
        while (context.getProjectMap().size() > 0) {
            applicationPage = new ApplicationPage();
            listMenuModal = applicationPage.getListPanel().displayProjectMenu(context.getProject().getName());
            deleteModal = listMenuModal.deleteBtn();
            applicationPage = deleteModal.confirmDelete();
        }
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
