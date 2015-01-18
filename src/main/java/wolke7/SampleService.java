package wolke7;

import java.util.List;

public interface SampleService {

	Sample create(Sample sample);
	
	Sample delete(String id);
	
	List<Sample> findAll();
	
	Sample findById(String id);
	
	Sample update(Sample sample);
}
