package io.apeex.api.test;

import io.apeex.api.client.BHexApiClientFactory;
import io.apeex.api.client.BHexApiRestClient;
import io.apeex.api.client.domain.general.BrokerInfo;
import io.apeex.api.test.constant.Constants;

public class GeneralRestApiTest {

    public static void main(String[] args) {

        BHexApiClientFactory factory = BHexApiClientFactory.newInstance();
        BHexApiRestClient client = factory.newRestClient();

        System.out.println("\n ------BrokerInfo-----");
        BrokerInfo brokerInfo = client.getBrokerInfo();
        System.out.println(brokerInfo);

    }


}
