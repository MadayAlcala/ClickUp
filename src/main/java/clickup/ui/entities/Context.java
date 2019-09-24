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
     * Constructor.
     */
    public Context() {
        this.space = new Space();
    }

    /**
     * Method let the ger the space.
     * @return space variable.
     */
    public Space getSpace() {
        return space;
    }

    /**
     * Mehtod to let set a space.
     * @param space variable.
     */
    public void setSpace(final Space space) {
        this.space = space;
    }
}
