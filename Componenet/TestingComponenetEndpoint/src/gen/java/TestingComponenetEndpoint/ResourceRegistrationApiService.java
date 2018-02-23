package TestingComponenetEndpoint;

import TestingComponenetEndpoint.*;
import TestingComponenetEndpoint.dto.*;

import TestingComponenetEndpoint.dto.ErrorDTO;
import TestingComponenetEndpoint.dto.CreateResourceDTO;
import TestingComponenetEndpoint.dto.ResourceDetailsDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ResourceRegistrationApiService {
    public abstract Response registerResource(ResourceDetailsDTO resource);
}

