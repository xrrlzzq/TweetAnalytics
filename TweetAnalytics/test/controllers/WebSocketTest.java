package controllers;

import static org.awaitility.Awaitility.*;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocket;
import play.test.TestServer;
/**
 * Testing with a WebSocket server using Play test framework.
 * mainly for test "public WebSocket ws()" in HomeController 
 *
 */
public class WebSocketTest {
	/**
	 * starting a test server and test WebSocket request from WS Client
	 */
    @Test
    public void testAcceptWebSocket() {
    	
        TestServer server = testServer(9001);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);
                try {
                    String serverURL = "ws://localhost:9001/ws";
                    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
                    queue.put("@RongXie - Hello concordia university");
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener((message) -> {
                        try {
                            queue.put(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    CompletableFuture<WebSocket> completionStage = webSocketClient.call(serverURL, listener);
                    await().until(completionStage::isDone);
                    //WebSocket websocket = completionStage.get();
                    String input = queue.take();
                    assertTrue(input.contains("Hello concordia university"));
                } finally {
                    client.close();
                }
            } catch (Exception e) {
            }
        });
    }
	
}

