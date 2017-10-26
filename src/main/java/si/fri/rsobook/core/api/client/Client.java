package si.fri.rsobook.core.api.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import si.fri.rsobook.core.api.ApiConfiguration;
import si.fri.rsobook.core.api.exception.ApiException;

import java.io.IOException;

public class Client {

    private HttpClient client;

    private ApiConfiguration configuration;

    public Client(ApiConfiguration configuration){
        this.configuration = configuration;
        this.client = HttpClientBuilder.create().build();
    }

    public HttpResponse execute(HttpUriRequest request) throws ApiException {
        HttpResponse response;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            throw new ApiException("Could not getInfo response from server");
        }
        return response;
    }

    public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public ApiConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ApiConfiguration configuration) {
        this.configuration = configuration;
    }

}
