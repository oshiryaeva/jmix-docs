package files.ex1.screen.attachment;

import files.ex1.entity.Attachment;
import io.jmix.core.FileRef;
import io.jmix.ui.component.BrowserFrame;
import io.jmix.ui.component.FileStorageResource;
import io.jmix.ui.component.SingleFileUploadField;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Attachment.edit")
@UiDescriptor("attachment-edit.xml")
@EditedEntityContainer("attachmentDc")
public class AttachmentEdit extends StandardEditor<Attachment> {

    @Autowired
    private BrowserFrame browserFrame;
    @Autowired
    private InstanceContainer<Attachment> attachmentDc;

    @Subscribe("fileField")
    public void onFileFieldFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) {
        FileRef fileRef = attachmentDc.getItem().getFile();
        //tag::browser-frame-mime-type[]
        browserFrame.setSource(FileStorageResource.class)
                .setFileReference(fileRef)
                .setMimeType("text/plain");
        //end::browser-frame-mime-type[]
    }

    private void showFrameWithType() {
        //tag::browser-frame[]
        FileRef fileRef = attachmentDc.getItem().getFile();
        browserFrame.setSource(FileStorageResource.class)
                .setFileReference(fileRef);
        //end::browser-frame[]
    }

}