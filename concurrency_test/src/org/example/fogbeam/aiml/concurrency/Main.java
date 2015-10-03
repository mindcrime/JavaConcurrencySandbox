package org.example.fogbeam.aiml.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* The point of this is just to be a sandbox to play with some concurrency
 * approaches, to figure out how we want to implement concurrent / asynchronous
 * processing in the main executor for the AI bot.  It'll probably basically
 * be some kind of producer/consumer thing, but what's interesting is that
 * we could submit a message to multiple subsystems, and one could return a 
 * "quick but wrong" answer, while a slower system may return a better answer
 * after we've already replied to the user.  So we need to work out how to
 * handle going back and saying "wait a minute, I was wrong about 'x'" and
 * send a better answer.
 */
public class Main 
{

	public static void main(String[] args) 
	{
		
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		
		ProducerRunnable producer = new ProducerRunnable( queue );
		Thread producerThread = new Thread( producer );
		
		DispatcherRunnable dispatcher = new DispatcherRunnable( queue );
		Thread dispatcherThread = new Thread( dispatcher );
		
		SubsystemOneObserver ss1Observer = new SubsystemOneObserver();
		SubsystemTwoObserver ss2Observer = new SubsystemTwoObserver();
		SubsystemThreeObserver ss3Observer = new SubsystemThreeObserver();
		
		dispatcher.registerSubsystem(ss1Observer);
		dispatcher.registerSubsystem(ss2Observer);
		dispatcher.registerSubsystem(ss3Observer);
		
		dispatcherThread.start();
		producerThread.start();
		
		try
		{
			Thread.sleep( 60000 );
		}
		catch( Exception e )
		{}
		
		producer.stop();
		dispatcher.stop();
		
		System.out.println( "done" );
	}

}
