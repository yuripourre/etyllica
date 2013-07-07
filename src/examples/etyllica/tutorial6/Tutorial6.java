package examples.etyllica.tutorial6;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial6.application.SubWindowExample;

public class Tutorial6 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial6() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new SubWindowExample(w,h));
	}
	
}
