
// SynchronizedBuffer synchronizes access to a single shared integer.
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class SynchronizedBuffer implements Buffer {
	// Lock to control synchronization with this buffer
	private Lock accessLock = new ReentrantLock();

	// conditions to control reading and writing
	private Condition canWrite = accessLock.newCondition();
	private Condition canRead = accessLock.newCondition();

	private int buffer = -1; // shared by producer and consumer threads
	private boolean occupied = false; // whether buffer is occupied

	// place int value into buffer
	public void set(int value, int sum, String name) {
		accessLock.lock(); // lock this object

		// output thread information and buffer information, then wait
		try {
			// while buffer is not empty, place thread in waiting state
			while (occupied) {
				canWrite.await();// wait until buffer is empty
			} // end while

			buffer = value; // set new buffer value

			// indicate producer cannot store another value
			// until consumer retrieves current buffer value
			occupied = true;
			
			sum += value;

			displayState(name + " deposits $" + buffer, " ", "\t\tBalance is $" + sum);

			// signal thread waiting to read from buffer
			canRead.signal();
		} // end try
		catch (InterruptedException exception) {
			exception.printStackTrace();
		} // end catch
		finally {
			accessLock.unlock(); // unlock this object
		} // end finally
	} // end method set

	// return value from buffer
	public int get(String name, int withdrawlAmount, int sum) {
		int readValue = 0; // initialize value read from buffer
		accessLock.lock(); // lock this object

		// output thread information and buffer information, then wait
		try {
			// while no data to read, place thread in waiting state
			while (sum - withdrawlAmount < 0) {
				displayState(" ", name + " withdrawls $" + withdrawlAmount, "Withdrawl - Blocked - Insufficient Funds");
				canRead.await(); // wait until buffer is full
			} // end while

			// indicate that producer can store another value
			// because consumer just retrieved buffer value
			occupied = false;

			readValue = buffer; // retrieve value from buffer
			displayState(" ", name + " withdrawls $" + withdrawlAmount, "Balance is $" + sum);

			// signal thread waiting for buffer to be empty
			canWrite.signal();
		} // end try
			// if waiting thread interrupted, print stack trace
		catch (InterruptedException exception) {
			exception.printStackTrace();
		} // end catch
		finally {
			accessLock.unlock(); // unlock this object
		} // end finally

		return readValue;
	} // end method get

	// display current operation and buffer state
	public void displayState(String depsoit, String withdrawl, String balance) {
		System.out.printf("%-40s%s\t\t%s\n", depsoit, withdrawl, balance);
	} // end method displayState
} // end class SynchronizedBuffer
