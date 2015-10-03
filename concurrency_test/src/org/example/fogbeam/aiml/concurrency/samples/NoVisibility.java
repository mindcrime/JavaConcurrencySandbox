package org.example.fogbeam.aiml.concurrency.samples;

public class NoVisibility 
{
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread
	{
		@Override
		public void run() 
		{
			while( !ready )
			{
				Thread.yield();
			}
			
			System.out.println( number );
			
		}
	}

	public static void main(String[] args) 
	{
		System.out.println( "starting" );
		ReaderThread readerThread = new ReaderThread();
		readerThread.start();
		// without synchronization, there's no guarantee that our reader thread
		// will ever see this write that's about to happen.  Or, both writes could
		// be made visible to the reader thread, but the operations could be reordered
		// so we might see 0 printed out. 
		number = 42;
		ready = true;
	}

}
