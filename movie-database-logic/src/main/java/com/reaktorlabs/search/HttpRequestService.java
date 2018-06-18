package com.reaktorlabs.search;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ernst
 */
@Stateless
public class HttpRequestService {
    
    
    public HttpRequestService() {
        
    }
    
    public String searchInMovie(String url) {
        final Client client = Client.create();
        final WebResource resource = client.resource(url);
        final ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new IllegalStateException("invalid request " + response.getStatus());
        }
        return response.getEntity(String.class);
    }
   
}
