package br.com.etyllica.gui;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseTextView;
import br.com.etyllica.gui.base.UIView;
import br.com.etyllica.gui.theme.ThemeManager;

public class TextView extends UIView {

	private BaseTextView textView;
	
	public TextView(int x, int y, int w, int h) {
		super(x,y,w,h);

		this.textView = ThemeManager.getInstance().getTheme().createTextView(x, y, w, h);
		delegateView(textView);
	}

	public TextView(String text) {
		this(0,0,0,0);
		setText(text);
	}

	public void rebuild() {
		BaseTextView view = ThemeManager.getInstance().getTheme().createTextView(x, y, w, h);
		view.copy(delegatedView);

		delegateView(view);
	}

	public String getText() {
		return textView.getText();
	}

	public void setText(String text) {
		textView.setText(text);
	}

}
