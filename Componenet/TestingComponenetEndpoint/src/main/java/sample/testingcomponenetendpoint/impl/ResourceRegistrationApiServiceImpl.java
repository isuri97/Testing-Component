package sample.testingcomponenetendpoint.impl;

import TestingComponenetEndpoint.*;

import TestingComponenetEndpoint.dto.CreateResourceDTO;
import TestingComponenetEndpoint.dto.ResourceDetailsDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.oauth.uma.service.exceptions.UMAClientException;
import org.wso2.carbon.identity.oauth.uma.service.exceptions.UMAServiceException;
import org.wso2.carbon.identity.oauth.uma.service.model.ResourceRegistration;
import org.wso2.carbon.identity.xacml.evalution.handler.impl.XACMLBasedAuthorizationHandler;

import javax.ws.rs.core.Response;

public class ResourceRegistrationApiServiceImpl extends ResourceRegistrationApiService {

    private static final Log log = LogFactory.getLog(ResourceRegistrationApiServiceImpl.class);

    @Override
    public Response registerResource(ResourceDetailsDTO resource) {

        try {

            ResourceRegistration registerResource = ResourceUtils.getResourceService()
                    .registerResource(ResourceUtils.getResource(resource));
            CreateResourceDTO createResourceDTO = ResourceUtils.createResponse(registerResource);
            return Response.status(Response.Status.CREATED).entity(createResourceDTO).build();
        } catch (UMAServiceException e) {
            log.debug("Server error when registering resource in resource server.", e);
        } catch (UMAClientException e) {
            if (log.isDebugEnabled()) {
                log.debug("Client error when registering resource in resource server.", e);
            }
        } catch (Throwable throwable) {
            log.error("Internal server error occurred. ", throwable);
        }
        return null;
    }
}
