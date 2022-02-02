package dashboards.ex1.screen.owner;

import io.jmix.ui.screen.*;
import dashboards.ex1.entity.Owner;

@UiController("sample_Owner.browse")
@UiDescriptor("owner-browse.xml")
@LookupComponent("ownersTable")
public class OwnerBrowse extends StandardLookup<Owner> {
}