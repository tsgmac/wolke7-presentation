package wolke7;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

/**
 * 
 * @author	Thomas Schoenfeld
 * @date		2015-02-08
 *
 */
public interface SampleRepository extends Repository<Sample, String> {

	Sample save(Sample sample);

	void delete(Sample sample);

	// Sample findById(String id);
	Sample findBySampleName(String sampleName);

	Optional<Sample> findOne(String id);

	List<Sample> findAll();
	// Page<Sample> findAll(Pageable pageable);
}
