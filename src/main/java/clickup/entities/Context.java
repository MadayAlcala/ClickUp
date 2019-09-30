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
 * Contains all objects or variables that wee need save.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Context {
    private Space space;
    private List list;
    private User user;
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
     * @return a User' object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter method.
     *
     * @param user that is the object to change its behavior.
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Getter method.
     *
     * @return a Task' object.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Setter method.
     *
     * @param task that is the object to change its behavior.
     */
    public void setTask(Task task) {
        this.task = task;
    }
}
