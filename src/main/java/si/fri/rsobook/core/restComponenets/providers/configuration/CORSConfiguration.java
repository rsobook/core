package si.fri.rsobook.core.restComponenets.providers.configuration;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

public class CORSConfiguration implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext creqc, ContainerResponseContext cresc) throws IOException {
        cresc.getHeaders().add("Access-Control-Allow-Origin", "*");
        cresc.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-content");
        cresc.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cresc.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        cresc.getHeaders().add("Access-Control-Max-Age", "1209600");
        cresc.getHeaders().add("Access-Control-Expose-Headers", "x-count");
    }

}