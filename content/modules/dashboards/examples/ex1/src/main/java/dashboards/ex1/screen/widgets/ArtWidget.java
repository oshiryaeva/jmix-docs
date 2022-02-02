package dashboards.ex1.screen.widgets;

import dashboards.ex1.entity.Pet;
import io.jmix.core.DataManager;
import io.jmix.dashboards.model.Widget;
import io.jmix.dashboardsui.annotation.DashboardWidget;
import io.jmix.dashboardsui.annotation.WidgetParam;
import io.jmix.ui.WindowParam;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sample_ArtWidget")
@UiDescriptor("art-widget.xml")
@DashboardWidget(name = "Pokemon Art")
public class ArtWidget extends ScreenFragment {

    public static final String INIT_POKEMON="Cubone";

    @Autowired
    private InstanceContainer<Pet> petDc;

    @WindowParam
    @WidgetParam
    protected Pet pet;

    @WindowParam
    protected Widget widget;

    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onInit(InitEvent event) {
        if (pet == null || pet.getPicture() == null) {
            pet = dataManager.loadValue("select s from sample_Pet s where " +
                            "s.name = :name", Pet.class)
                    .parameter("name", INIT_POKEMON)
                    .one();
        }
        petDc.setItem(pet);
        widget.setCaption(pet.getName());
    }

}