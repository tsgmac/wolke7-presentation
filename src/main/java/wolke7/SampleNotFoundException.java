package wolke7;

public class SampleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9220631061450252853L;
	
	public SampleNotFoundException(String id) {
		throw new RuntimeException("Sample with ID " + id + " not found!\n");
	}
}
