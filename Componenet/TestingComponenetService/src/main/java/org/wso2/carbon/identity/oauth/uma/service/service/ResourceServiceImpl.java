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

package org.wso2.carbon.identity.oauth.uma.service.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.oauth.uma.service.TestService;
import org.wso2.carbon.identity.oauth.uma.service.dao.ResourceDAO;
import org.wso2.carbon.identity.oauth.uma.service.exceptions.UMAClientException;
import org.wso2.carbon.identity.oauth.uma.service.exceptions.UMAException;
import org.wso2.carbon.identity.oauth.uma.service.exceptions.UMAServiceException;
import org.wso2.carbon.identity.oauth.uma.service.model.ResourceRegistration;
import org.wso2.carbon.identity.xacml.evalution.handler.impl.XACMLBasedAuthorizationHandler;

/**
 * TestService use for resource management
 */
public class ResourceServiceImpl implements TestService {

    private static final Log log = LogFactory.getLog(ResourceServiceImpl.class);
    ResourceDAO resourceDAO = new ResourceDAO();

    @Override
    public ResourceRegistration registerResource(ResourceRegistration resourceRegistration) throws
            UMAException {
        // check whether the resource id is provided
        if (StringUtils.isBlank(resourceRegistration.getName())) {
            String errorMessage = "Resource name can not be null.";
            throw new UMAClientException(404, errorMessage);
        } else {
            try {

                XACMLBasedAuthorizationHandler xacmlBasedAuthorizationHandler = new XACMLBasedAuthorizationHandler();
                xacmlBasedAuthorizationHandler.isAuthorized();
                log.info("Evaluate policy successfully without error.");

                resourceRegistration = resourceDAO.registerResource(resourceRegistration);
            } catch (UMAServiceException e) {
                String errorMessage = "Resource id is not persistant on db.";
                throw new UMAClientException(404, "Resource id not found", errorMessage);
            }

            return resourceRegistration;
        }
    }
}