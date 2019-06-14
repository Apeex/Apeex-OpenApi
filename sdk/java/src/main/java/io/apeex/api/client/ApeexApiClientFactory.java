package io.apeex.api.client;

import io.apeex.api.client.constant.ApeexConstants;
import io.apeex.api.client.impl.ApeexApiRestClientImpl;
import io.apeex.api.client.impl.ApeexApiWebSocketClientImpl;
import io.apeex.api.client.impl.ApeexOptionApiRestClientImpl;

import static io.apeex.api.client.impl.ApeexApiServiceGenerator.getSharedClient;

/**
 * A factory for creating ApeexApi client objects.
 */
public final class ApeexApiClientFactory {

    /**
     * API Key
     */
    private String apiKey;

    /**
     * Secret.
     */
    private String secret;

    private String baseUrl = ApeexConstants.API_BASE_URL;

    /**
     * Instantiates a new Apeex api client factory.
     *
     * @param apiKey the API key
     * @param secret the Secret
     */
    private ApeexApiClientFactory(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    private ApeexApiClientFactory(String baseUrl, String apiKey, String secret) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
    }

    /**
     * New instance.
     *
     * @param apiKey the API key
     * @param secret the Secret
     * @return the Apeex api client factory
     */
    public static ApeexApiClientFactory newInstance(String apiKey, String secret) {
        return new ApeexApiClientFactory(apiKey, secret);
    }

    /**
     * for bhop.cloud client and inner test only
     *
     * @param baseUrl
     * @param apiKey
     * @param secret
     * @return
     */
    public static ApeexApiClientFactory newInstance(String baseUrl, String apiKey, String secret) {
        return new ApeexApiClientFactory(baseUrl, apiKey, secret);
    }

    /**
     * New instance without authentication.
     *
     * @return the Apeex api client factory
     */
    public static ApeexApiClientFactory newInstance() {
        return new ApeexApiClientFactory(null, null);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public ApeexApiRestClient newRestClient() {
        return new ApeexApiRestClientImpl(baseUrl, apiKey, secret);
    }


    public ApeexApiWebSocketClient newWebSocketClient() {
        return new ApeexApiWebSocketClientImpl(getSharedClient(), ApeexConstants.WS_API_BASE_URL, ApeexConstants.WS_API_USER_URL);
    }

    /**
     * for bhop.cloud client and inner test only
     *
     * @param wsApiBaseUrl
     * @param wsApiUserUrl
     * @return
     */
    public ApeexApiWebSocketClient newWebSocketClient(String wsApiBaseUrl, String wsApiUserUrl) {
        return new ApeexApiWebSocketClientImpl(getSharedClient(), wsApiBaseUrl, wsApiUserUrl);
    }

    /**
     * Creates a new synchronous/blocking Option REST client.
     */
    public ApeexOptionApiRestClient newOptionRestClient() {
        return new ApeexOptionApiRestClientImpl(baseUrl, apiKey, secret);
    }

}
