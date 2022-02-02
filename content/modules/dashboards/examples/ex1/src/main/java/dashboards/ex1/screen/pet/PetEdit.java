package dashboards.ex1.screen.pet;

import io.jmix.ui.component.FileStorageUploadField;
import io.jmix.ui.screen.*;
import dashboards.ex1.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sample_Pet.edit")
@UiDescriptor("pet-edit.xml")
@EditedEntityContainer("petDc")
public class PetEdit extends StandardEditor<Pet> {
    @Autowired
    private FileStorageUploadField pictureField;
}