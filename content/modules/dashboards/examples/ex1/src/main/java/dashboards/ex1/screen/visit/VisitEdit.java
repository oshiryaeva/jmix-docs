package dashboards.ex1.screen.visit;

import io.jmix.ui.screen.*;
import dashboards.ex1.entity.Visit;

@UiController("sample_Visit.edit")
@UiDescriptor("visit-edit.xml")
@EditedEntityContainer("visitDc")
public class VisitEdit extends StandardEditor<Visit> {
}