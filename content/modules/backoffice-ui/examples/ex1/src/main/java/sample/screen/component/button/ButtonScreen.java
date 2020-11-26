package sample.screen.component.button;

import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("button-screen")
@UiDescriptor("button-screen.xml")
public class ButtonScreen extends Screen {

    @Autowired
    protected Notifications notifications;

    // tag::click-handler[]
    @Subscribe("helloButton") // <1>
    protected void onHelloButtonClick(Button.ClickEvent event) {
        notifications.create()
                .withCaption("Hello, world!")
                .show();
    }
    // end::click-handler[]

    @Subscribe("saveButton1")
    protected void onSaveButton1Click(Button.ClickEvent event) {
        save(event.getSource().getId());
    }

    @Subscribe("saveButton2")
    protected void onSaveButton2Click(Button.ClickEvent event) {
        save(event.getSource().getId());
    }

    public void save(String id) {
        notifications.create()
                .withCaption("Save called from " + id)
                .show();
    }
}