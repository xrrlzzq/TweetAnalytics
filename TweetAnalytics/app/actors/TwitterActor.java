package actors;


import java.util.HashSet;



import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import play.mvc.Controller;
/**
 * The actor which get the continuously twitter informations form TwitterActionController and pass these information to UserActor
 * 
 * @version 1.0
 * @author Rong Xie
 *
 */
public class TwitterActor extends AbstractActorWithTimers {
private HashSet<ActorRef> userActors=new HashSet<ActorRef>();

private static TwitterStream twitterStream;	
	/**
	 * This method create a TwitterActor 
	 * @return TwitterActor
	 */
    public static Props props(){
		return Props.create(TwitterActor.class, ()->new TwitterActor());
	}
	/**
	 * The action to get twitterStream's instance of TwitterActor  
	 * @return twitterStream's instance 
	 */
	public static TwitterStream getTwitterStream(){
		return twitterStream;
	}
	/**
	 * The class which get the twitter informations from TwitterActionController
	 * 
	 * @version 1.0
	 * @author Rong Xie
	 *
	 */
	static public class FilterTwitter{
	     private static String twitterInfo;
	     public FilterTwitter(String info){
	    	 this.twitterInfo=info;
	     }
	}
	/**
	 * The action which tell the message(twitter informations) to each UserActor
	 * @param ft
	 *        FilterTwitter ft
	 */
	public void notifyClients(FilterTwitter ft){
		UserActor.TwitterMessage tMsg = new UserActor.TwitterMessage(ft.twitterInfo);
		userActors.forEach(ar -> ar.tell(tMsg, self()));
	}
	/**
	 * The action is executed before creating the TwitterActor, which initializes the twitter's 
	 * listener(including how to do when there is coming indicated twitter informations) and the 
	 * security key of twitter api
	 *  
	 */
	 @Override
	public void preStart(){
		
		
		StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                //System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
               String info= String.format("<a href=/profile?screenName=%s>@%s</a> - %s<br>\r\n",
                		status.getUser().getScreenName(),status.getUser().getScreenName(),status.getText());
                self().tell(new FilterTwitter(info),self());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                self().tell(new FilterTwitter("Got a status deletion notice id:" + statusDeletionNotice.getStatusId()),self());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                
                self().tell(new FilterTwitter("Got track limitation notice:" + numberOfLimitedStatuses),self());
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                
                self().tell(new FilterTwitter("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId),self());
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                
                self().tell(new FilterTwitter("Got stall warning:" + warning),self());
            }

            @Override
            public void onException(Exception ex) {
                
                self().tell(new FilterTwitter("error:" + ex.getMessage()),self());
            }
        };
        ConfigurationBuilder cb = new ConfigurationBuilder();  
        cb.setDebugEnabled(true)  
          .setOAuthConsumerKey("cP2z03lDFfiFNhdqtwfEVDKpt")  
          .setOAuthConsumerSecret("66mEoiAoHBkaOe4wExEDo3Or8Z7y4tTNmolmQ4OTge01vEfxFV")  
          .setOAuthAccessToken("968293983964336128-637EPJ9gS66mMWOV0GYjakPn70RMGPN")  
          .setOAuthAccessTokenSecret("sa1s37RS44RdKDXuT0tv85mQ1VFWfSYGkf1ahBV0tEdzy");  
        cb.setJSONStoreEnabled(true);   
        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());  
        twitterStream = tf.getInstance(); 
        
        twitterStream.addListener(listener);
	}
	/**
	 * The void class which is used for informing TwitterActor to add new UserActor to HashSet
	 * 
	 */
	 static public class RegisterMsg{
	
		
	}
	
	/**
	 * The method which define when TwitterActor should tell message to UserActor and when TwitterActor should add new UserActor to HashSet 
	 * @return Receive
	 */
	 
	 @Override
	  public Receive createReceive(){
	    	return  receiveBuilder().match(RegisterMsg.class, msg -> userActors.add(sender())).
	    			match(FilterTwitter.class, this::notifyClients).build();
	    }
	
}
