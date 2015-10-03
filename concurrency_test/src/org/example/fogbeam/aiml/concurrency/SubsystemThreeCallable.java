package org.example.fogbeam.aiml.concurrency;

import java.util.concurrent.Callable;

public class SubsystemThreeCallable implements Callable<String> 
{
	
	private volatile boolean keepRunning = true;

	private String message;
	
	public SubsystemThreeCallable( String message )
	{
		this.message = message;
	}
	
	@Override
	public String call()
	{
		System.out.println( "SubsystemThree handling input: " + message );
		
		try 
		{
			Thread.sleep( 612 );
		} 
		catch (InterruptedException e)
		{
		}
		
		String response = "That's awesome!";
		return response;
	}
}
