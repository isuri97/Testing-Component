package TestingComponenetEndpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class CreateResourceDTO  {
  
  
  
  private String resourceId = null;
  
  
  private String policyUri = null;

  
  /**
   *  Returning resource Id after successfull registration.
   **/
  @ApiModelProperty(value = " Returning resource Id after successfull registration.")
  @JsonProperty("resourceId")
  public String getResourceId() {
    return resourceId;
  }
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  
  /**
   *  Policy_URI used for user access
   **/
  @ApiModelProperty(value = " Policy_URI used for user access")
  @JsonProperty("policyUri")
  public String getPolicyUri() {
    return policyUri;
  }
  public void setPolicyUri(String policyUri) {
    this.policyUri = policyUri;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateResourceDTO {\n");
    
    sb.append("  resourceId: ").append(resourceId).append("\n");
    sb.append("  policyUri: ").append(policyUri).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
