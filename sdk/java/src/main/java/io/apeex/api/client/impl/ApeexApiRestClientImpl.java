package io.apeex.api.client.impl;

import io.apeex.api.client.ApeexApiRestClient;
import io.apeex.api.client.constant.ApeexConstants;
import io.apeex.api.client.domain.account.*;
import io.apeex.api.client.domain.account.request.*;
import io.apeex.api.client.domain.general.BrokerInfo;
import io.apeex.api.client.domain.market.*;
import io.apeex.api.client.service.ApeexApiService;

import java.util.List;

import static io.apeex.api.client.impl.ApeexApiServiceGenerator.createService;
import static io.apeex.api.client.impl.ApeexApiServiceGenerator.executeSync;

/**
 * Implementation of Apeex's REST API using Retrofit with synchronous/blocking method calls.
 */
public class ApeexApiRestClientImpl implements ApeexApiRestClient {

    private final ApeexApiService aPeexApiService;

    public ApeexApiRestClientImpl(String baseUrl, String apiKey, String secret) {
        aPeexApiService = createService(baseUrl, ApeexApiService.class, apiKey, secret);
    }

    // General endpoints

    @Override
    public void ping() {
        executeSync(aPeexApiService.ping());
    }

    @Override
    public Long getServerTime() {
        return executeSync(aPeexApiService.getServerTime()).getServerTime();
    }

    @Override
    public BrokerInfo getBrokerInfo() {
        return executeSync(aPeexApiService.getBrokerInfo());
    }

    @Override
    public OrderBook getOrderBook(String symbol, Integer limit) {
        return executeSync(aPeexApiService.getOrderBook(symbol, limit));
    }

    @Override
    public List<TradeHistoryItem> getTrades(String symbol, Integer limit) {
        return executeSync(aPeexApiService.getTrades(symbol, limit));
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit) {
        return executeSync(aPeexApiService.getCandlestickBars(symbol, interval.getIntervalId(), startTime, endTime, limit));
    }

    @Override
    public TickerStatistics get24HrPriceStatistics(String symbol) {
        return executeSync(aPeexApiService.get24HrPriceStatistics(symbol));
    }

    @Override
    public TickerPrice getPrice(String symbol) {
        return executeSync(aPeexApiService.getLatestPrice(symbol));
    }

    @Override
    public BookTicker getBookTicker(String symbol) {
        return executeSync(aPeexApiService.getBookTicker(symbol));
    }

    @Override
    public Index getIndex(String symbol) {
        return executeSync(aPeexApiService.getIndex(symbol));
    }

    @Override
    public NewOrderResponse newOrder(NewOrder order) {
        return executeSync(aPeexApiService.newOrder(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(),
                order.getIcebergQty(), order.getRecvWindow(), order.getTimestamp()));
    }

    @Override
    public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
        return executeSync(aPeexApiService.getOrderStatus(orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
                orderStatusRequest.getRecvWindow(), orderStatusRequest.getTimestamp()));
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
        return executeSync(aPeexApiService.cancelOrder(cancelOrderRequest.getOrderId(), cancelOrderRequest.getClientOrderId(),
                cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()));
    }

    @Override
    public List<Order> getOpenOrders(OpenOrderRequest orderRequest) {
        return executeSync(aPeexApiService.getOpenOrders(orderRequest.getSymbol(), orderRequest.getLimit(),
                orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
    }

    @Override
    public List<Order> getHistoryOrders(HistoryOrderRequest orderRequest) {
        return executeSync(aPeexApiService.getHistroyOrders(orderRequest.getOrderId(), orderRequest.getStartTime(), orderRequest.getEndTime(),
                orderRequest.getLimit(), orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
    }

    @Override
    public Account getAccount(Long recvWindow, Long timestamp) {
        return executeSync(aPeexApiService.getAccount(recvWindow, timestamp));
    }

    @Override
    public List<Trade> getMyTrades(MyTradeRequest request) {
        return executeSync(aPeexApiService.getMyTrades(request.getFromId(), request.getToId(), request.getStartTime(), request.getEndTime(),
                request.getLimit(), request.getRecvWindow(), request.getTimestamp()));
    }

    @Override
    public List<DepositOrder> getDepositOrders(DepositOrderRequest request) {
        return executeSync(aPeexApiService.getDepositOrders(request.getToken(), request.getStartTime(), request.getEndTime(), request.getFromId(),
                request.getLimit(), request.getRecvWindow(), request.getTimestamp()));
    }

    @Override
    public String startUserDataStream(Long recvWindow, Long timestamp) {
        return executeSync(aPeexApiService.startUserDataStream(recvWindow, timestamp)).toString();
    }

    @Override
    public void keepAliveUserDataStream(String listenKey, Long recvWindow, Long timestamp) {
        executeSync(aPeexApiService.keepAliveUserDataStream(listenKey, recvWindow, timestamp));
    }

    @Override
    public void closeUserDataStream(String listenKey, Long recvWindow, Long timestamp) {
        executeSync(aPeexApiService.closeAliveUserDataStream(listenKey, recvWindow, timestamp));
    }

}
