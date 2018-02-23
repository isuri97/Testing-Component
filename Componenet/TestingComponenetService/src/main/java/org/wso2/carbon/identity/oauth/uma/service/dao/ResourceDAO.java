/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.identity.oauth.uma.service.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.persistence.JDBCPersistenceManager;
import org.wso2.carbon.identity.oauth.uma.service.ResourceConstants;
import org.wso2.carbon.identity.oauth.uma.service.exceptions.UMAServiceException;
import org.wso2.carbon.identity.oauth.uma.service.model.MetaData;
import org.wso2.carbon.identity.oauth.uma.service.model.ResourceRegistration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Layer functionality for Resource management. This includes storing, updating, deleting
 * and retrieving resources.
 */
public class ResourceDAO {

    private static final Log log = LogFactory.getLog(ResourceDAO.class);

    /**
     * Add a resource
     *
     * @param resourceRegistration details of the registered resource
     * @return resourceId of resgistered resource description
     * @throws UMAServiceException ResourceException
     */
    public ResourceRegistration registerResource(ResourceRegistration resourceRegistration)
            throws UMAServiceException {

        String INSERT_RESOURCE = "INSERT INTO IDN_RESOURCE(RESOURCE_ID,RESOURCE_NAME,TIME_CREATED," +
                "RESOUCE_OWNER_ID,TENANT_ID) VALUES (?,?,?,?,?)";

        String INSERT_INTO_RESOURCE_META_DATA = "INSERT INTO IDN_RESOURCE_META_DATA(RESOURCE_ID," +
                "PROPERTY_KEY,PROPERTY_VALUE) VALUES (?,?,?)";

        String INSERT_INTO_RESOURCE_SCOPE1 = "INSERT INTO IDN_SCOPE (RESOURCE_ID,NAME)VALUES (?,?)";

        String resourcesql = INSERT_RESOURCE;
        String metadatasql =INSERT_INTO_RESOURCE_META_DATA;
        String scopesql = INSERT_INTO_RESOURCE_SCOPE1;

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(resourcesql)) {
                preparedStatement.setString(1, resourceRegistration.getResourceId());
                preparedStatement.setString(2, resourceRegistration.getName());
                preparedStatement.setTimestamp(3, resourceRegistration.getTimecreated());
                preparedStatement.setString(4, resourceRegistration.getResourceOwnerId());
                preparedStatement.setString(5, resourceRegistration.getTenentId());
                preparedStatement.execute();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(metadatasql)) {
                preparedStatement.setString(1, resourceRegistration.getResourceId());
                for (MetaData metaData : resourceRegistration.getPropertyData()) {
                    preparedStatement.setString(2, metaData.getKey());
                    preparedStatement.setString(3, metaData.getData());
                    preparedStatement.execute();
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(scopesql)) {
                preparedStatement.setString(1, resourceRegistration.getResourceId());
                preparedStatement.setString(2, String.valueOf(resourceRegistration.getScopes()));
                preparedStatement.execute();
                connection.commit();
            }

        } catch (SQLException e) {
            log.error("Error when retrieving the resource description. ");
            String errordescription = "Resource Id can not be found in data base";
            throw new UMAServiceException(ResourceConstants.ErrorMessages.ERROR_CODE_FAIL_TO_GET_RESOURCE,
                    errordescription);
        }
        return resourceRegistration;
    }
}