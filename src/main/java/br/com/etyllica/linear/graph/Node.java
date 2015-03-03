package br.com.etyllica.linear.graph;

import br.com.etyllica.linear.Point2D;

public class Node {
	
	private Node parent;
	
	private Point2D point;
	
	public Node() {
		this(0, 0);		
	}
	
	public Node(double x, double y) {
		super();
		point = new Point2D(x, y);
		parent = this;
	}
	
	public Node(Point2D point) {
		super();
		
		this.point = point;		
	}

	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}
	
	public void setLocation(double x, double y) {
		point.setLocation(x, y);
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
		
}
