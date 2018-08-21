package algorithms;

public class Generic {

	// O(1) operation to determine if a number is an exponent of 2
	public boolean exponentOf2(int num) {
		boolean bitwise = (num & num-1) == 0; // bitwise intersection of num and num-1
		return bitwise;
	}
}
