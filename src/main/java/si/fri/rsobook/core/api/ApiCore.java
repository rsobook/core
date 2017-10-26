package si.fri.rsobook.core.api;

import si.fri.rsobook.core.api.client.Client;
import si.fri.rsobook.core.api.client.authorization.provider.base.ApiAuthProvider;

public class ApiCore {

    private ApiConfiguration configuration;
    private Client client;
    private ApiAuthProvider apiAuthProvider;

    private boolean defaultCoreContentHeader = false;

    public ApiCore(ApiConfiguration configuration, ApiAuthProvider apiAuthProvider) {
        this.configuration = configuration;
        this.client = new Client(configuration);
        this.apiAuthProvider = apiAuthProvider;
    }

    public ApiConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ApiAuthProvider getApiAuthProvider() {
        return apiAuthProvider;
    }

    public void setApiAuthProvider(ApiAuthProvider apiAuthProvider) {
        this.apiAuthProvider = apiAuthProvider;
    }

    public boolean getDefaultCoreContentHeader() {
        return defaultCoreContentHeader;
    }

    public void setDefaultCoreContentHeader(boolean defaultCoreContentHeader) {
        this.defaultCoreContentHeader = defaultCoreContentHeader;
    }
}
