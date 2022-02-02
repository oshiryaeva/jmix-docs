package dashboards.ex1.screen.visit;

import io.jmix.ui.screen.*;
import dashboards.ex1.entity.Visit;

@UiController("sample_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
public class VisitBrowse extends StandardLookup<Visit> {
}