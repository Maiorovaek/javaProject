package com;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
public class CalculationRestClient {
    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();
        URI baseURI = null;
        try{
            baseURI = new URI("http://localhost:8080/rest");
        }catch (URISyntaxException ex){
            ex.printStackTrace();
        }

        //http://localhost:8080/rest/calc/add/2/5
        //http://localhost:8080/rest/calc/div/2/5
          WebTarget webTarget = client.target(baseURI).path("calc").path("div").path("4").path("5");
         WebTarget webTarget2 = client.target(baseURI).path("calc").path("add").path("2").path("5");

          Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
         Response response = invocationBuilder.post(null);
         System.out.println(response.readEntity(String.class));
          Response response1 = webTarget.request().get();
         System.out.println(response.readEntity(String.class));
           System.out.println(response1.readEntity(String.class));
    }
}
