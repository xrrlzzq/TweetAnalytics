package controllers;

import static org.junit.Assert.assertTrue;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import actors.TwitterActor;
import actors.UserActor;
import actors.TwitterActor.FilterTwitter;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
/**
 * Provides test for all actor's messages using Akka TestKit
 * 
 * @version 1.0 
 * @author  Rong Xie
 */
public class ActorsTest {
    static ActorSystem system;
    static ActorRef testTwitterActor;
    static ActorRef testUserActor;
    static TestKit testProbe;

    /**
     * initialize the environment before run test
     */
    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    /**
     * clean up the environment after run test
     */
    
    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    /**
     * test TwitterActor and UserActor's message transmutation
     */
    
    @Test
    public void testActors() {
        testProbe = new TestKit(system);
        
        //set testTwitterActor named as "twitterActor", make actorSelection("/user/twitterActor") find this actor in UserActor's preStart(), 
        //otherwise "dead letters encountered" appear(because found TwitterActor actor which is not active when test)
        //see UserActor's twActor =context().actorSelection("/user/twitterActor")
        testTwitterActor = system.actorOf(TwitterActor.props(),"twitterActor");        
        //testProbe.getRef():this is test actor(created internally by TestKit), mock then actor created by play websocket internally(wsActor defined in UserActor)
        testUserActor = system.actorOf(UserActor.props(testProbe.getRef()));
    	
        //UserActor's preStart() will set RegisterMsg after FilterTwitter, so we call it first(RegisterMsg will be sent twice)
        testTwitterActor.tell(new TwitterActor.RegisterMsg(), testUserActor);   	
   	    testTwitterActor.tell(new FilterTwitter("@RongXie - Hello concordia university"), testTwitterActor);    	
    	    	
    	ObjectNode response = testProbe.expectMsgClass(ObjectNode.class);
        assertTrue(response.toString().contains("Hello concordia university"));
    }

}
