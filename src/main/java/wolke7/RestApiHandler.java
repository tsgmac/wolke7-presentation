package wolke7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Thomas Schoenfeld
 * @date 2015-04-25
 *
 */
@RestController
@RequestMapping("/samples")
public class RestApiHandler extends AbstractRestHandler {

	@Autowired
	private SampleRepository	sampleRepository;

	@Autowired
	private MongoTemplate			mongoTemplate;

	/**
	 * Finds all samples. Complete output including the clusters.
	 * 
	 * @return all samples with clusters
	 */
	@RequestMapping("all")
	public @ResponseBody List<Sample> findAll() {
		List<Sample> results = sampleRepository.findAll();
		return results;
	}

	/**
	 * Finds all samples. Short output excluding the clusters.
	 * 
	 * @return all samples without clusters
	 */
	@RequestMapping("header")
	public @ResponseBody List<Sample> findSampleHeader() {
		Query query = new Query();
		query.fields().exclude("clusters");
		List<Sample> header = mongoTemplate.find(query, Sample.class);
		return header;
	}

	/**
	 * Finds all samples with a given name.<br>
	 * Assigns the search results to their respective algorithms.
	 * 
	 * @param sampleName
	 * 
	 * @return all samples with a given name assigned to their algorithms
	 */
	@RequestMapping(value = "{sampleName}", method = RequestMethod.GET)
	public @ResponseBody List<Object> findSampleBySampleName(
			@PathVariable("sampleName") String sampleName) {

		// Should not be necessary if you use the method via web.
		if (!(sampleName instanceof String)) {
			throw new IllegalArgumentException(NO_STRING);
		}

		List<Object> result = new ArrayList<Object>();

		if (!(this.nameExists(sampleName))) {
			return result;
		}

		for (String algorithmName : this.algorithms()) {

			List<Object> algorithmClasses = new ArrayList<Object>();
			algorithmClasses.add(algorithmName);
			List<Sample> samples = new ArrayList<Sample>();

			for (Sample sample : this.findSampleHeader()) {
				if (sample.getSampleName().equals(sampleName.trim())
						&& (sample.getAlgorithmName().equals(algorithmName))) {
					samples.add(sample);
				}
			}

			algorithmClasses.add(samples);
			result.add(algorithmClasses);
		}

		return result;
	}

	/**
	 * Deletes all samples with a given name.
	 * 
	 * @param sampleName
	 */
	@RequestMapping(value = "{sampleName}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("sampleName") String sampleName) {

		// Should not be necessary if you use the method via web.
		if (!(sampleName instanceof String)) {
			throw new IllegalArgumentException(NO_STRING);
		}

		if (!(this.nameExists(sampleName))) {
			throw new SampleNotFoundException(sampleName);
		}

		for (Sample sample : this.findSampleHeader()) {
			if (sample.getSampleName().equals(sampleName.trim())) {
				sampleRepository.delete(sample);
			}
		}
	}

//	@RequestMapping(value = "{input}", method = RequestMethod.POST)
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Response createClusterExperiment(
			@PathVariable("input") String input)
			throws URISyntaxException, KeyManagementException,
			NoSuchAlgorithmException, IOException {
		return (new ClusterExperimentAPI()).createClusterExperiment(input);
	}

	/**
	 * Searches for all used algorithms in the database and returns a TreeSet to
	 * prevent duplicates.
	 * 
	 * @return TreeSet with names of all used algorithms.
	 */
	private TreeSet<String> algorithms() {

		TreeSet<String> algorithmNames = new TreeSet<String>();

		for (Sample sample : this.findSampleHeader()) {
			algorithmNames.add(sample.getAlgorithmName());
		}

		return algorithmNames;
	}

	/**
	 * Checks if a sample with the passed name exists in the database.
	 * 
	 * @param sampleName
	 *          name of the searched sample
	 * 
	 * @return true if the sample exists else false
	 */
	private boolean nameExists(String sampleName) {

		boolean nameExists = false;

		if (!(sampleName instanceof String)) {
			throw new IllegalArgumentException(NO_STRING);
		}

		for (Sample sample : this.findSampleHeader()) {
			if (sample.getSampleName().equals(sampleName.trim())) {
				nameExists = true;
			}
		}

		return nameExists;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleSampleNotFound(SampleNotFoundException ex) {}
}