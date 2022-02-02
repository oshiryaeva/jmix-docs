package dashboards.ex1.screen.widgets;

import io.jmix.dashboards.model.DashboardModel;
import io.jmix.dashboards.model.Widget;
import io.jmix.dashboardsui.annotation.DashboardWidget;
import io.jmix.dashboardsui.annotation.WidgetParam;
import io.jmix.dashboardsui.component.Dashboard;
import io.jmix.ui.WindowParam;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

//tag::logo-widget[]
@UiController("sample_Logo")
@UiDescriptor("logo-widget.xml")
@DashboardWidget(name = "Logo")
public class LogoWidget extends ScreenFragment {
}
//end::logo-widget[]