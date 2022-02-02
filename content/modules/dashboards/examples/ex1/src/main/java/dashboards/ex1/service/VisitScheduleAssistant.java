package dashboards.ex1.service;

import dashboards.ex1.screen.widgets.VisitsCalendarWidget;
import io.jmix.dashboardsui.component.Dashboard;
import io.jmix.dashboardsui.dashboard.assistant.DashboardViewAssistant;
import io.jmix.dashboardsui.event.DashboardUpdatedEvent;
import io.jmix.ui.screen.ScreenFragment;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("sample_VisitScheduleAssistant")
@Scope("prototype")
public class VisitScheduleAssistant implements DashboardViewAssistant {

    protected Dashboard dashboard;

    @Override
    public void init(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @EventListener
    public void dashboardEventListener(DashboardUpdatedEvent event){
        ScreenFragment widget = dashboard.getWidget("visits-calendar");
        if (widget instanceof VisitsCalendarWidget){
            VisitsCalendarWidget visitsCalendarWidget = (VisitsCalendarWidget) widget;
            visitsCalendarWidget.reloadSchedule();
        }
    }

}