package TestingComponenetEndpoint;

import TestingComponenetEndpoint.dto.*;
import TestingComponenetEndpoint.ResourceRegistrationApiService;
import TestingComponenetEndpoint.factories.ResourceRegistrationApiServiceFactory;

import io.swagger.annotations.ApiParam;

import TestingComponenetEndpoint.dto.ErrorDTO;
import TestingComponenetEndpoint.dto.CreateResourceDTO;
import TestingComponenetEndpoint.dto.ResourceDetailsDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/ResourceRegistration")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/ResourceRegistration", description = "the ResourceRegistration API")
public class ResourceRegistrationApi  {

   private final ResourceRegistrationApiService delegate = ResourceRegistrationApiServiceFactory.getResourceRegistrationApi();

    @POST
    @Path("/resource")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add a resource", notes = "This method uses to register resources in Authorization server.", response = CreateResourceDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Resource registered successfully.\n"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid input.") })

    public Response registerResource(@ApiParam(value = "The resource description which resource owner save in Authorization server."  ) ResourceDetailsDTO resource)
    {
    return delegate.registerResource(resource);
    }
}

