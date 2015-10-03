package org.example.fogbeam.aiml.concurrency;

import java.util.concurrent.Callable;

public class SubsystemTwoCallable implements Callable<String> 
{

	private volatile boolean keepRunning = true;
	private String message;
	
	public SubsystemTwoCallable( String message )
	{
		this.message = message;
	}
	
	@Override
	public String call() 
	{
		System.out.println( "SubsystemTwo handling input: " + message );
		
		try 
		{
			Thread.sleep( 345 );
		} 
		catch (InterruptedException e)
		{
		}
		
		String response = "Gnarly, dude!";
		return response;
	}
}