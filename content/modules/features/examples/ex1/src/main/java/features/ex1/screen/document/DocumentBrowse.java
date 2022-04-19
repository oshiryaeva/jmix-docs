package features.ex1.screen.document;

import io.jmix.ui.screen.*;
import features.ex1.entity.Document;

@UiController("Document.browse")
@UiDescriptor("document-browse.xml")
@LookupComponent("documentsTable")
public class DocumentBrowse extends StandardLookup<Document> {
}