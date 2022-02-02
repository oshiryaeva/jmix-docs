package dashboards.ex1.screen.owner;

import io.jmix.ui.screen.*;
import dashboards.ex1.entity.Owner;

@UiController("sample_Owner.edit")
@UiDescriptor("owner-edit.xml")
@EditedEntityContainer("ownerDc")
public class OwnerEdit extends StandardEditor<Owner> {
}