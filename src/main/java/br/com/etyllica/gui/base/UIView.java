package br.com.etyllica.gui.base;

import java.util.Collection;
import java.util.List;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.View;

public abstract class UIView extends View {

	private View delegatedView;
	
	public UIView() {
		super();
	}
	
	@Override
	public int getX() {
		return delegatedView.getX();
	}

	@Override
	public int getY() {
		return delegatedView.getY();
	}

	@Override
	public int getW() {
		return delegatedView.getW();
	}

	@Override
	public int getH() {
		return delegatedView.getH();
	}
	
	@Override
	public void draw(Graphics g) {
		delegatedView.draw(g);
	}

	@Override
	public GUIEvent getLastEvent() {
		return delegatedView.getLastEvent();
	}

	@Override
	public boolean isMouseOver() {
		return delegatedView.isMouseOver();
	}
	
	@Override
	public boolean isDisabled() {
		return delegatedView.isDisabled();
	}

	@Override
	public void setDisabled(boolean disabled) {
		delegatedView.setDisabled(disabled);
	}

	@Override
	public boolean isVisible() {
		return delegatedView.isVisible();
	}

	public boolean isOnFocus() {
		return delegatedView.isOnFocus();
	}
	
	@Override
	public List<Action> getActions() {
		return delegatedView.getActions();
	}

	@Override
	public void hide() {
		delegatedView.hide();
	}

	@Override
	public List<View> getViews() {
		return delegatedView.getViews();
	}

	@Override
	public boolean colideRect(int bx, int by, int bw, int bh) {
		return delegatedView.colideRect(bx, by, bw, bh);
	}

	@Override
	public void clearComponents() {
		delegatedView.clearComponents();
	}

	@Override
	public boolean colideCircleCircle(int bx, int by, int bw, int bh) {
		return delegatedView.colideCircleCircle(bx, by, bw, bh);
	}

	@Override
	public void add(View component) {
		delegatedView.add(component);
	}

	@Override
	public void addAll(Collection<? extends View> components) {
		delegatedView.addAll(components);
	}

	@Override
	public boolean colideCirclePoint(int px, int py) {
		return delegatedView.colideCirclePoint(px, py);
	}

	@Override
	public boolean onMouse(PointerEvent event) {
		return delegatedView.onMouse(event);
	}

	@Override
	public boolean onMouse(int mx, int my) {
		return delegatedView.onMouse(mx, my);
	}

	@Override
	public void executeAction(GUIEvent event) {
		delegatedView.executeAction(event);
	}

	@Override
	public void addAction(GUIEvent event, Action action) {
		delegatedView.addAction(event, action);
	}

	@Override
	public boolean equals(Object obj) {
		return delegatedView.equals(obj);
	}

	@Override
	public View findNext() {
		return delegatedView.findNext();
	}
	
	@Override
	public int hashCode() {
		return delegatedView.hashCode();
	}
	
	@Override
	public void setX(int x) {
		delegatedView.setX(x);
	}

	@Override
	public void setY(int y) {
		delegatedView.setY(y);
	}

	@Override
	public void setW(int w) {
		delegatedView.setW(w);
	}

	@Override
	public void setH(int h) {
		delegatedView.setH(h);
	}

	@Override
	public void setBounds(int x, int y, int w, int h) {
		delegatedView.setBounds(x, y, w, h);
	}

	@Override
	public void setCoordinates(int x, int y) {
		delegatedView.setCoordinates(x, y);
	}

	@Override
	public void setOffsetX(int offsetX) {
		delegatedView.setOffsetX(offsetX);
	}

	@Override
	public void setOffsetY(int offsetY) {
		delegatedView.setOffsetY(offsetY);
	}

	@Override
	public void setLastEvent(GUIEvent lastEvent) {
		delegatedView.setLastEvent(lastEvent);
	}

	@Override
	public void update(GUIEvent event) {
		delegatedView.update(event);
	}

	@Override
	public void setLocation(int offsetX, int offsetY) {
		delegatedView.setLocation(offsetX, offsetY);
	}

	@Override
	public void setVisible(boolean visible) {
		delegatedView.setVisible(visible);
	}

	@Override
	public void setMouseOver(boolean mouseOver) {
		delegatedView.setMouseOver(mouseOver);
	}

	@Override
	public void show() {
		delegatedView.show();
	}

	@Override
	public void setOnFocus(boolean focus) {
		delegatedView.setOnFocus(focus);
	}

	@Override
	public void setActions(List<Action> actions) {
		delegatedView.setActions(actions);
	}

	@Override
	public void swapVisible() {
		delegatedView.swapVisible();
	}

	@Override
	public void remove(View component) {
		delegatedView.remove(component);
	}

	@Override
	public void removeAll(Collection<? extends View> components) {
		delegatedView.removeAll(components);
	}

	@Override
	public void translateComponents(int x, int y) {
		delegatedView.translateComponents(x, y);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return delegatedView.updateMouse(event);
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return delegatedView.updateKeyboard(event);
	}
	
	@Override
	public String toString() {
		return delegatedView.toString();
	}
	
	protected void delegateView(View view) {
		this.delegatedView = view;
		this.style = view.style;
	}
}
