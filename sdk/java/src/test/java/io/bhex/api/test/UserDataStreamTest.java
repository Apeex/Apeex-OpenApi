package io.apeex.api.test;

import io.apeex.api.client.ApeexApiClientFactory;
import io.apeex.api.client.ApeexApiRestClient;
import io.apeex.api.client.ApeexApiWebSocketClient;
import io.apeex.api.client.constant.ApeexConstants;
import io.apeex.api.test.constant.Constants;

//@Slf4j
public class UserDataStreamTest {

    public static void main(String[] args) {
//
        ApeexApiWebSocketClient client = ApeexApiClientFactory.newInstance().newWebSocketClient();
        ApeexApiRestClient restClient = ApeexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY).newRestClient();

        System.out.println("\n ------Get Listen Key -----");
        System.out.println();
        String listenKey = restClient.startUserDataStream(ApeexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        System.out.println("listenKey:" + listenKey);
        // order
        client.onUserEvent(listenKey, response -> System.out.println(response), true);

    }
}
