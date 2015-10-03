package org.example.fogbeam.aiml.concurrency;

import java.util.concurrent.Callable;

public class SubsystemOneCallable implements Callable<String>
{

	private volatile boolean keepRunning = true;
	
	private String message;
	
	public SubsystemOneCallable( String message )
	{
		this.message = message;
	}
	
	@Override
	public String call() 
	{
		System.out.println( "SubsystemOne handling input: " + message );
		
		try 
		{
			Thread.sleep( 750 );
		} 
		catch (InterruptedException e)
		{
		}
		
		String response = "Yeah, right!";
		
		return response;
	}	
}
