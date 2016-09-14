public class SynchronizedSum implements Sum {
	private int sum = 0; // shared by producer and consumer threads

	public void incrementSum(int value) {
		sum += value;
	}

	public void decrementSum(int value) {
		sum -= value;
	}

	// return value from buffer
	public int getSum() {
		return sum;
	} // end method get

	// display current operation and buffer state
	public void displayState(String depsoit, String withdrawl, String balance) {
		System.out.printf("%-40s%s\t\t\t\t\t%s\n", depsoit, withdrawl, balance);
	} // end method displayState
} 
