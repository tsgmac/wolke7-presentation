package wolke7;

/**
 * Given string is (trimmed) empty.
 */
public class StringEmptyException extends IllegalArgumentException {

	private static final long	serialVersionUID	= 1L;

	public StringEmptyException() {
		super();
	}

	public StringEmptyException(String message) {
		super(message);
	}

	/**
	 * Checks if the given string is (trimmed) not empty. Throws an exception if
	 * empty.
	 *
	 * @param string
	 *          String to check.
	 *
	 * @throws clusteringCloud.Exceptions.StringEmptyException
	 *           Given string is empty.
	 */
	public static void checkStringNotEmpty(String string)
			throws StringEmptyException {
		if (string.trim().isEmpty()) {
			throw new StringEmptyException(string);
		}
	}
}