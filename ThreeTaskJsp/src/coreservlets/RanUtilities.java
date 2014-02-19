package coreservlets; // Always use packages!!

/** Simple utility to generate random integers. */
public class RanUtilities {
	/** A random int from 1 to range (inclusive). */
	public static int randomInt(int range) {
		return (1 + ((int) (Math.random() * range)));
	}

	public static void main(String[] args) {
		int range = 10;
		try {
			range = Integer.parseInt(args[0]);
		} catch (Exception e) { // Array index or number format
			// Do nothing: range already has default value.
		}
		for (int i = 0; i < 100; i++) {
			System.out.println(randomInt(range));
		}
	}
}