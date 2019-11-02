/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package clickup.entities;

/**
 * Imports.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Import {
    private boolean importIsOk;
    private boolean showMessage;

    /**
     * Lets verify the message.
     *
     * @return boolean variable.
     */
    public boolean isShowMessage() {
        return showMessage;
    }

    /**
     * Lets set the message.
     *
     * @param showMessage variable.
     */
    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    /**
     * Lets get the import.
     *
     * @return boolean variable.
     */
    public boolean getImportIsOk() {
        return importIsOk;
    }

    /**
     * Lets to set the import.
     *
     * @param importIsOk boolean variable.
     */
    public void setImportIsOk(Boolean importIsOk) {
        this.importIsOk = importIsOk;
    }
}
