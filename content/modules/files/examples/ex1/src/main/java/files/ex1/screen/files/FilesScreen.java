package files.ex1.screen.files;

import io.jmix.core.CoreProperties;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorageLocator;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiProperties;
import io.jmix.ui.component.FileStorageUploadField;
import io.jmix.ui.component.SingleFileUploadField;
import io.jmix.ui.download.ByteArrayDataProvider;
import io.jmix.ui.download.DownloadFormat;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.download.FileDataProvider;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import io.jmix.ui.upload.TemporaryStorage;
import io.jmix.ui.upload.TemporaryStorageManagementFacade;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

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
    @Autowired
    private Downloader downloader;
    @Autowired
    private UiProperties uiProperties;
    @Autowired
    private CoreProperties coreProperties;

    @Autowired
    private FileStorageLocator fileStorageLocator;

    //tag::temporary-storage-2[]
    @Subscribe("fileField")
    public void onFileFieldFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) {
        File file = temporaryStorage.getFile(fileField.getFileId()); // <1>
        if (file != null) {
            FileRef fileRef = temporaryStorage.putFileIntoStorage(fileField.getFileId(), event.getFileName()); // <2>
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

    private void downloadFileWithByteArrayDataProvider() {
        try {
            URLConnection connection = new URL("https://picsum.photos/300").openConnection();
            try (InputStream inputStream = connection.getInputStream()) {
                //tag::byte-array-data-provider[]
                byte[] data = IOUtils.toByteArray(inputStream);
                ByteArrayDataProvider dataProvider = new ByteArrayDataProvider(data,
                        uiProperties.getSaveExportedByteArrayDataThresholdBytes(), // <1>
                        coreProperties.getTempDir()); // <2>
                downloader.download(dataProvider, "fileName");
                //end::byte-array-data-provider[]
            }
        } catch (IOException e) {
            throw new RuntimeException("Error getting image", e);
        }
    }

    private void downloadFileWithFileDataProvider(FileRef fileRef) {
        //tag::file-data-provider[]
        String fileName = fileRef.getFileName();
        downloader.download(new FileDataProvider(fileRef,
                        fileStorageLocator.getByName("additional-storage")), // <1>
                fileName, // <2>
                DownloadFormat.JSON); // <3>
        //end::file-data-provider[]
    }
}