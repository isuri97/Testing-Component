package TestingComponenetEndpoint.factories;

import TestingComponenetEndpoint.ResourceRegistrationApiService;
import sample.testingcomponenetendpoint.impl.ResourceRegistrationApiServiceImpl;

public class ResourceRegistrationApiServiceFactory {

   private final static ResourceRegistrationApiService service = new ResourceRegistrationApiServiceImpl();

   public static ResourceRegistrationApiService getResourceRegistrationApi()
   {
      return service;
   }
}
