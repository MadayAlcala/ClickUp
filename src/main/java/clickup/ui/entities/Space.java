/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.ui.entities;

/**
 * Let save some variables according our requirements.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Space {
    private String title;

    /**
     * Let gets the title for space name.
     *
     * @return title type string.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Let set the title to the space name.
     *
     * @param title type string.
     */
    public void setTitle(final String title) {
        this.title = title;
    }
}
