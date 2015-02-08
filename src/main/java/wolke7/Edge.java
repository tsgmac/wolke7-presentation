package wolke7;

/**
 * 
 * @author	Thomas Schoenfeld
 * @date		2015-02-08
 *
 * Die Klasse Edge beschreibt eine Kante.
 */
public class Edge {

	private String	edgeId;
	private Node		fromNode;
	private Node		toNode;

	/** 
	 * Standard-Konstruktor der Klasse Edge.
	 */
	public Edge() {}
	
	/**
	 * Konstruktor der Klasse Edge.
	 * 
	 * @param edgeId
	 * @param fromNode
	 * @param toNode
	 */
	public Edge(String edgeId, Node fromNode, Node toNode) {
		super();
		this.edgeId = edgeId;
		this.fromNode = fromNode;
		this.toNode = toNode;
	}

	/**
	 * @return the edgeId
	 */
	public String getEdgeId() {
		return edgeId;
	}

	/**
	 * @param edgeId
	 *          the edgeId to set
	 */
	public void setEdgeId(String edgeId) {
		this.edgeId = edgeId;
	}

	/**
	 * @return the fromNode
	 */
	public Node getFromNode() {
		return fromNode;
	}

	/**
	 * @param fromNode
	 *          the fromNode to set
	 */
	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	/**
	 * @return the toNode
	 */
	public Node getToNode() {
		return toNode;
	}

	/**
	 * @param toNode
	 *          the toNode to set
	 */
	public void setToNode(Node toNode) {
		this.toNode = toNode;
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
		result = prime * result + ((edgeId == null) ? 0 : edgeId.hashCode());
		result = prime * result + ((fromNode == null) ? 0 : fromNode.hashCode());
		result = prime * result + ((toNode == null) ? 0 : toNode.hashCode());
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
		if (!(obj instanceof Edge))
			return false;
		Edge other = (Edge) obj;
		if (edgeId == null) {
			if (other.edgeId != null)
				return false;
		}
		else if (!edgeId.equals(other.edgeId))
			return false;
		if (fromNode == null) {
			if (other.fromNode != null)
				return false;
		}
		else if (!fromNode.equals(other.fromNode))
			return false;
		if (toNode == null) {
			if (other.toNode != null)
				return false;
		}
		else if (!toNode.equals(other.toNode))
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
		builder.append("Edge [edgeId=");
		builder.append(edgeId);
		builder.append(", fromNode=");
		builder.append(fromNode);
		builder.append(", toNode=");
		builder.append(toNode);
		builder.append("]");
		return builder.toString();
	}

}
