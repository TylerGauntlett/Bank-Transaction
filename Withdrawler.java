
// Consumer's run method loops ten times reading a value from buffer.
import java.util.Random;

public class Withdrawler implements Runnable {
	private static Random generator = new Random();
	private Buffer sharedLocation; // reference to shared object
	private Sum sharedSum;
	private String name;

	// constructor
	public Withdrawler(Buffer shared, Sum sharedSum, String name) {
		sharedLocation = shared;
		this.name = name;
		this.sharedSum = sharedSum;
	} // end Consumer constructor

	// read sharedLocation's value four times and sum the values
	public void run() {
		for(int i = 0; i < 5; i++){
			// sleep 0 to 3 seconds, read value from buffer and add to sum
			try {
				int withdrawlAmount = 1 + generator.nextInt(50);
				
				if((sharedSum.getSum() - withdrawlAmount) < 0){					
					Thread.sleep(generator.nextInt(30));
				} else {
					sharedSum.decrementSum(withdrawlAmount);
					sharedLocation.get(name, withdrawlAmount, sharedSum.getSum()); // true if it can withdrawl money, false if it can not.
				}
				
			} // end try
				// if sleeping thread interrupted, print stack trace
			catch (InterruptedException exception) {
				exception.printStackTrace();
			} // end catch
		}
	} // end method run
} // end class Consumer
