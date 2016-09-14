// Buffer interface specifies methods called by Producer and Consumer.

public interface Buffer {
	public void set(int value, int sum, String name); // place int value into Buffer

	public int get(String name, int withdrawlAmount, int sum); // return int value from Buffer
} // end interface Buffer
