package com.rxp.transactionmonitorserver;

import java.util.Arrays;
import java.util.List;

import org.oauth4j.core.OAuth4JService.Method;
import org.oauth4j.core.OAuth4JService.Protocol;
import org.oauth4j.core.OAuth4JServiceProvider;
import org.oauth4j.core.OAuth4JServiceProviderService;
import org.oauth4j.core.SimpleOAuth4JSPService;

public class PaybinServiceProvider implements OAuth4JServiceProvider {
    private static final String requestToken = "http://payb.in/oauth/request_token";
    private static final String authorize = "http://payb.in/oauth/confirm_access";
    private static final String accessToken = "http://payb.in/oauth/access_token";

    public OAuth4JServiceProviderService getRequestTokenEndpoint() {
        return new SimpleOAuth4JSPService(Protocol.HTTP, Method.GET,
                requestToken, String.class, 0);
    }

    public OAuth4JServiceProviderService getAuthorizeTokenEndpoint() {
        return new SimpleOAuth4JSPService(Protocol.HTTP, Method.GET,
                authorize, String.class, 0);
    }

    public OAuth4JServiceProviderService getAccessTokenEndpoint() {
        return new SimpleOAuth4JSPService(Protocol.HTTP, Method.GET,
                accessToken, String.class, 0);
    }

    @SuppressWarnings("unchecked")
    public <S extends OAuth4JServiceProviderService> List<S> getServices() {
        List<PaybinServiceProviderService> list = Arrays.asList(PaybinServiceProviderService.values());
        return (List<S>) list;
    }


}