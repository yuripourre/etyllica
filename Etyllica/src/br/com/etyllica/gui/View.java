package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.etyllica.core.GUIComponent;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class View extends Layer implements GUIComponent{
	
	protected GUIEvent lastEvent = GUIEvent.NONE;
	
	protected boolean onFocus = false;
	protected boolean mouseOver = false;

	protected View root = null;
	
	private List<View> views = new ArrayList<View>();
	
	protected List<Action> actions = new ArrayList<Action>();
	
	//GUIAction's Map
	protected Map<GUIEvent,Action> map = new HashMap<GUIEvent, Action>();
				
	public View(int x, int y) {
		super(x,y,1,1);
	}
	
	public View(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public View(){
		this(0,0);
	}
	
	public GUIEvent getLastEvent() {
		return lastEvent;
	}

	/**
	 * 
	 * @param lastEvent
	 */
	public void setLastEvent(GUIEvent lastEvent) {
		this.lastEvent = lastEvent;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}
	
	/**
	 * 
	 * @param mouseOver
	 */
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isOnFocus() {
		return onFocus;
	}

	/**
	 * 
	 * @param focus
	 */
	public void setOnFocus(boolean focus) {
		this.onFocus = focus;
	}

	public List<Action> getActions() {
		return actions;
	}

	/**
	 * 
	 * @param actions
	 */
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<View> getComponents() {
		return views;
	}

	public void clearComponents(){
		this.views.clear();
	}
	
	/**
	 * 
	 * @param component
	 */
	public void remove(View component){
		this.views.remove(component);
	}
	
	/**
	 * 
	 * @param components
	 */
	public void removeAll(Collection<? extends View> components){
		this.views.removeAll(components);
	}
	
	/**
	 * @param component
	 */
	public void add(View component) {
		component.setRoot(this);
		this.views.add(component);
	}
	
	/**
	 * 
	 * @param components
	 */
	public void addAll(Collection<? extends View> components) {
		
		for(View component: components)	{
			add(component);
		}
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translateComponents(int x, int y){
		for(View component: views){
			translateComponent(x, y, component);
		}
	}
	
	private void translateComponent(int x, int y, View component){
		
		component.setOffset(x, y);
		
		for(View child: component.views){
			translateComponent(x, y, child);
		}
		
	}	


	/**
	 * Method to execute component's associated actions
	 * 
	 * @param event
	 */	
	public void executeAction(GUIEvent event){

		if(map.containsKey(event)){
			map.get(event).executeAction();
		}
		
	}
	
	/**
	 * 
	 * @param event
	 * @param action
	 */
	public void addAction(GUIEvent event, Action action){
		map.put(event, action);
	}
	
	protected void setRoot(View root){
		this.root = root;
	}
	
	public View findNext(){
						
		if(root!=null){
			
			Iterator<View> it = root.getComponents().iterator();

	        while(it.hasNext()){
	        	
	        	if(this.equals(it.next())){
	        		
	        		if(it.hasNext()){
	        			return it.next();
	        		}
	        	}
	        	
	        }
			
		}
		
		return null;
		
	}
	
}