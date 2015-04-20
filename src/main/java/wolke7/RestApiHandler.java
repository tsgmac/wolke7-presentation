package wolke7;

import java.util.ArrayList;
import java.util.List;

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
 * @date 2015-04-20
 *
 */
@RestController
@RequestMapping("/samples")
public class RestApiHandler extends AbstractRestHandler {

	@Autowired
	private SampleRepository	sampleRepository;

	@Autowired
	private MongoTemplate			mongoTemplate;

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
	@RequestMapping(value = "{sampleName}", method = RequestMethod.GET)
	public @ResponseBody List<Sample> findSampleBySampleName(
			@PathVariable("sampleName") String sampleName) {

//		if (!(sampleName instanceof String))
//			throw new IllegalArgumentException("This is not a String.");

		List<Sample> samples = new ArrayList<Sample>();
		for (Sample sample : sampleRepository.findAll()) {
			if (sample.getSampleName().equals(sampleName.trim())) {
				samples.add(sample);
			}
		}

		return samples;
	}

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

	@RequestMapping("header")
	public @ResponseBody List<Sample> findSampleHeader() {
		Query query = new Query();
		query.fields().exclude("clusters");
		List<Sample> header = mongoTemplate.find(query, Sample.class);
		return header;
	}

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

//		if (!(sampleName instanceof String))
//			throw new IllegalArgumentException("This is not a String.");

		for (Sample sample : sampleRepository.findAll()) {
			if (sample.getSampleName().equals(sampleName.trim())) {
				sampleRepository.delete(sample);
			}
		}				
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleSampleNotFound(SampleNotFoundException ex) {}
}