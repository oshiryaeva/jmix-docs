package files.ex1.screen.files;

import io.jmix.core.FileRef;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.FileStorageUploadField;
import io.jmix.ui.component.SingleFileUploadField;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import io.jmix.ui.upload.TemporaryStorage;
import io.jmix.ui.upload.TemporaryStorageManagementFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@UiController("sample_FilesScreen")
@UiDescriptor("files-screen.xml")
public class FilesScreen extends Screen {


    //tag::temporary-storage-1[]
    @Autowired
    private FileStorageUploadField fileField;
    @Autowired
    private TemporaryStorage temporaryStorage;

    //end::temporary-storage-1[]

    //tag::temporary-storage-clean-1[]
    @Autowired
    private TemporaryStorageManagementFacade storageFacade;

    //end::temporary-storage-clean-1[]

    @Autowired
    private Notifications notifications;

    //tag::temporary-storage-2[]
    @Subscribe("fileField")
    public void onFileFieldFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) {
        File file = temporaryStorage.getFile(fileField.getFileId()); // <1>
        if (file != null) {
            FileRef fileRef = temporaryStorage.putFileIntoStorage(fileField.getFileId(), event.getFileName());
            fileField.setValue(fileRef);
        }
    }
    //end::temporary-storage-2[]


    private void processAndDeleteFile() throws FileNotFoundException {
        //tag::get-and-delete[]
        File file = temporaryStorage.getFile(fileField.getFileId());
        if (file != null) {
            FileOutputStream outputStream = new FileOutputStream(file);
            processFile(outputStream);
            temporaryStorage.deleteFile(fileField.getFileId());
        }
        //end::get-and-delete[]

        //tag::temporary-storage-clean-2[]
        storageFacade.clearTempDirectory();
        //end::temporary-storage-clean-2[]
    }

    private void processFile(FileOutputStream stream) {

    }
}