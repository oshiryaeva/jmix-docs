package dashboards.ex1.screen.widgets;

import io.jmix.dashboardsui.annotation.DashboardWidget;
import io.jmix.dashboardsui.event.DashboardEvent;
import io.jmix.dashboardsui.widget.RefreshableWidget;
import io.jmix.ui.component.TextArea;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

//tag::joke-widget[]
@UiController("sample_RandomJokeWidget")
@UiDescriptor("random-joke-widget.xml")
@DashboardWidget(name = "Random Joke")
public class RandomJokeWidget extends ScreenFragment implements RefreshableWidget {

    @Autowired
    private TextArea<String> randomJoke;

    @Subscribe
    private void onAfterInit(AfterInitEvent event) {
        randomJoke.setValue(getNewJoke());
    }

    @Override
    public void refresh(DashboardEvent dashboardEvent) {
        randomJoke.setValue(getNewJoke());
    }

    private String getNewJoke() {
        String host = "icanhazdadjoke.com";
        String url = "https://" + host;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "text/plain");
        headers.set("User-Agent", "CubaDashboardDemoApp");
        headers.set("Cache-Control", "no-cache");
        headers.set("Host", host);
        HttpEntity<String> request = new HttpEntity<>("", headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return response.getBody();

    }
}
//end::joke-widget[]