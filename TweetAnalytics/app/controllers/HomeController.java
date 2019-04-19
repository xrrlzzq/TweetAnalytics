package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import actors.TwitterActor;
import actors.UserActor;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.streams.ActorFlow;
import play.mvc.*;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * 
 * @version 1.0 
 * @author  Rong Xie
 */
public class HomeController extends Controller {
	/**
	 * render index html page
	 */	
	@Inject HttpExecutionContext ec;
	
	/**
	 *  the constructor of HomeCtroller, which call props() method to create a TwitterActor
	 * @param system
	 *        ActorSystem from dependency injection
	 */
	@Inject 
    public HomeController(ActorSystem system) {
	    	system.actorOf(TwitterActor.props(), "twitterActor");
	}
	/**
     * An action that renders an HTML page with a welcome message.
     * @return html page
     */
    public CompletionStage<Result> index() {
	    	return CompletableFuture.supplyAsync(()->ok(views.html.index.render(request())) , ec.current());
	    }
	@Inject private ActorSystem actorSystem;
	@Inject private Materializer materializer;
	/**
	 * An action that receive webSocket connection,create the client actor and pass the client actor to UserActor by calling
	 * UserActor.props()
	 * @return WebSocket
	 */
	public WebSocket ws() {
	        return WebSocket.Json.accept(request -> ActorFlow.actorRef(UserActor::props, actorSystem, materializer));
	    }
	
}
