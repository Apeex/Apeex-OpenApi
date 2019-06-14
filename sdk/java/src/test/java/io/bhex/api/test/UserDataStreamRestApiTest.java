package io.apeex.api.test;

import io.apeex.api.client.ApeexApiClientFactory;
import io.apeex.api.client.ApeexApiRestClient;
import io.apeex.api.client.constant.ApeexConstants;
import io.apeex.api.test.constant.Constants;

public class UserDataStreamRestApiTest {

    public static void main(String[] args) {

        ApeexApiClientFactory factory = ApeexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        ApeexApiRestClient client = factory.newRestClient();

        System.out.println("\n ------start user data stream-----");
        String listenKey = client.startUserDataStream(ApeexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        System.out.println(listenKey);

        System.out.println("\n ------keepAlive user data stream-----");
        client.keepAliveUserDataStream(listenKey, ApeexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());

        System.out.println("\n ------close user data stream-----");
        client.closeUserDataStream(listenKey, ApeexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());

    }

}
