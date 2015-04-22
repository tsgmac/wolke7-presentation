package wolke7;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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
 * @date 2015-04-22
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
	// Version 1
	// @RequestMapping("all")
	// public @ResponseBody List<Sample> findAll(
	// @RequestParam(value = "page", required = true) Integer page,
	// @RequestParam(value = "size", required = true) Integer size) {
	//
	// Page<Sample> pageOfSample = sampleRepository.findAll(new PageRequest(page,
	// size));
	//
	// return pageOfSample.getContent();
	// }

	// Version 2
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
	// Version 1
	// @RequestMapping(value = "{sampleId}", method = RequestMethod.GET)
	// public @ResponseBody Sample findSampleById(
	// @PathVariable("sampleId") String sampleId) {
	// return sampleRepository.findById(sampleId);
	// }

	// Version 2
	// @RequestMapping(value = "{sampleName}", method = RequestMethod.GET)
	// public @ResponseBody List<Sample> findSampleBySampleName(
	// @PathVariable("sampleName") String sampleName) {
	// return sampleRepository.findSampleBySampleName(sampleName);
	// }

	// Version 3
	// @RequestMapping(value = "{sampleName}", method = RequestMethod.GET)
	// public @ResponseBody List<Sample> findSampleBySampleName(
	// @PathVariable("sampleName") String sampleName) {
	//
	// // if (!(sampleName instanceof String))
	// // throw new IllegalArgumentException("This is not a String.");
	//
	// List<Sample> samples = new ArrayList<Sample>();
	// for (Sample sample : this.findSampleHeader()) {
	// if (sample.getSampleName().equals(sampleName.trim())) {
	// samples.add(sample);
	// }
	// }
	//
	// return samples;
	// }

	// Version 4
	@RequestMapping(value = "{sampleName}", method = RequestMethod.GET)
	public @ResponseBody List<Object> findSampleBySampleName(
			@PathVariable("sampleName") String sampleName) {

		// if (!(sampleName instanceof String))
		// throw new IllegalArgumentException("This is not a String.");

		List<Object> result = new ArrayList<Object>();

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
	// Version 1
	// @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// public void delete(@PathVariable("id") String id) {
	// sampleRepository.delete(id);
	// // return service.delete(id);
	// return;
	// }

	// Version 2
	@RequestMapping(value = "{sampleName}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("sampleName") String sampleName) {

		// if (!(sampleName instanceof String))
		// throw new IllegalArgumentException("This is not a String.");

		for (Sample sample : this.findAll()) {
			if (sample.getSampleName().equals(sampleName.trim())) {
				sampleRepository.delete(sample);
			}
		}
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

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleSampleNotFound(SampleNotFoundException ex) {}
}