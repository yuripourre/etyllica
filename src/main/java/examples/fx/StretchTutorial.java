package examples.fx;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.fx.application.StretchApplication;

public class StretchTutorial extends Etyllica {

    private static final long serialVersionUID = 1L;

    public StretchTutorial() {
        super(800, 600);
    }

    public static void main(String[] args) {
        StretchTutorial app = new StretchTutorial();
        app.init();
    }

    @Override
    public Application startApplication() {
        //initialSetup("../../");
        return new StretchApplication(w, h);
    }

}
