package wolke7;

import java.util.List;

/**
 * 
 * @author	Thomas Schoenfeld
 * @date		2015-02-08
 * 
 * Ein Cluster setzt sich aus Knoten und Kanten zusammen.
 *
 */
public class Cluster {

	private List<Node>	nodes;
	private List<Edge>	edges;

	/**
	 * Standard-Konstruktor der Klasse Cluster
	 */
	public Cluster() {}
	
	/**
	 * Konstruktor der Klasse Cluster
	 * 
	 * @param nodes
	 * @param edges
	 */
	public Cluster(List<Node> nodes, List<Edge> edges) {
		super();
		this.nodes = nodes;
		this.edges = edges;
	}

	/**
	 * @return the nodes
	 */
	public List<Node> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes
	 *          the nodes to set
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the edges
	 */
	public List<Edge> getEdges() {
		return edges;
	}

	/**
	 * @param edges
	 *          the edges to set
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
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
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
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
		if (!(obj instanceof Cluster))
			return false;
		Cluster other = (Cluster) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		}
		else if (!edges.equals(other.edges))
			return false;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		}
		else if (!nodes.equals(other.nodes))
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
		builder.append("Cluster [nodes=");
		builder.append(nodes);
		builder.append(", edges=");
		builder.append(edges);
		builder.append("]");
		return builder.toString();
	}

}
