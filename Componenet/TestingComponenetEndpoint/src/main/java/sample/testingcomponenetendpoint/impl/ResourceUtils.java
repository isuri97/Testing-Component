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

package sample.testingcomponenetendpoint.impl;

import TestingComponenetEndpoint.dto.CreateResourceDTO;
import TestingComponenetEndpoint.dto.ResourceDetailsDTO;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.oauth.uma.service.TestService;
import org.wso2.carbon.identity.oauth.uma.service.model.ResourceRegistration;


/**
 * This class holds the util methods used by ResourceRegistrationApiServiceImpl.
 */
public class ResourceUtils {

    public static TestService getResourceService() {

        return (TestService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(TestService.class, null);
    }



    public static ResourceRegistration getResource(ResourceDetailsDTO resourceDetailsDTO) {

        ResourceRegistration resourceRegistration = new ResourceRegistration();
        resourceRegistration.setName(resourceDetailsDTO.getName());
        resourceRegistration.setScopes(resourceDetailsDTO.getResource_scopes());
        if (resourceDetailsDTO.getIcon_uri() != null) {
            resourceRegistration.setIcon_uri("icon_uri", resourceDetailsDTO.getIcon_uri());
            resourceRegistration.getPropertyData().add(resourceRegistration.getIconuri());
        }
        if (resourceDetailsDTO.getType() != null) {
            resourceRegistration.setType("type", resourceDetailsDTO.getType());
            resourceRegistration.getPropertyData().add(resourceRegistration.getType());
        }
        if (resourceDetailsDTO.getDescription() != null) {
            resourceRegistration.setDescription("description", resourceDetailsDTO.getDescription());
            resourceRegistration.getPropertyData().add(resourceRegistration.getDescription());
        }
        return resourceRegistration;
    }
    /**
     * Returns a CreateResourceDTO object
     *
     * @param resourceRegistration specifies the details carried out by the ResourceRegistration Model class
     * @return A generic createResourceDTO with the specified details
     */
    public static CreateResourceDTO createResponse(ResourceRegistration resourceRegistration) {

        CreateResourceDTO createResourceDTO = new CreateResourceDTO();

        createResourceDTO.setResourceId(resourceRegistration.getResourceId());
        return createResourceDTO;
    }

}
