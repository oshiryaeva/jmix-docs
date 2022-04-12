package ui.ex1.widgets.client;

import com.google.gwt.dom.client.Style;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;
import io.jmix.ui.widget.client.button.JmixButtonConnector;
import ui.ex1.widgets.ColorButton;

// tag::color-button-connector[]
@Connect(ColorButton.class)
public class ColorButtonConnector extends JmixButtonConnector {

    @Override
    public ColorButtonState getState() {
        return (ColorButtonState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        if (event.hasPropertyChanged("color")) {
            Style style = getWidget().getElement().getStyle();
            style.setBackgroundColor(getState().color);
            style.setBackgroundImage("none");
        }
    }
}
// end::color-button-connector[]