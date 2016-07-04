package br.com.etyllica.gui;

import br.com.etyllica.gui.base.BaseTextView;
import br.com.etyllica.gui.base.UIView;
import br.com.etyllica.theme.ThemeManager;

public class TextView extends UIView {
	
	private BaseTextView textView;
	
	public TextView(int x, int y, int w, int h) {
		super();
		
		this.textView = ThemeManager.getInstance().getTheme().createTextView(x, y, w, h);
		this.delegatedView = textView;
	}
	
	public String getText() {
		return textView.getText();
	}

	public void setText(String text) {
		textView.setText(text);
	}

}
