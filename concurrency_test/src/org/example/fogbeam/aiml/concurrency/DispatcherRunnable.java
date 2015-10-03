package org.example.fogbeam.aiml.concurrency;

import java.util.HashSet;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class DispatcherRunnable implements Runnable 
{
	private volatile boolean keepRunning = true;
	
	private BlockingQueue<String> workQueue;
	private Set<Observer> subsystemObservers = new HashSet<Observer>();
	
	public DispatcherRunnable( BlockingQueue<String> workQueue )
	{
		this.workQueue = workQueue;
	}
	
	
	@Override
	public void run() 
	{
		while( keepRunning )
		{
			try 
			{
				String input = workQueue.take();
				
				System.out.println( "Dispatcher received message: " + input );
				
				// we received an input message, deliver it to all of the registered
				// subsystems
				for( Observer subsystemObserver : subsystemObservers )
				{
					subsystemObserver.update(null, input );
				}
				
			} 
			catch (InterruptedException e) 
			{
				
			}
		}
		
		System.out.println( "DispatcherRunnable stopped..." );
	}

	
	public void registerSubsystem( Observer subsystemObserver )
	{
		this.subsystemObservers.add( subsystemObserver );
	}
	
	public void stop()
	{
		this.keepRunning = false;
	}

	
}
