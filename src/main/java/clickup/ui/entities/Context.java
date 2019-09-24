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
 * Context class.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Context {
   private Space space;

    /**
     * Creation of the constructor to init the class Space.
     */
    public Context() {
        this.space = new Space();
    }

    /**
     * Let the get the space class.
     *
     * @return space variable.
     */
    public Space getSpace() {
        return space;
    }

    /**
     * Let set a space class.
     *
     * @param space variable.
     */
    public void setSpace(final Space space) {
        this.space = space;
    }
}
