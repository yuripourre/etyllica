package br.com.etyllica.linear.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.etyllica.linear.Point2D;
import br.com.etyllica.linear.graph.Edge;
import br.com.etyllica.linear.graph.Node;

public class GenericComplexGraph<E extends Edge> {

	protected List<Node> nodes;
	
	protected Map<Node, List<E>> edges;
	
	public GenericComplexGraph() {
		super();
		
		nodes = new ArrayList<Node>();
		
		edges = new HashMap<Node, List<E>>();
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}
	
	public void addNode(Point2D point) {
		this.nodes.add(new Node(point.getX(), point.getY()));
	}

	public Map<Node, List<E>> getAllEdges() {
		return edges;
	}
	
	public List<E> getEdges(Node node) {
		
		if(edges.containsKey(node)) {
			return edges.get(node);
		}
		
		return new ArrayList<E>();
	}
	
	public void addEdge(E edge) {
		
		Node origin = edge.getOrigin();
		
		if(!edges.containsKey(origin)) {
			edges.put(origin, new ArrayList<E>());
		}
		
		edges.get(origin).add(0, edge);		
	}
			
	public void clear() {
		nodes.clear();
		edges.clear();
	}
	
}
