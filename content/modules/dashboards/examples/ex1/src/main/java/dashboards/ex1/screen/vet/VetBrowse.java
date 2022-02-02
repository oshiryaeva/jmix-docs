package dashboards.ex1.screen.vet;

import io.jmix.ui.screen.*;
import dashboards.ex1.entity.Vet;

@UiController("sample_Vet.browse")
@UiDescriptor("vet-browse.xml")
@LookupComponent("vetsTable")
public class VetBrowse extends StandardLookup<Vet> {
}