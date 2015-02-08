package wolke7;

/**
 * 
 * @author	Thomas Schoenfeld
 * @date		2015-02-08
 *
 */
public class SampleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9220631061450252853L;
	
	public SampleNotFoundException(String id) {
		throw new RuntimeException("Sample with ID " + id + " not found!\n");
	}
}
