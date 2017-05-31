package br.com.etyllica.core;

import br.com.etyllica.core.context.Context;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;

public interface Module extends Drawable, Updatable {

    /**
     * Update pointer events
     *
     * @param event
     * @return
     */
    void updateMouse(PointerEvent event);

    /**
     * Update keyboard events
     *
     * @param event
     * @return
     */
    void updateKeyboard(KeyEvent event);

    void updateGuiEvent(GUIEvent event);

    /**
     * Handles the context
     *
     * @param context
     * @return
     */
    void init(Context context);

    void dispose(Context context);

}
