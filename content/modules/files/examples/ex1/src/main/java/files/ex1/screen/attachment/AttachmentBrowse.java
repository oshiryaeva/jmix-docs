package files.ex1.screen.attachment;

import files.ex1.entity.Attachment;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.core.FileStorageLocator;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.*;
import io.jmix.ui.download.DownloadFormat;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@UiController("Attachment.browse")
@UiDescriptor("attachment-browse.xml")
@LookupComponent("attachmentsTable")
public class AttachmentBrowse extends StandardLookup<Attachment> {

    // tag::files[]
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader; // <1>

    @Install(to = "attachmentsTable.fileName", subject = "columnGenerator")
    private Component attachmentsTableFileColumnGenerator(Attachment attachment) {
        if (attachment.getFile() != null) {
            LinkButton linkButton = uiComponents.create(LinkButton.class);
            linkButton.setAction(new BaseAction("download")
                    .withCaption(attachment.getFile().getFileName())
                    .withHandler(actionPerformedEvent ->
                            downloader.download(attachment.getFile()) // <2>
                    )
            );
            return linkButton;
        } else {
            return new Table.PlainTextCell("<empty>");
        }
    }
    // end::files[]

    @Subscribe("getExternalImageBtn")
    public void onGetExternalImageClick(Button.ClickEvent event) {
        getAndSaveImage();
    }

    @Autowired
    private GroupTable<Attachment> attachmentsTable;

    @Subscribe("saveImageBtn")
    public void onSaveImageBtnClick(Button.ClickEvent event) {
        Attachment attachment = attachmentsTable.getSingleSelected();
        if (attachment == null)
            return;

        saveToLocalFile(attachment, Paths.get("."));
    }

    // tag::file-storage-1[]
    @Autowired
    private FileStorageLocator fileStorageLocator; // <1>
    @Autowired
    private DataManager dataManager;

    private void getAndSaveImage() {
        try {
            // <2>
            URLConnection connection = new URL("https://picsum.photos/300").openConnection();
            try (InputStream responseStream = connection.getInputStream()) {
                // <3>
                FileStorage fileStorage = fileStorageLocator.getDefault();
                FileRef fileRef = fileStorage.saveStream("photo.jpg", responseStream);
                // <4>
                Attachment attachment = dataManager.create(Attachment.class);
                attachment.setFile(fileRef);
                dataManager.save(attachment);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error getting image", e);
        }
    }
    // end::file-storage-1[]

    // tag::file-storage-2[]
    private void saveToLocalFile(Attachment attachment, Path path) {
        FileStorage fileStorage = fileStorageLocator.getDefault();
        FileRef fileRef = attachment.getFile();
        // <1>
        InputStream inputStream = fileStorage.openStream(fileRef);
        try {
            // <2>
            Files.copy(inputStream, path.resolve(fileRef.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error saving image", e);
        }
    }
    // end::file-storage-2[]


    private void openAttachmentInNewWindow(Attachment attachment) {
        // tag::show-file[]
        downloader.setShowNewWindow(true);
        downloader.download(attachment.getFile());
        // end::show-file[]
    }

    private void downloadAttachmentWithCustomFormat(Attachment attachment) {
        // tag::download-format[]
        DownloadFormat txtFormat = new DownloadFormat("txt-mime-type", "txt");
        downloader.download(attachment.getFile(), txtFormat);
        // end::download-format[]
    }

    private void downloadAttachmentByUrl() {
        // tag::download-url[]
        try {
            URLConnection connection = new URL("https://picsum.photos/300").openConnection();
            try (InputStream responseStream = connection.getInputStream()) {
                byte[] fileArray = IOUtils.toByteArray(responseStream);
                downloader.download(fileArray, "photo.jpg", DownloadFormat.JPG);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error getting image", e);
        }
        // end::download-url[]
    }

    private void downloadFileAsByteArray() {
        // tag::download-array[]
        byte[] note = "A very small text note".getBytes();
        downloader.download(note, "note", DownloadFormat.TEXT);
        // end::download-array[]
    }
}