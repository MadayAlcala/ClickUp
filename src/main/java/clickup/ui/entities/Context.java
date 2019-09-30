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
 * Contains all objects or variables that wee need save.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Context {
    private Space space;
    private List list;
    private Task task;

    /**
     * Initializes the variables.
     */
    public Context() {
        this.space = new Space();
        this.list = new List();
        this.task = new Task();
    }

    /**
     * Gets the space class.
     *
     * @return space class.
     */
    public Space getSpace() {
        return space;
    }

    /**
     * Sets a space class.
     *
     * @param space variable.
     */
    public void setSpace(final Space space) {
        this.space = space;
    }

    /**
     * Getter method.
     *
     * @return a List' object.
     */
    public List getList() {
        return list;
    }

    /**
     * Setter method.
     *
     * @param list that is the object to change its behavior.
     */
    public void setList(final List list) {
        this.list = list;
    }

    /**
     * Getter method.
     *
     * @return a Task instance to be shared across step definitions.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Setter method.
     *
     * @param task A Task instance to be shared across step definitions.
     */
    public void setTask(Task task) {
        this.task = task;
    }
}
