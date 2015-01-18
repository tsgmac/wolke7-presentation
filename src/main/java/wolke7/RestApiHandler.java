package wolke7;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/samples")
public class RestApiHandler extends AbstractRestHandler {

	@Autowired
	private SampleService service;

	@Autowired
	private SampleRepository sampleRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	// @RequestMapping(value = "{sampleId}", method = RequestMethod.GET)
	// public @ResponseBody Sample findSampleById(
	// @PathVariable("sampleId") String sampleId) {
	// return sampleRepository.findById(sampleId);
	// }

	@RequestMapping(value = "{sampleName}", method = RequestMethod.GET)
	public @ResponseBody Sample findSampleBySampleName(
			@PathVariable("sampleName") String sampleName) {
		return sampleRepository.findBySampleName(sampleName);
	}

	@RequestMapping("all")
//	public @ResponseBody List<Sample> findAll(
//			@RequestParam(value = "page", required = true) Integer page,
//			@RequestParam(value = "size", required = true) Integer size) {
//
//		Page<Sample> pageOfSample = sampleRepository.findAll(new PageRequest(page, size));
//
//		return pageOfSample.getContent();
//	}
	public @ResponseBody List<Sample> findAll() {
		List<Sample> results = sampleRepository.findAll();
		return results;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	Sample delete(@PathVariable("id") String id) {
		return service.delete(id);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleSampleNotFound(SampleNotFoundException ex) {}
}