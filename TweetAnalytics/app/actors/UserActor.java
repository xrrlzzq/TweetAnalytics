package actors;

import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import play.libs.Json;
/**
 * The actor which get the message from TwitterActor and tell the message to the client actor
 * 
 * @version 1.0
 * @author Rong Xie
 *
 */
public final class UserActor extends AbstractActor {
    private final ActorRef ws;
    /**
     * The constructor of UserActor which receive the client actor
     * @param wsOut
     *        ActorRef wsout
     */
    public UserActor(final ActorRef wsOut) {
    	ws =  wsOut;
    }
    /**
     * The action which create a UserActor 
     *  @param wsout
     *        ActorRef wsout
     * 
     * @return UserActor
     */
    public static Props props(final ActorRef wsout) {
        return Props.create(UserActor.class, wsout);
    }
    /**
     * The action is executed before creating UserActor, which informs TwitterActor to add new UserActors to TwitterActor's HashSet 
     */
    @Override
    public void preStart() {
       	context().actorSelection("/user/twitterActor/")
                 .tell(new TwitterActor.RegisterMsg(), self());
    }
    /**
     * The class which get the twitter informations from TwitterActor
     * 
     * @version 1.0
     * @author Rong Xie
     *
     */
    static public class TwitterMessage{
    	public final String info;
    	
    	public  TwitterMessage(String info){
    		this.info=info;
    	}
    }
    /**
     * The action which convert the twitter informations to Json type and send it to client actor
     * @param msg
     *        TwitterMessage msg
     */
    private void sendTwitter(TwitterMessage msg) {
        final ObjectNode response = Json.newObject();
        response.put("twitter", msg.info);
        ws.tell(response, self());
    }
    /**
     * The method which define when to send the message 
     * @return Receive
     */
    @Override
    public Receive createReceive(){
    	return  receiveBuilder().match(TwitterMessage.class, this::sendTwitter).build();
    }
}
