package examples.ui.material.application;

import java.awt.Font;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.theme.ThemeManager;
import examples.ui.material.application.model.MaterialButton;

public class BlueButtonApplication extends Application {
	
	int bx = 40;
	int by = 130;
	int bw = 140;
	int bh = 50;
	
	MaterialButton button;
	String text = "BUTTON";

	public BlueButtonApplication(int w, int h) {
		super(w,h);
	}

	public void load() {
		ThemeManager.getInstance().getTheme().setShadow(false);
		
		TextLabel label = new TextLabel(text);
		label.setFontStyle(Font.BOLD);
		label.setFontSize(16);
		
		button = new MaterialButton(bx, by, bw, bh);
		button.setLabel(label);
		addView(button);
	}

	@Override
	public void updateMouse(PointerEvent event) {

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}

