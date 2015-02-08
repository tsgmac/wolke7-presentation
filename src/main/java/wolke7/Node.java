package wolke7;

/**
 * 
 * @author Thomas Schoenfeld
 * @date 2015-02-08
 *
 *       Die Klasse Node beschreibt einen Knoten.
 */
public class Node {

	private String	nodeId;

	/**
	 * Standard-Konstruktor der Klasse Node.
	 */
	public Node() {}

	/**
	 * Konstruktor der Klasse Node.
	 * 
	 * @param nodeId
	 */
	public Node(String nodeId) {
		super();
		this.nodeId = nodeId;
	}

	/**
	 * 
	 * @return nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * 
	 * @param nodeId
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
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
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
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
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		}
		else if (!nodeId.equals(other.nodeId))
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
		builder.append("Node [nodeId=");
		builder.append(nodeId);
		builder.append("]");
		return builder.toString();
	}

}
