package br.com.etyllica.gui.base;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.Label;
import br.com.etyllica.gui.RoundView;
import br.com.etyllica.gui.theme.Theme;

/**
 *
 * Button Component
 * 
 * @author yuripourre
 *
 */

public class BaseButton extends RoundView {

	protected Label label;

	private String alt = "";

	protected boolean clicked = false;
		
	public BaseButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g) {
		
		if(!visible)
			return;

		Theme theme = getTheme();

		if(!disabled) {

			if(!mouseOver) {

				g.setColor(theme.getBaseColor());

			} else {

				if(clicked) {
					g.setColor(theme.getActiveColor());
				} else {
					g.setColor(theme.getSelectionColor());
				}

			}

		} else {
			g.setColor(theme.getButtonDisabledColor());
		}

		g.fillRect(left(),top(),width(),height());

		drawLabel(g);

	}

	protected void drawLabel(Graphics g) {
		if(hasLabel())
			label.draw(g);

	}

	public void updateEvent(GUIEvent event) {
		executeAction(event);
		
		if(hasLabel())
			label.updateEvent(event);
	}
	
	protected void leftClick() {
		//igualaImagem(click);
	}

	protected void leftUp() {

	}

	protected void middleClick() {
		//new Voicer().say(rotulo);
	}

	protected void rightClick() {

	}

	protected void justOnMouse() {
		//igualaImagem(sobMouse);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		GUIEvent value = super.updateMouse(event);
		
		if(value != GUIEvent.NONE) {
			updateEvent(value);
			return value;
		}
		
		if(!disabled) {

			//If mouse is Over
			if(mouseOver) {
				
				if(event.getState() == PointerState.PRESSED) {

					if(event.isKey(MouseEvent.MOUSE_BUTTON_LEFT)) {
						
						clicked = true;

						leftClick();

						value = GUIEvent.MOUSE_LEFT_BUTTON_DOWN;

					} else if(event.isKey(MouseEvent.MOUSE_BUTTON_RIGHT)) {

						rightClick();

						value = GUIEvent.MOUSE_RIGHT_BUTTON_DOWN;

					} else if(event.isKey(MouseEvent.MOUSE_BUTTON_MIDDLE)) {

						middleClick();

						value = GUIEvent.MOUSE_MIDDLE_BUTTON_DOWN;
					}
				}

				else if(event.getState() == PointerState.RELEASED) {

					if(event.isKey(MouseEvent.MOUSE_BUTTON_LEFT)) {

						clicked = false;
						
						leftUp();

						value = GUIEvent.MOUSE_LEFT_BUTTON_UP;

					} else if(event.isKey(MouseEvent.MOUSE_BUTTON_RIGHT)) {

						value = GUIEvent.MOUSE_RIGHT_BUTTON_UP;

					} else if(event.isKey(MouseEvent.MOUSE_BUTTON_MIDDLE)) {

						value = GUIEvent.MOUSE_MIDDLE_BUTTON_UP;

					}

				} else if(event.getState()==PointerState.DOUBLE_CLICK) {

					if(event.isKey(MouseEvent.MOUSE_BUTTON_LEFT)) {

						value = GUIEvent.MOUSE_LEFT_BUTTON_DOUBLE_CLICK;

					} else if(event.isKey(MouseEvent.MOUSE_BUTTON_RIGHT)) {

						value = GUIEvent.MOUSE_RIGHT_BUTTON_DOUBLE_CLICK;

					} else if(event.isKey(MouseEvent.MOUSE_BUTTON_MIDDLE)) {

						value = GUIEvent.MOUSE_MIDDLE_BUTTON_DOUBLE_CLICK;

					}

				} else if(event.getState() == PointerState.MOVE) {

					justOnMouse();

					value = GUIEvent.MOUSE_OVER;

				}

			//If mouse is not over
			} else {

				if(event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {

					onFocus = false;

				}

			}
		}
		
		updateEvent(value);
		return value;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;

		label.setX(x+(w/2-label.getW()/2)+label.getX());
		label.setY(y+(h/2-label.getH()/2)+label.getY());

		//int offsetX = label.getX();
		//int offsetY = label.getY();

		//label.setX(x+offsetX);
		//label.setY(y+offsetY);

		label.setContentBounds(left(), top(), width(), height());
	}

	public void setCenterLabel(Label label) {
		this.label = label;

		label.setX(label.getX()+(left()+width()/2-label.getW()/2));
		label.setY(label.getY()+(top()+height()/2-label.getH()/2));

		label.setContentBounds(left(), top(), width(), height());
	}

	@Override
	public void setX(int x) {
		super.setX(x);

		if(hasLabel()) {
			label.setContentBounds(left(), top(), width(), height());
		}
	}

	@Override
	public void setY(int y) {
		super.setY(y);

		if(hasLabel())
			label.setContentBounds(left(), top(), width(), height());
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.VK_TAB)) {
			return GUIEvent.NEXT_COMPONENT;
		}

		if(event.isKeyDown(KeyEvent.VK_ENTER)) {
			this.updateEvent(GUIEvent.MOUSE_LEFT_BUTTON_DOWN);
		}/*else if(event.getReleased(Tecla.TSK_ENTER)) {

			return GUIEvent.MOUSE_LEFT_BUTTON_UP;

		}*/

		return GUIEvent.NONE;
	}

	protected boolean hasLabel() {
		return label != null; 
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	@Override
	public void resize() {
		super.resize();
		if(hasLabel()) {
			label.resize();	
		}
	}
	
	@Override
	public void setBounds(int x, int y, int w, int h) {
		super.setBounds(x, y, w, h);
		if(hasLabel()) {
			label.setContentBounds(left(), top(), width(), height());
		}
	}

}
