package wolke7;

import java.util.List;

/**
 * 
 * @author	Thomas Schoenfeld
 * @date		2015-02-08
 *
 */
public interface SampleService {

	Sample create(Sample sample);
	
	Sample delete(String id);
	
	List<Sample> findAll();
	
	Sample findById(String id);
	
	Sample update(Sample sample);
}
