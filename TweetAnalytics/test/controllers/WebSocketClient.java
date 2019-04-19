package controllers;

import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.BoundRequestBuilder;
import play.shaded.ahc.org.asynchttpclient.ListenableFuture;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocket;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocketTextListener;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocketUpgradeHandler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

/**
 * A quick wrapper around AsyncHttpClient WebSocket.
 * for mock ws://localhost:9000/ws WebSocket request when test
 *
 */
public class WebSocketClient {

    private AsyncHttpClient client;

    /**
     * constructor, keep AsyncHttpClient
     * 
     */
    public WebSocketClient(AsyncHttpClient c) {
        this.client = c;
    }

    /**
     * Mock WebSocket client request to server for test  
     */
    public CompletableFuture<WebSocket> call(String url, WebSocketTextListener listener) throws ExecutionException, InterruptedException {
        final BoundRequestBuilder requestBuilder = client.prepareGet(url);

        final WebSocketUpgradeHandler handler = new WebSocketUpgradeHandler.Builder().addWebSocketListener(listener).build();
        final ListenableFuture<WebSocket> future = requestBuilder.execute(handler);
        return future.toCompletableFuture();
    }

    /**
     * Mock WebSocket client listener to receive message sent from server
     *  
     */
    static class LoggingListener implements WebSocketTextListener {
        private final Consumer<String> onMessageCallback;

        public LoggingListener(Consumer<String> onMessageCallback) {
            this.onMessageCallback = onMessageCallback;
        }

        private Throwable throwableFound = null;

        public Throwable getThrowable() {
            return throwableFound;
        }

        @Override
        public void onOpen(WebSocket websocket) {
            websocket.sendMessage("WebSocket Connected, listening ...");
        }

        @Override
        public void onClose(WebSocket websocket) {
        	websocket.sendMessage("WebSocket Disconnected");
        }

        @Override
        public void onError(Throwable t) {
            throwableFound = t;
        }

        @Override
        public void onMessage(String s) {
            onMessageCallback.accept(s);
        }
    }

}
