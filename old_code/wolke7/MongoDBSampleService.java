package wolke7;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Thomas Schoenfeld
 * @param <EntityManager>
 * @date 2015-03-28
 *
 */
@Service
public class MongoDBSampleService implements SampleRepository {

	@Autowired
	private SampleRepository	repo;

	@Override
	public Sample delete(String id) {
		Sample deleted = findSampleById(id);
		repo.delete(deleted);
		return deleted;
	}

	@Override
	public void delete(Sample sample) {
		Sample deleted = findSampleById(sample.getId());
		repo.delete(deleted);
	}

	@Override
	public List<Sample> findAll() {
		List<Sample> samples = repo.findAll();
		return samples;
	}

	@Override
	public Sample findById(String id) {
		Sample sample = findSampleById(id);
		return sample;
	}

	@Override
	public Sample findSampleBySampleName(String sampleName) {
		Sample sample = repo.findSampleBySampleName(sampleName);
		return sample;
	}

	private Sample findSampleById(String id) {
		Optional<Sample> result = repo.findOne(id);
		return result.orElseThrow(() -> new SampleNotFoundException(id));
	}

	@Override
	public Optional<Sample> findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
