
// Producer's run method stores the values 1 to 10 in buffer.
import java.util.Random;

public class Depositer implements Runnable {
	private static Random generator = new Random();
	private Buffer sharedLocation; // reference to shared object
	private Sum sharedSum;
	private String name;

	// constructor
	public Depositer(Buffer shared, Sum sharedSum, String name) {
		sharedLocation = shared;
		this.name = name;
		this.sharedSum = sharedSum;
	} // end Producer constructor

	// store values from 1 to 10 in sharedLocation
	public void run() {
		for(int i = 0; i < 5; i++){
			try // sleep 0 to 3 seconds, then place value in Buffer
			{
				int depositAmount = 1 + generator.nextInt(250);
				sharedLocation.set(depositAmount, sharedSum.getSum(), name); // update sum.
				sharedSum.incrementSum(depositAmount);
				Thread.sleep(generator.nextInt(30)); // sleep thread
			} // end try
				// if sleeping thread interrupted, print stack trace
			catch (InterruptedException exception) {
				exception.printStackTrace();
			} // end catch
		}
	} // end method run
} // end class Producer
