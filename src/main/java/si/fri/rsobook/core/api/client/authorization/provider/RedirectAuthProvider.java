package si.fri.rsobook.core.api.client.authorization.provider;

import si.fri.rsobook.core.api.client.authorization.provider.base.ApiAuthProvider;

public class RedirectAuthProvider implements ApiAuthProvider {

    public String token;

    @Override
    public String getAuthorizationToken() {
        return token;
    }
}
