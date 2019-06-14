package io.apeex.api.client.impl;

import java.util.List;

import io.apeex.api.client.ApeexOptionApiRestClient;
import io.apeex.api.client.constant.ApeexConstants;
import io.apeex.api.client.domain.account.request.CancelOrderRequest;
import io.apeex.api.client.domain.option.OptionMatchResult;
import io.apeex.api.client.domain.option.OptionOrderResult;
import io.apeex.api.client.domain.option.PositionResult;
import io.apeex.api.client.domain.option.SettlementResult;
import io.apeex.api.client.domain.option.TokenOptionResult;
import io.apeex.api.client.domain.option.request.OptionHistoryOrderRequest;
import io.apeex.api.client.domain.option.request.OptionOpenOrderRequest;
import io.apeex.api.client.domain.option.request.OptionOrderRequest;
import io.apeex.api.client.domain.option.request.OptionPositionRequest;
import io.apeex.api.client.domain.option.request.OptionSettlementRequest;
import io.apeex.api.client.domain.option.request.OptionTradeRequest;
import io.apeex.api.client.domain.option.request.OptionsRequest;
import io.apeex.api.client.service.ApeexOptionApiService;

import static io.apeex.api.client.impl.ApeexApiServiceGenerator.createService;
import static io.apeex.api.client.impl.ApeexApiServiceGenerator.executeSync;

/**
 * Implementation of Apeex's Option REST API using Retrofit with synchronous/blocking method calls.
 */
public class ApeexOptionApiRestClientImpl implements ApeexOptionApiRestClient {

    private final ApeexOptionApiService aPeexOptionApiService;

    public ApeexOptionApiRestClientImpl(String baseUrl, String apiKey, String secret) {
        aPeexOptionApiService = createService(baseUrl, ApeexOptionApiService.class, apiKey, secret);
    }

    @Override
    public List<TokenOptionResult> getOptions(OptionsRequest request) {
        return executeSync(aPeexOptionApiService.getOptions(request.getExpired()));
    }

    @Override
    public OptionOrderResult newOptionOrder(OptionOrderRequest request) {
        return executeSync(aPeexOptionApiService.newOptionOrder(
                request.getSymbol(),
                request.getOrderSide() == null ? "" : request.getOrderSide().name(),
                request.getOrderType() == null ? "" : request.getOrderType().name(),
                request.getTimeInForce().name(),
                request.getQuantity(),
                request.getPrice(),
                request.getClientOrderId(),
                request.getRecvWindow(),
                request.getTimestamp()
        ));
    }

    @Override
    public OptionOrderResult cancelOptionOrder(CancelOrderRequest cancelOrderRequest) {
        return executeSync(aPeexOptionApiService.cancelOptionOrder(
                cancelOrderRequest.getOrderId(),
                cancelOrderRequest.getClientOrderId(),
                cancelOrderRequest.getRecvWindow(),
                cancelOrderRequest.getTimestamp()
        ));
    }

    @Override
    public List<OptionOrderResult> getOptionOpenOrders(OptionOpenOrderRequest orderRequest) {
        return executeSync(aPeexOptionApiService.getOptionOpenOrders(
                orderRequest.getSymbol(),
                orderRequest.getOrderId(),
                orderRequest.getLimit(),
                orderRequest.getOrderSide() == null ? "" : orderRequest.getOrderSide().name(),
                orderRequest.getOrderType() == null ? "" : orderRequest.getOrderType().name(),
                orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()
        ));
    }

    @Override
    public List<OptionOrderResult> getOptionHistoryOrders(OptionHistoryOrderRequest orderRequest) {
        return executeSync(aPeexOptionApiService.getOptionHistoryOrders(
                orderRequest.getSymbol(),
                orderRequest.getOrderSide() == null ? "" : orderRequest.getOrderSide().name(),
                orderRequest.getOrderType() == null ? "" : orderRequest.getOrderType().name(),
                orderRequest.getLimit(),
                orderRequest.getOrderStatus() == null ? "" : orderRequest.getOrderStatus().name(),
                orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()
        ));
    }

    @Override
    public List<OptionMatchResult> getOptionMyTrades(OptionTradeRequest request) {
        return executeSync(aPeexOptionApiService.getOptionMyTrades(
                request.getSymbol(),
                request.getFromId(),
                request.getToId(),
                request.getLimit(),
                request.getOrderSide() == null ? "" : request.getOrderSide().name(),
                request.getRecvWindow(),
                request.getTimestamp()
        ));
    }

    @Override
    public List<PositionResult> getOptionPositions(OptionPositionRequest request) {
        return executeSync(aPeexOptionApiService.getOptionPositions(
                request.getSymbol(),
                request.getRecvWindow(),
                request.getTimestamp()
        ));
    }

    @Override
    public List<SettlementResult> getOptionSettlements(OptionSettlementRequest request) {
        return executeSync(aPeexOptionApiService.getOptionSettlements(
                request.getSymbol(),
                request.getRecvWindow(),
                request.getTimestamp()
        ));
    }
}
