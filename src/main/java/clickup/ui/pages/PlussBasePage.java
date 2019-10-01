/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.ui.pages;

import clickup.ui.components.SpaceBar;

public abstract class PlussBasePage extends ApplicationBasePage {
    private SpaceBar spaceBar;

    /**
     * Class' constructor.
     */
    public PlussBasePage() {
        super();
        spaceBar = new SpaceBar();
    }

    /**
     * Getter method.
     *
     * @return a SpaceBar' object.
     */
    public SpaceBar getSpaceBar() {
        return spaceBar;
    }

    /**
     * Setter method.
     *
     * @param spaceBar that is the object to change its behavior.
     */
    public void setSpaceBar(SpaceBar spaceBar) {
        this.spaceBar = spaceBar;
    }
}
