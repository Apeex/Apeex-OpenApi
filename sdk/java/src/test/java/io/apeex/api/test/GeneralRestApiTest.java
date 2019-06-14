package io.apeex.api.test;

import io.apeex.api.client.ApeexApiClientFactory;
import io.apeex.api.client.ApeexApiRestClient;
import io.apeex.api.client.domain.general.BrokerInfo;
import io.apeex.api.test.constant.Constants;

public class GeneralRestApiTest {

    public static void main(String[] args) {

        ApeexApiClientFactory factory = ApeexApiClientFactory.newInstance();
        ApeexApiRestClient client = factory.newRestClient();

        System.out.println("\n ------BrokerInfo-----");
        BrokerInfo brokerInfo = client.getBrokerInfo();
        System.out.println(brokerInfo);

    }


}
