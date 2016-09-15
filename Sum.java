public interface Sum {
	public void incrementSum(int value);

	public void decrementSum(int value);

	public int getSum();
	
	public void displayState(String depsoit, String withdrawal, String balance);
	
	public void blockWithdrawalThread();
	
	public void unlockWithdrawalThread();
}
