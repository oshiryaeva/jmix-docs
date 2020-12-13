package sample.screen.component.valuePicker;

import io.jmix.ui.Actions;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.action.valuepicker.ValueClearAction;
import io.jmix.ui.component.ValuePicker;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.screen.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("valuePicker-screen")
@UiDescriptor("valuePicker-screen.xml")
public class ValuePickerScreen extends Screen {

    @Autowired
    protected Notifications notifications;
    @Autowired
    protected ValuePicker<String> valuePicker;
    // tag::add-clear-action[]
    @Autowired
    protected ValuePicker<String> loginValuePicker;
    @Autowired
    protected Actions actions;

    @Subscribe
    protected void onInit(InitEvent event) {
    // end::add-clear-action[]
        loginValuePicker.addAction(new BaseAction("showValue")
                .withHandler(actionPerformedEvent -> {
                    String value = loginValuePicker.getValue();

                    notifications.create()
                            .withCaption(value != null ? value : "No value")
                            .show();
                })
                .withDescription("Show Value")
                .withIcon(JmixIcon.EYE.source()));
    // tag::add-clear-action1[]
        loginValuePicker.addAction(actions.create(ValueClearAction.ID));
    }
    // end::add-clear-action1[]

    @Subscribe("valuePicker.generate")
    protected void onValuePickerGenerate(Action.ActionPerformedEvent event) {
        valuePicker.setValue(RandomStringUtils.randomAlphabetic(5, 10));
    }
    // tag::custom-action-handler[]
    @Subscribe("loginValuePicker.generate") // <1>
    protected void onLoginValuePickerGenerate(Action.ActionPerformedEvent event) {
        loginValuePicker.setValue(RandomStringUtils.randomAlphabetic(5, 10));
    }
    // end::custom-action-handler[]
}