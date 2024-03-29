package io.apeex.api.client;


import io.apeex.api.client.domain.account.SocketUserResponse;
import io.apeex.api.client.domain.event.CandlestickEvent;
import io.apeex.api.client.domain.event.DepthEvent;
import io.apeex.api.client.domain.event.IndexEvent;
import io.apeex.api.client.domain.event.TickerEvent;
import io.apeex.api.client.domain.event.TradeEvent;
import io.apeex.api.client.domain.market.CandlestickInterval;

import java.io.Closeable;

/**
 * Apeex API data streaming façade, supporting streaming of events through web sockets.
 */
public interface ApeexApiWebSocketClient extends Closeable {

    /**
     * Open a new web socket to receive {@link DepthEvent depthEvents} on a callback.
     *
     * @param symbols   SYMBOL must be form with exchangeId.symbol like 'BTCUSDT'
     * @param callback  the callback to call on new events
     * @param autoRetry setting the choose to auto retry connect socket
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onDepthEvent(String symbols, ApeexApiCallback<DepthEvent> callback, boolean autoRetry);

    Closeable onDepthEvent(String symbols, ApeexApiCallback<DepthEvent> callback);

    /**
     * Open a new web socket to receive {@link CandlestickEvent candlestickEvents} on a callback.
     *
     * @param symbols   SYMBOL must be form with exchangeId.symbol like 'BTCUSDT'
     * @param interval  the interval of the candles tick events required
     * @param callback  the callback to call on new events
     * @param autoRetry setting the choose to auto retry connect socket
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */

    Closeable onCandlestickEvent(String symbols, CandlestickInterval interval, ApeexApiCallback<CandlestickEvent> callback, boolean autoRetry);

    Closeable onCandlestickEvent(String symbols, CandlestickInterval interval, ApeexApiCallback<CandlestickEvent> callback);


    /**
     * Open a new web socket to receive {@link TradeEvent} on a callback
     *
     * @param symbols   SYMBOL must be form with exchangeId.symbol like 'BTCUSDT'
     * @param callback  the callback to call on new events
     * @param autoRetry setting the choose to auto retry connect socket
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onTradeEvent(String symbols, ApeexApiCallback<TradeEvent> callback, boolean autoRetry);

    Closeable onTradeEvent(String symbols, ApeexApiCallback<TradeEvent> callback);

    /**
     * Open a new web socket to receive {@link TickerEvent} on a callback
     *
     * @param symbols   SYMBOL must be form with exchangeId.symbol like 'BTCUSDT'
     * @param callback  the callback to call on new events
     * @param autoRetry setting the choose to auto retry connect socket
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onTicker24HourEvent(String symbols, ApeexApiCallback<TickerEvent> callback, boolean autoRetry);

    Closeable onTicker24HourEvent(String symbols, ApeexApiCallback<TickerEvent> callback);

    /**
     * Open a new web socket to receive {@link IndexEvent} on a callback
     *
     * @param symbols   SYMBOL must be form with symbol like 'BTCUSDT'
     * @param callback  the callback to call on new events
     * @param autoRetry setting the choose to auto retry connect socket
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onIndexEvent(String symbols, ApeexApiCallback<IndexEvent> callback, boolean autoRetry);

    Closeable onIndexEvent(String symbols, ApeexApiCallback<IndexEvent> callback);

    /**
     * Open a new web socket for account infomations on a callback
     *
     * @param listenKey user stream start key
     * @param callback  the callback to call on new events
     * @param autoRetry setting the choose to auto retry connect socket
     * @return
     */
    Closeable onUserEvent(String listenKey, ApeexApiCallback<SocketUserResponse> callback, boolean autoRetry);

    Closeable onUserEvent(String listenKey, ApeexApiCallback<SocketUserResponse> callback);
}
