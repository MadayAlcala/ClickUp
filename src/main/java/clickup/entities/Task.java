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

import java.util.HashMap;
import java.util.Map;

/**
 * Task class.
 *
 * @author Maday Alcala, Alejandro Sánchez
 * @version 1.0
 */
public class Task {
    private String name;
    private String id;
    private String attachmentFile;
    private String description;
    private String priority;
    private String startDate;
    private String dueDate;

    /**
     * Getter method.
     *
     * @return a String containing name associated to this task.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method.
     *
     * @param name a String containing the new value to assign as a new name for this task.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter method.
     *
     * @return a the id associated to an instance of this class.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method.
     *
     * @param id A String containing the id to be assigned to an instance of this class.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Getter method.
     *
     * @return a String containing the name of the attached file of a Task.
     */
    public String getAttachmentFile() {
        return attachmentFile;
    }

    /**
     * Setter method.
     *
     * @param attachmentFile a String containing the name of a file to be attached to a Task.
     */
    public void setAttachmentFile(final String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    /**
     * Getter method.
     *
     * @return a String containing the description of a Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method.
     *
     * @param description a String containing the description of a Task.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Getter method.
     *
     * @return a String containing the priority of a Task.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Setter method.
     *
     * @param priority a String containing the priority of a Task.
     */
    public void setPriority(final String priority) {
        this.priority = priority;
    }

    /**
     * Getter method.
     *
     * @return a String containing the start date of a Task.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Setter method.
     *
     * @param startDate a String containing the start date of a Task.
     */
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter method.
     *
     * @return a String containing the due date of a Task.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Setter method.
     *
     * @param dueDate a String containing the due date of a Task.
     */
    public void setDueDate(final String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns a Map containing the set of keys declared as part of the configuration to be checked against.
     *
     * @return a Map containing the set of keys declared as part of the configuration to be checked against.
     */
    public Map getCurrentTaskConfiguration() {
        Map resultMap = new HashMap<String, String>();
        resultMap.put("Description", getDescription());
        resultMap.put("Start Date", getStartDate());
        resultMap.put("Due Date", getDueDate());
        return resultMap;
    }
}
