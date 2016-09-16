// Depositor's run method deposits a random amount of money to a shared sum.
import java.util.Random;

public class Depositor implements Runnable {
	private static Random generator = new Random();
	private Sum sharedSum; // reference to shared sum
	private String name; // Name of thread in form "Thread n" where n is an int

	// constructor
	public Depositor(Sum sharedSum, String name) {
		this.name = name;
		this.sharedSum = sharedSum;
	} // end Producer constructor
	
	public void run() {
		// Run threads indefinitely.
		while (true) {
			try
			{
				// Get a random int of value 1-200.
				int depositAmount = 1 + generator.nextInt(200);
				// Add the random amount to the shared sum.
				sharedSum.incrementSum(depositAmount); 
				// Print to console the name of the thread, how much it withdrew and the current shared balance.
				sharedSum.displayState(name + " deposited $" + Integer.toString(depositAmount), "", "Balance is $" + sharedSum.getSum());
				// Unlock the withdrawal thread because money has been deposited.
				sharedSum.unlockWithdrawalThread();
				//  Sleep thread for 0 to .9 seconds
				Thread.sleep(generator.nextInt(900)); 
			} // end try
				// if sleeping thread interrupted, print stack trace
			catch (InterruptedException exception) {
				exception.printStackTrace();
			} // end catch
		}
	} // end method run
} // end class Producer
