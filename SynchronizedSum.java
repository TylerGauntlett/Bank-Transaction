import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class SynchronizedSum implements Sum {
	private Lock accessLock = new ReentrantLock();

	private Condition canWithdraw = accessLock.newCondition();

	private int sum = 0; // shared by producer and consumer threads

	// Increase the shared sum by a value.
	public void incrementSum(int value) {
		sum += value;
	}
	// Decrease the shared sum by a value.
	public void decrementSum(int value) {
		sum -= value;
	}

	// Block the withdrawal class.
	public void blockWithdrawalThread() {
		accessLock.lock(); // lock this object
		try {
			// Pause the thread where this function is called.
			canWithdraw.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			accessLock.unlock(); // unlock this object
		} // end finally

	}

	public void unlockWithdrawalThread() {
		accessLock.lock(); // lock this object
		try {
			// Signal withdrawer thread waiting for money to become available
			canWithdraw.signal();
		} finally {
			accessLock.unlock(); // unlock this object
		} // end finally
	}

	// Return current shared sum.
	public int getSum() {
		return sum;
	}

	// display current thread and balance.
	public void displayState(String deposit, String withdrawal, String balance) {
		System.out.println(deposit + "\t\t\t\t" + withdrawal + "\t\t\t" + balance);
	} // end method displayState
}
