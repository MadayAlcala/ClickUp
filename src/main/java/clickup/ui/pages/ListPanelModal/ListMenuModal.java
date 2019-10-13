package clickup.ui.pages.ListPanelModal;

import clickup.ui.BasePage;
import clickup.ui.pages.ApplicationPage;
import clickup.ui.pages.ListPanelModal.CopyListModal;
import clickup.ui.pages.ListPanelModal.DeleteModal;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * ListMenuModal class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListMenuModal extends BasePage {
    @FindBy(css = "[cutooltip='Delete']")
    private WebElement deleteBtn;

    @FindBy(css = " [cutooltip='Rename']")
    private WebElement renameBtn;

    @FindBy(css = "[icon='copy']")
    private WebElement copyLink;

    @FindBy(css = "[icon='move']")
    private WebElement moveLink;

    /**
     * Clicks on 'delete' button.
     *
     * @return a DeleteModal instance.
     */
    public DeleteModal deleteBtn() {
        WebElementActions.click(deleteBtn);
        return new DeleteModal();
    }

    /**
     * Clicks on 'copy' button.
     *
     * @return a CopyListModal instance.
     */
    public CopyListModal copyBtn() {
        WebElementActions.click(copyLink);
        return new CopyListModal();
    }

    /**
     * Clicks on 'rename' button.
     *
     * @return an ApplicationPage instance.
     */
    public ApplicationPage renameBtn() {
        WebElementActions.click(renameBtn);
        return new ApplicationPage();
    }
}
