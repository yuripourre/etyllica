package examples.etyllica.tutorial10;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import examples.etyllica.tutorial10.application.SpinnerExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial10 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial10() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		return new SpinnerExample(w,h);
	}
	
}