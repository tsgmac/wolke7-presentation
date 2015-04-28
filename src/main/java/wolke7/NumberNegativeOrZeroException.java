package wolke7;

/**
 * Given number is negative or equal zero.
 */
public class NumberNegativeOrZeroException extends IllegalArgumentException {

	private static final long	serialVersionUID	= 1L;

		public NumberNegativeOrZeroException() {
        super();
    }

    public NumberNegativeOrZeroException(String message) {
        super(message);
    }

    public NumberNegativeOrZeroException(int number) {
        this(Integer.toString(number));
    }

    /**
     * Checks if the given number is positive. Throws an exception if not.
     *
     * @param number Number to check.
     *
     * @throws NumberNegativeOrZeroException Given number is not positive.
     */
    public static void checkNumberPositive(int number) throws NumberNegativeOrZeroException {
        if (number <= 0) {
            throw new NumberNegativeOrZeroException(number);
        }
    }
}