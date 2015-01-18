package wolke7;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="samples")
public class Sample {

	@Id
	private String id;
	private String sampleName;
	private String uri;

	public Sample() {}

	public Sample(String id, String sampleName, String uri) {
		this.id = id;
		this.sampleName = sampleName;
		this.uri = uri;
	}

	public String getId() {
		return id;
	}

	public String getSampleName() {
		return sampleName;
	}

	public String getUri() {
		return uri;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sample [id=");
		builder.append(id);
		builder.append(", sampleName=");
		builder.append(sampleName);
		builder.append(", uri=");
		builder.append(uri);
		builder.append("]");
		return builder.toString();
	}

}
