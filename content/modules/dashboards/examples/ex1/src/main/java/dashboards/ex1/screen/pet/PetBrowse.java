package dashboards.ex1.screen.pet;

import io.jmix.ui.screen.*;
import dashboards.ex1.entity.Pet;

@UiController("sample_Pet.browse")
@UiDescriptor("pet-browse.xml")
@LookupComponent("petsTable")
public class PetBrowse extends StandardLookup<Pet> {
}