package controllers;


import static org.junit.Assert.assertTrue;
import static play.test.Helpers.contentAsString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletionStage;

import org.junit.Test;

import akka.dispatch.forkjoin.ForkJoinPool;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;

import play.twirl.api.Content;
import twitter4j.*;
import static org.mockito.Mockito.*;
/**
 * the unit test which is used for testing Template,search method,get profile method
 * 
 *
 */

public class UnitTest {
    /**
     * the test for template
     */
	@Test
    public void checkTemplate() {
    	play.mvc.Http.Request request = mock(play.mvc.Http.Request.class);
        Content html = views.html.index.render(request);
        assertTrue(contentAsString(html).contains("TweetAnalytics"));
    }
    /**
     * the test for search method
     * @throws TwitterException
     */
    @Test
    public void testSearch() throws TwitterException {    	
    	play.mvc.Controller pc = mock(play.mvc.Controller.class);
        play.mvc.Http.Request request = mock(play.mvc.Http.Request.class);//String keyValue =request().getQueryString("key"); 
      //  when(play.mvc.Controller.request()).thenReturn(request);
      //  when(play.mvc.Controller.request().getQueryString("key")).thenReturn("concordia university");//key ="concordia university"

    	
    	QueryResult qr = mock(QueryResult.class);
    	Status tweet = mock(Status.class);    	

    	List<Status> tweets = new ArrayList<Status>();
    	tweets.add(tweet);
    	
        when(qr.getTweets()).thenReturn(tweets);
        
        User usr = mock(User.class);
     	when(tweet.getUser()).thenReturn(usr);//avoid tweet.getUser() return null 
        when(tweet.getUser().getScreenName()).thenReturn("Rong Xie");
        
        when(tweet.getText()).thenReturn("hello concordia university");

        
        HttpExecutionContext ec = new HttpExecutionContext(ForkJoinPool.commonPool());        
        TwitterActionController tc = new TwitterActionController(ec); 
         
        CompletionStage<Result> result = tc.search();
        
        Content html = views.html.action.render(request,result.toString());
        assertTrue(contentAsString(html).contains("TweetAnalytics"));//concordia university java.util.concurrent.CompletableFuture@53a0331a[Not completed]
    }
    /**
     * the test for get profile method
     * @throws TwitterException
     */

    @Test
    public void testProfiler() throws TwitterException {
    	Twitter twitter = mock(Twitter.class);
    	User user = mock(User.class);
    	Status tweet = mock(Status.class);    	

    	List<Status> tweets = new ArrayList<Status>();
    	tweets.add(tweet);
    	    	
    	when(twitter.showUser(anyString())).thenReturn(user);
    	
    	when(user.getName()).thenReturn("BillGates");
        when(user.getEmail()).thenReturn("test@gmail.com");
        when(user.getLocation()).thenReturn("Canada");
        when(user.getFollowersCount()).thenReturn(100);
        when(user.getFavouritesCount()).thenReturn(200);
        when(user.getCreatedAt()).thenReturn(new Date());
        when(user.getDescription()).thenReturn("student of concordia university");
        
        
        User usr = mock(User.class);
     	when(tweet.getUser()).thenReturn(usr);//avoid tweet.getUser() return null 
        when(tweet.getUser().getScreenName()).thenReturn("BillGates");
        
        //when(twitter.getUserTimeline("Rong Xie")).thenReturn((ResponseList<Status>) tweets);
        when(tweet.getText()).thenReturn("hello concordia university");

        HttpExecutionContext ec = new HttpExecutionContext(ForkJoinPool.commonPool());        
        TwitterActionController tc = new TwitterActionController(ec); 
         
        CompletionStage<Result> result = tc.profile("BillGates");
        play.mvc.Http.Request request = mock(play.mvc.Http.Request.class);
        Content html = views.html.action.render(request,result.toString());
        assertTrue(html.contentType().contains("html"));    	
    }
}
