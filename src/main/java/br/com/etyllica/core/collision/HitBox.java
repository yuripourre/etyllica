package br.com.etyllica.core.collision;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.linear.Rectangle;

/**
 * 
 * @author yuripourre
 *
 */

public class HitBox {

	private List<Rectangle> areas = new ArrayList<Rectangle>();
	
	public HitBox() {
		super();
	}

	public List<Rectangle> getAreas() {
		return areas;
	}

	public void setAreas(List<Rectangle> area) {
		this.areas = area;
	}

}
