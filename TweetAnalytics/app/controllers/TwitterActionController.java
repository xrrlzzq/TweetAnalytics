package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import twitter4j.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import actors.TwitterActor;
import play.libs.concurrent.HttpExecutionContext;

/**
 * This controller contains actions to search Twitter key word
 * and user profile information.
 * 
 * @version 1.0 
 * @author  Rong Xie
 */

public class TwitterActionController extends Controller {
	private static StringBuffer searchBuf = new StringBuffer();
	private HttpExecutionContext httpExecutionContext;
	private static HashSet<String> track=new HashSet<String>();
	/**
	 * Constructor of Controller to accept HttpExecutionContext
	 * 
	 * @param  ec
	 *         HttpExecutionContext from caller
	 */	
	@Inject
    public TwitterActionController(HttpExecutionContext ec) {
        this.httpExecutionContext = ec;
    }
    
	/**
	 * This method get the instance of TwitterActor's TwitterStream and 
	 * use this TwitterStream instance to filter the tweets by keyword
	 * @return "ok" string
	 */
	public CompletionStage<String> doTwitterFiler(){
		  String[] trackArray = track.toArray(new String[0]);	    	  
		  TwitterStream twitterStream=TwitterActor.getTwitterStream();//get twitterStream created in TwitterActor
	  	  
		  //filter() method internally creates a thread which manipulates TwitterStream and calls adequate listener methods continuously.
	  	  twitterStream.filter(new FilterQuery(trackArray)); 
	        	  
		  return CompletableFuture.completedFuture("ok");
		}

	/**
	 * Controller action routed in routes file, used to search twitter
	 * key word, executed asynchronously.
	 * 
     * @return html page which contains search datas
	 */		
	public CompletionStage<Result> search() {
		return doSearch().thenApplyAsync(content -> {return ok(views.html.action.render(request(),content));}, httpExecutionContext.current());
	}

	/**
	 * search twitter key word by call twitter API warped using twitter4j
	 * 
     * @return key word records fetched from twitter
	 */		
    public CompletionStage<String> doSearch() {
    	String keyValue;
    	try{
  		    keyValue =request().getQueryString("key"); // <input type="text" name="key">
    	}
    	catch(Exception e){
    		keyValue = "concordia university"; //for test purpose: Mockito does not support static Mock    		
    	}
        
        if (keyValue.equals(""))			
          return CompletableFuture.completedFuture("<p>Key word can not be empty!");

        String content="";
    	Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query(keyValue);
            query.setCount(10);//set fetch count each time   
            QueryResult qr;
            content=String.format("<p>keyword list for [%s]:<br><br>\r\n",keyValue);
            qr = twitter.search(query);
            List<Status> tweets = qr.getTweets();
            for (Status tweet : tweets) {
               	content=content+String.format("<a href=/profile?screenName=%s>@%s</a> - %s<br>\r\n",tweet.getUser().getScreenName(),tweet.getUser().getScreenName(),tweet.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }     	
        
       // content = String.format("<p>keyword list for [%s]:<br><br>\r\n",keyValue);
       // content=content+String.format("<a href=/profiler?screenName=%s>@%s</a> - How are you concordia<br>\r\n","BillGates","BillGates");        
       // content=content+String.format("<a href=/profiler?screenName=%s>@%s</a> - How are you concordia<br>\r\n","XR","XR");
 
        searchBuf.append(content);
        track.add(keyValue);
        CompletableFuture.supplyAsync(()->doTwitterFiler());

        return CompletableFuture.completedFuture(searchBuf.toString());
    }
    
	/**
	 * Controller action routed in routes file, used to get twitter user profile
	 * information, executed asynchronously.
	 * 
     * @return html page which contains user profile datas
	 */		
    
    public CompletionStage<Result> profile(String screenName) {
    	
    	return getUserProfile(screenName).thenApplyAsync(content -> {return ok(views.html.action.render(request(),content));}, httpExecutionContext.current());
    }
    
	/**
	 * search twitter user profile information by call twitter API warped using twitter4j
	 * 
     * @return user profile records fetched from twitter
	 */		    
    public CompletionStage<String> getUserProfile(String screenName) {
    	String content="";
    	// gets Twitter instance with default credentials
        
    	if(screenName.equals("Return_To_Search_List"))
      	  return CompletableFuture.completedFuture(searchBuf.toString());
      	
      	content= "<p><a href=/profile?screenName=Return_To_Search_List>Return To Search List</a><br>\r\n";
      	Twitter twitter = new TwitterFactory().getInstance();
        try {
        	content=content+String.format("<p>profile for user [%s]:<br><br>\r\n",screenName);
        	//user profiler
        	User user=twitter.showUser(screenName);
        	
        	
            content=content+String.format("Name=%s<br>\r\n", user.getName());
            content=content+String.format("Email=%s<br>\r\n", user.getEmail());
            content=content+String.format("Location=%s<br>\r\n", user.getLocation());
            content=content+String.format("FollowersCount=%s<br>\r\n", user.getFollowersCount());
            content=content+String.format("FavouritesCount=%s<br>\r\n",  user.getFavouritesCount());
            content=content+String.format("Created Date=%s<br>\r\n", user.getCreatedAt());
            content=content+String.format("Description=%s<br>\r\n",user.getDescription());
            
            content=content+String.format("<p>recent tweets of user [%s]:<br><br>\r\n",screenName);
            //user tweet list
            List<Status> tweets;
            tweets = twitter.getUserTimeline(screenName); //current user: screenName = twitter.verifyCredentials().getScreenName();

            for (Status tweet : tweets) {
                content=content+String.format("@%s - %s<br>\r\n",tweet.getUser().getScreenName(),tweet.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
        }   
        
     //   content= "<p>hello world "+screenName;
        
        return CompletableFuture.completedFuture(content);
    }    
}
