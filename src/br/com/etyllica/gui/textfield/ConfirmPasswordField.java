package br.com.etyllica.gui.textfield;

import br.com.etyllica.gui.PasswordField;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ConfirmPasswordField extends PasswordField{

	private PasswordField passwordField;
	
	public ConfirmPasswordField(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

}
