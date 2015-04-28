package wolke7;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

/**
 * 
 * @author	Thomas Schoenfeld
 * @date		2015-04-27
 *
 */
public interface SampleRepository extends Repository<Sample, String> {

	void delete(Sample sample);
	
	Sample delete(String sampleName);

	Sample findById(String id);
	
	Sample findSampleBySampleName(String sampleName);

	Optional<Sample> findOne(String id);

	List<Sample> findAll();
	
}
