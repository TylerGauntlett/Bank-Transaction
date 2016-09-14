
// Application shows two threads manipulating a synchronized buffer.
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankTransactions {
	public static void main(String[] args) {
		// create new thread pool with two threads
		ExecutorService application = Executors.newFixedThreadPool(3);

		// create SynchronizedBuffer to store ints
		Buffer sharedLocation = new SynchronizedBuffer();
		Sum sharedSum = new SynchronizedSum();

		System.out.printf("%-40s%s\t\t%s\n%-40s%s\n\n", "Deposit Threads", "Withdrawl Threads", "      Balance",
				"---------------", "---------------\t\t\t      --------");

		try // try to start producer and consumer
		{
			application.execute(new Depositer(sharedLocation, sharedSum,"Thread 1"));
			application.execute(new Withdrawler(sharedLocation, sharedSum, "Thread 1"));
			application.execute(new Withdrawler(sharedLocation, sharedSum, "Thread 2"));
		} // end try
		catch (Exception exception) {
			exception.printStackTrace();
		} // end catch

		application.shutdown();
	} // end main
} // end class SharedBufferTest2