package br.com.etyllica.gui.base;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.SliderButton;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.theme.Theme;

/**
 * @author yuripourre
 */

public class BaseSlider extends View {

    protected int sliderPosition = 0;

    protected float minValue = 0;
    protected float maxValue = 255;

    protected float value = 0;
    private boolean activated = false;

    protected SliderButton button;

    public BaseSlider(int x, int y, int w, int h) {
        super(x, y, w, h);

        button = new SliderButton(x, y, h / 4, h);
        sliderPosition = getX() - button.getW() / 2;
    }

    public void rebuild() {
        button.rebuild();
        button.setX(sliderPosition);
    }

    @Override
    public GUIEvent updateMouse(PointerEvent event) {
        GUIEvent value = super.updateMouse(event);

        if (mouseOver) {
            if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
                activated = true;
            }
        }

        if (activated) {
            if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
                updateValue(event);
                return GUIEvent.COMPONENT_CHANGED;
            } else if (event.isButtonUp(MouseEvent.MOUSE_BUTTON_LEFT)) {
                activated = false;
            }
        }

        button.setMouseOver(mouseOver);

        return value;
    }

    public void updateValue(PointerEvent event) {
        float interval = maxValue - minValue;
        int mx = event.getX() - getX();
        value = (mx * interval) / w;

        if (value < minValue) {
            value = minValue;
            sliderPosition = getX() - button.getW() / 2;
        } else if (value > maxValue) {
            value = maxValue;
            sliderPosition = getX() + getW() - button.getW() / 2;
        } else {
            sliderPosition = event.getX() - button.getW() / 2;
        }

        button.setX(sliderPosition);
    }

    @Override
    public void updateEvent(GUIEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        if (!visible)
            return;

        //Draw Slide
        Theme theme = getTheme();

        g.setColor(theme.getBarColor());

        int sh = h / 5;
        g.fillRect(x, y + h / 2 - sh / 2, w, sh);

        //Draw Button
        button.draw(g);
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {

        this.value = value;

        float interval = maxValue - minValue;

        float bx = x + ((value * w) / interval);

        button.setX((int)bx - button.getW() / 2);
    }

    @Override
    public GUIEvent updateKeyboard(KeyEvent event) {
        if (onFocus) {
            if (event.isKeyDown(KeyEvent.VK_RIGHT)) {
                setValue(value + 1);
            }
            if (event.isKeyDown(KeyEvent.VK_LEFT)) {
                setValue(value + 1);
            }
        }

        return GUIEvent.NONE;
    }

}

