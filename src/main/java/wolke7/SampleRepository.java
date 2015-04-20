package wolke7;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

/**
 * 
 * @author	Thomas Schoenfeld
 * @date		2015-03-28
 *
 */
public interface SampleRepository extends Repository<Sample, String> {

	void delete(Sample sample);
	
	Sample delete(String id);

	Sample findById(String id);
	
	Sample findSampleBySampleName(String sampleName);

	Optional<Sample> findOne(String id);

	List<Sample> findAll();
	
	// Page<Sample> findAll(Pageable pageable);
	
}
