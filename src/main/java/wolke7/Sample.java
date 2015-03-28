package wolke7;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Thomas Schoenfeld
 * @date 2015-02-08
 * 
 *       Die Klasse Sample beschreibt eine einzelne Berechnung.
 */
// @Document(collection = "samples")
@Document(collection = "experiments")
public class Sample {

	@Id
	private String				id;
	private String				sampleName;
	private String				algorithmName;
	private int						numNodes;
	private List<Node>		startNodes;
	private int						desiredClusterCount;
	private int						clusterCount;
	private boolean				validity;
	private String				invalidityReason;
	private int						maxClusterSize;
	private int						minClusterSize;
	private List<Integer>	clusterSize;
	private double				clusterSpread;
	private List<Double>	spreadCurve;
	private List<Cluster>	clusters;
	private String				uri;

	/**
	 * Standard-Konstruktor der Klasse Sample.
	 */
	public Sample() {}

	/**
	 * Konstruktor der Klasse Sample.
	 * 
	 * @param id
	 * @param sampleName
	 * @param uri
	 */
	public Sample(String id, String sampleName, String uri) {

		this.id = id;
		this.sampleName = sampleName;
		this.uri = uri;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *          the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the sampleName
	 */
	public String getSampleName() {
		return sampleName;
	}

	/**
	 * @param sampleName
	 *          the sampleName to set
	 */
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	/**
	 * @return the algorithmName
	 */
	public String getAlgorithmName() {
		return algorithmName;
	}

	/**
	 * @param algorithmName
	 *          the algorithmName to set
	 */
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	/**
	 * @return the numNodes
	 */
	public int getNumNodes() {
		return numNodes;
	}

	/**
	 * @param numNodes
	 *          the numNodes to set
	 */
	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}

	/**
	 * @return the startNodes
	 */
	public List<Node> getStartNodes() {
		return startNodes;
	}

	/**
	 * @param startNodes
	 *          the startNodes to set
	 */
	public void setStartNodes(List<Node> startNodes) {
		this.startNodes = startNodes;
	}

	/**
	 * @return the desiredClusterCount
	 */
	public int getDesiredClusterCount() {
		return desiredClusterCount;
	}

	/**
	 * @param desiredClusterCount
	 *          the desiredClusterCount to set
	 */
	public void setDesiredClusterCount(int desiredClusterCount) {
		this.desiredClusterCount = desiredClusterCount;
	}

	/**
	 * @return the clusterCount
	 */
	public int getClusterCount() {
		return clusterCount;
	}

	/**
	 * @param clusterCount
	 *          the clusterCount to set
	 */
	public void setClusterCount(int clusterCount) {
		this.clusterCount = clusterCount;
	}

	/**
	 * @return the validity
	 */
	public boolean isValidity() {
		return validity;
	}

	/**
	 * @param validity
	 *          the validity to set
	 */
	public void setValidity(boolean validity) {
		this.validity = validity;
	}

	/**
	 * @return the invalidityReason
	 */
	public String getInvalidityReason() {
		return invalidityReason;
	}

	/**
	 * @param invalidityReason
	 *          the invalidityReason to set
	 */
	public void setInvalidityReason(String invalidityReason) {
		this.invalidityReason = invalidityReason;
	}

	/**
	 * @return the maxClusterSize
	 */
	public int getMaxClusterSize() {
		return maxClusterSize;
	}

	/**
	 * @param maxClusterSize
	 *          the maxClusterSize to set
	 */
	public void setMaxClusterSize(int maxClusterSize) {
		this.maxClusterSize = maxClusterSize;
	}

	/**
	 * @return the minClusterSize
	 */
	public int getMinClusterSize() {
		return minClusterSize;
	}

	/**
	 * @param minClusterSize
	 *          the minClusterSize to set
	 */
	public void setMinClusterSize(int minClusterSize) {
		this.minClusterSize = minClusterSize;
	}

	/**
	 * @return the clusterSize
	 */
	public List<Integer> getClusterSize() {
		return clusterSize;
	}

	/**
	 * @param clusterSize
	 *          the clusterSize to set
	 */
	public void setClusterSize(List<Integer> clusterSize) {
		this.clusterSize = clusterSize;
	}

	/**
	 * @return the clusterSpread
	 */
	public double getClusterSpread() {
		return clusterSpread;
	}

	/**
	 * @param clusterSpread
	 *          the clusterSpread to set
	 */
	public void setClusterSpread(double clusterSpread) {
		this.clusterSpread = clusterSpread;
	}

	/**
	 * @return the spreadCurve
	 */
	public List<Double> getSpreadCurve() {
		return spreadCurve;
	}

	/**
	 * @param spreadCurve
	 *          the spreadCurve to set
	 */
	public void setSpreadCurve(List<Double> spreadCurve) {
		this.spreadCurve = spreadCurve;
	}

	/**
	 * @return the clusters
	 */
	public List<Cluster> getClusters() {
		return clusters;
	}

	/**
	 * @param clusters
	 *          the clusters to set
	 */
	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *          the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((algorithmName == null) ? 0 : algorithmName.hashCode());
		result = prime * result + clusterCount;
		result = prime * result
				+ ((clusterSize == null) ? 0 : clusterSize.hashCode());
		long temp;
		temp = Double.doubleToLongBits(clusterSpread);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((clusters == null) ? 0 : clusters.hashCode());
		result = prime * result + desiredClusterCount;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((invalidityReason == null) ? 0 : invalidityReason.hashCode());
		result = prime * result + maxClusterSize;
		result = prime * result + minClusterSize;
		result = prime * result + numNodes;
		result = prime * result
				+ ((sampleName == null) ? 0 : sampleName.hashCode());
		result = prime * result
				+ ((spreadCurve == null) ? 0 : spreadCurve.hashCode());
		result = prime * result
				+ ((startNodes == null) ? 0 : startNodes.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		result = prime * result + (validity ? 1231 : 1237);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Sample))
			return false;
		Sample other = (Sample) obj;
		if (algorithmName == null) {
			if (other.algorithmName != null)
				return false;
		}
		else if (!algorithmName.equals(other.algorithmName))
			return false;
		if (clusterCount != other.clusterCount)
			return false;
		if (clusterSize == null) {
			if (other.clusterSize != null)
				return false;
		}
		else if (!clusterSize.equals(other.clusterSize))
			return false;
		if (Double.doubleToLongBits(clusterSpread) != Double
				.doubleToLongBits(other.clusterSpread))
			return false;
		if (clusters == null) {
			if (other.clusters != null)
				return false;
		}
		else if (!clusters.equals(other.clusters))
			return false;
		if (desiredClusterCount != other.desiredClusterCount)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (invalidityReason == null) {
			if (other.invalidityReason != null)
				return false;
		}
		else if (!invalidityReason.equals(other.invalidityReason))
			return false;
		if (maxClusterSize != other.maxClusterSize)
			return false;
		if (minClusterSize != other.minClusterSize)
			return false;
		if (numNodes != other.numNodes)
			return false;
		if (sampleName == null) {
			if (other.sampleName != null)
				return false;
		}
		else if (!sampleName.equals(other.sampleName))
			return false;
		if (spreadCurve == null) {
			if (other.spreadCurve != null)
				return false;
		}
		else if (!spreadCurve.equals(other.spreadCurve))
			return false;
		if (startNodes == null) {
			if (other.startNodes != null)
				return false;
		}
		else if (!startNodes.equals(other.startNodes))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		}
		else if (!uri.equals(other.uri))
			return false;
		if (validity != other.validity)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sample [id=");
		builder.append(id);
		builder.append(", sampleName=");
		builder.append(sampleName);
		builder.append(", algorithmName=");
		builder.append(algorithmName);
		builder.append(", numNodes=");
		builder.append(numNodes);
		builder.append(", startNodes=");
		builder.append(startNodes);
		builder.append(", desiredClusterCount=");
		builder.append(desiredClusterCount);
		builder.append(", clusterCount=");
		builder.append(clusterCount);
		builder.append(", validity=");
		builder.append(validity);
		builder.append(", invalidityReason=");
		builder.append(invalidityReason);
		builder.append(", maxClusterSize=");
		builder.append(maxClusterSize);
		builder.append(", minClusterSize=");
		builder.append(minClusterSize);
		builder.append(", clusterSize=");
		builder.append(clusterSize);
		builder.append(", clusterSpread=");
		builder.append(clusterSpread);
		builder.append(", spreadCurve=");
		builder.append(spreadCurve);
		builder.append(", clusters=");
		builder.append(clusters);
		builder.append(", uri=");
		builder.append(uri);
		builder.append("]");
		return builder.toString();
	}

}
