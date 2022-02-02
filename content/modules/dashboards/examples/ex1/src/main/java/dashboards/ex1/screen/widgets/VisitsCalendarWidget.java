package dashboards.ex1.screen.widgets;

import dashboards.ex1.entity.Visit;
import io.jmix.dashboardsui.annotation.DashboardWidget;
import io.jmix.ui.component.Calendar;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.calendar.CalendarEvent;
import io.jmix.ui.component.calendar.ContainerCalendarEventProvider;
import io.jmix.ui.component.calendar.SimpleCalendarEvent;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.DataLoader;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Consumer;

@UiController("sample_VisitsCalendarWidget")
@UiDescriptor("visits-calendar-widget.xml")
@DashboardWidget(name = "Visits Calendar")
public class VisitsCalendarWidget extends ScreenFragment {

    @Autowired
    private DataLoader visitsDl;

    public void reloadSchedule(){
        visitsDl.load();
    }
}