package wolke7;

/**
 * 
 * @author	Thomas Schoenfeld
 * @date		2015-04-25
 *
 */
public class SampleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9220631061450252853L;
	
	public SampleNotFoundException(String sampleName) {
		throw new RuntimeException("Sample " + sampleName + " not found!\n");
	}
}
