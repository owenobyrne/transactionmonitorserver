package com.rxp.transactionmonitorserver;

import java.util.List;

import org.oauth4j.core.OAuth4JService.Method;
import org.oauth4j.core.OAuth4JService.Protocol;
import org.oauth4j.core.OAuth4JServiceProviderService;

import com.rxp.transactionmonitorserver.model.Transaction;

public enum PaybinServiceProviderService implements OAuth4JServiceProviderService {

    /**
     * The Transactions resource lists transactions for today. 
     * GET http://payb.in/v1/transactions
     */
    TRANSACTIONS(Protocol.HTTP, Method.GET, "http://payb.in/v1/transactions", List.class),
    
    /**
     * selected transaction http://payb.in/v1/transaction/{guid}/{orderid}
     */
    TRANSACTION(Protocol.HTTP, Method.GET,
            "http://payb.in/v1/transaction/{0}/{1}", Transaction.class, 2),
    
    /**
     * Retrieve and encrypted blob of credentials.
     * http://payb.in/v1/credentials
     */
    CREDENTIALS(Protocol.HTTP, Method.GET, "http://payb.in/v1/credentials", String.class);
    
    private Protocol protocol;
    private Method method;
    private String url;
    private Class<?> entity;
    private int urlParams;

    PaybinServiceProviderService(final Protocol protocol, final Method method,
            final String url, final Class<?> entity) {
        this.protocol = protocol;
        this.method = method;
        this.url = url;
        this.entity = entity;
    }

    PaybinServiceProviderService(final Protocol protocol, final Method method,
            final String url, final Class<?> entity, int urlParams) {
        this.protocol = protocol;
        this.method = method;
        this.url = url;
        this.entity = entity;
        this.urlParams = urlParams;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public Method getMethod() {
        return method;
    }

    public String getURL() {
        return url;
    }

    public Class<?> getEntity() {
        return entity;
    }

    public int getURLParamCount() {
        return urlParams;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LinkedInServices [protocol=").append(protocol)
                .append(", method=").append(method).append(", url=")
                .append(url).append(", clazz=").append(entity).append("]");
        return builder.toString();
    }
}
