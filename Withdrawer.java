// Withdrawer's run method tries to withdrawal a random amount of money to a shared sum, and is blocked if it detects insufficient funds.
import java.util.Random;

public class Withdrawer implements Runnable {
	private static Random generator = new Random();
	private Sum sharedSum; // reference to shared sum
	private String name; // Name of thread in form "Thread n" where n is an int

	// constructor
	public Withdrawer(Sum sharedSum, String name) {
		this.name = name;
		this.sharedSum = sharedSum;
	} // end Consumer constructor

	// read sharedLocation's value four times and sum the values
	public void run() {
		// Run threads indefinitely.
		for (int i = 0; i < 300; i++) {
			try {
				// Get a random int of value 1-50.
				int withdrawlAmount = 1 + generator.nextInt(50);

				// Check if there is enough money in the account to withdraw the random amount.
				if ((sharedSum.getSum() - withdrawlAmount) > 0) {
					// If there is enough, subtract the random amount from the shared sum.
					sharedSum.decrementSum(withdrawlAmount);
					// Print to console the name of the thread, how much it withdrew and the current shared balance.
					sharedSum.displayState("", name + " withdraws $" + withdrawlAmount,
							"Balance is $" + sharedSum.getSum());
				} else {
					// If there is not enough money, print the blocked status.
					sharedSum.displayState("", name + " withdraws $" + withdrawlAmount, "Blocked - Insufficient Funds");
					// Block the withdrawal thread until signaled by the depositor.
					sharedSum.blockWithdrawalThread();
				}
				//  Sleep thread for 0 to .3 seconds
				Thread.sleep(generator.nextInt(300));

			} // end try
				// if sleeping thread interrupted, print stack trace
			catch (InterruptedException exception) {
				exception.printStackTrace();
			} // end catch
		}
	} // end method run
} // end class Consumer