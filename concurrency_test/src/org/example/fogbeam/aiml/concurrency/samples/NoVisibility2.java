package org.example.fogbeam.aiml.concurrency.samples;

public class NoVisibility2 
{
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread
	{
		@Override
		public void run() 
		{
			synchronized( NoVisibility2.class )
			{
				while( !ready )
				{
					Thread.yield();
				}
			
				System.out.println( number );
			}
		}
	}

	public static void main(String[] args) 
	{
		System.out.println( "starting" );
		ReaderThread readerThread = new ReaderThread();
		readerThread.start();
		
		// now access to these mutable variables is guarded by the same lock in
		// both threads, so we are guaranteed that after a lock is released, the next
		// read will get the updated state of the variables. 
		synchronized( NoVisibility2.class )
		{
			number = 42;
			ready = true;
		}
	}

}
