//Name: Tyler Gauntlett
//Course: CNT 4714
//Assignment Title: Project 2: An Application Using Cooperating and Synchronized Multiple Threads In Java Using Locks
//Due Date: Sunday September 25, 2016 by 11:59 pm


// Application shows two threads manipulating a synchronized buffer.
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankTransactions {
	public static void main(String[] args) {
		// create new thread pool with 12 threads
		ExecutorService application = Executors.newFixedThreadPool(12);

		// create SynchronizedBuffer to store sum.
		Sum sharedSum = new SynchronizedSum();

		System.out.println("Deposit Threads\t\t\tWithdrawl Threads\t\t\tBalance");
		
		System.out.println("---------------\t\t\t-----------------\t\t\t-----------");

		try // try to start Withdrawer and Depositor
		{
			application.execute(new Withdrawer(sharedSum, "Thread 1"));
			application.execute(new Withdrawer(sharedSum, "Thread 2"));
			application.execute(new Withdrawer(sharedSum, "Thread 3"));
			application.execute(new Withdrawer(sharedSum, "Thread 4"));
			application.execute(new Withdrawer(sharedSum, "Thread 5"));
			application.execute(new Withdrawer(sharedSum, "Thread 6"));
			application.execute(new Withdrawer(sharedSum, "Thread 7"));
			application.execute(new Withdrawer(sharedSum, "Thread 8"));
			application.execute(new Withdrawer(sharedSum, "Thread 9"));
			application.execute(new Depositor(sharedSum,"Thread 1"));
			application.execute(new Depositor(sharedSum,"Thread 2"));
			application.execute(new Depositor(sharedSum,"Thread 3"));
		} // end try
		catch (Exception exception) {
			exception.printStackTrace();
		} // end catch

		application.shutdown();
	} // end main
} // end class BankTransactions