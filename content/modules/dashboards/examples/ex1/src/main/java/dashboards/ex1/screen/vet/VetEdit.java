package dashboards.ex1.screen.vet;

import io.jmix.ui.screen.*;
import dashboards.ex1.entity.Vet;

@UiController("sample_Vet.edit")
@UiDescriptor("vet-edit.xml")
@EditedEntityContainer("vetDc")
public class VetEdit extends StandardEditor<Vet> {
}