package wolke7;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoDBSampleService implements SampleService {

	@Autowired
	private SampleRepository repo; 	
	
	@Override
	public Sample create(Sample sample) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sample delete(String id) {
		Sample deleted = findSampleById(id);
		repo.delete(deleted);
		return deleted;
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
	public Sample update(Sample sample) {
		// TODO Auto-generated method stub
		return null;
	}

	private Sample findSampleById(String id) {
		Optional<Sample> result = repo.findOne(id);
		return result.orElseThrow(() -> new SampleNotFoundException(id));
	}
}
