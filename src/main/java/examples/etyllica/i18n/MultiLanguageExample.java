package examples.etyllica.i18n;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;

public class MultiLanguageExample extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public MultiLanguageExample() {
		super(800, 600);
	}

	@Override
	public Application startApplication() {

		return new MultiLanguageApplication(w,h);
	}

}
