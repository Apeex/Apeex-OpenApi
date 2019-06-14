package io.apeex.api.client.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apeex.api.client.ApeexApiError;
import io.apeex.api.client.constant.ApeexConstants;
import io.apeex.api.client.exception.ApeexApiException;
import io.apeex.api.client.security.AuthenticationInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

/**
 * Generates a Apeex API implementation based on @see {@link io.apeex.api.client.service.ApeexApiService}.
 */
public class ApeexApiServiceGenerator {
    private static final OkHttpClient sharedClient = new OkHttpClient.Builder()
            .pingInterval(20, TimeUnit.SECONDS)
            .build();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final Converter.Factory converterFactory = JacksonConverterFactory.create(OBJECT_MAPPER);

    @SuppressWarnings("unchecked")
    private static final Converter<ResponseBody, ApeexApiError> errorBodyConverter =
            (Converter<ResponseBody, ApeexApiError>) converterFactory.responseBodyConverter(
                    ApeexApiError.class, new Annotation[0], null);

    public static <S> S createService(Class<S> serviceClass) {
        return createService(ApeexConstants.API_BASE_URL, serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String apiKey, String secret) {
        return createService(ApeexConstants.API_BASE_URL, serviceClass, apiKey, secret);
    }

    /**
     *
     * @param baseUrl
     * @param serviceClass
     * @param apiKey
     * @param secret
     * @param <S>
     * @return
     */
    public static <S> S createService(String baseUrl, Class<S> serviceClass, String apiKey, String secret) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory);

        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(secret)) {
            retrofitBuilder.client(sharedClient);
        } else {
            // `adaptedClient` will use its own interceptor, but share thread pool etc with the 'parent' client
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(apiKey, secret);
            OkHttpClient adaptedClient = sharedClient.newBuilder().addInterceptor(interceptor).build();
            retrofitBuilder.client(adaptedClient);
        }

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                ApeexApiError apiError = getApeexApiError(response);
                throw new ApeexApiException(apiError);
            }
        } catch (IOException e) {
            throw new ApeexApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public static ApeexApiError getApeexApiError(Response<?> response) throws IOException, ApeexApiException {
        return errorBodyConverter.convert(response.errorBody());
    }

    /**
     * Returns the shared OkHttpClient instance.
     */
    public static OkHttpClient getSharedClient() {
        return sharedClient;
    }

}
