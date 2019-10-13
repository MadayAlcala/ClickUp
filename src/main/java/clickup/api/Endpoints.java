/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.api;

/**
 * Endpoints prefixes and suffixes.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public final class Endpoints {
    public static final String TEAM_SUFFIX = "team";
    public static final String SPACE_SUFFIX = "space";
    public static final String PROJECT_SUFFIX = "folder";
    public static final String LIST_SUFFIX = "list";
    public static final String TASK_SUFFIX = "task";

    /**
     * Private constructor to comply checkStyle suggestions.
     */
    private Endpoints() { }
}
