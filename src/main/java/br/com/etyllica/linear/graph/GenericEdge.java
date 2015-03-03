package br.com.etyllica.linear.graph;

public class GenericEdge <N extends Node> {

	protected N origin;
	
	protected N destination;
	
	public GenericEdge(N origin, N destination) {
		super();
		
		this.origin = origin;
		this.destination = destination;
	}

	public N getOrigin() {
		return origin;
	}

	public void setOrigin(N origin) {
		this.origin = origin;
	}

	public N getDestination() {
		return destination;
	}

	public void setDestination(N destination) {
		this.destination = destination;
	}
		
}