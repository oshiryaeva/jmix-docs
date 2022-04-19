package features.ex1.screen.document;

import features.ex1.entity.Document;
import io.jmix.data.Sequence;
import io.jmix.data.Sequences;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Document.edit")
@UiDescriptor("document-edit.xml")
@EditedEntityContainer("documentDc")
public class DocumentEdit extends StandardEditor<Document> {

    //tag::sequences-inject[]
    @Autowired
    private Sequences sequences;
    //end::sequences-inject[]

    //tag::sequences-use[]
    @Autowired
    private InstanceContainer<Document> documentDc;

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onPreCommit(DataContext.PreCommitEvent event) {
        Long number = sequences.createNextValue(Sequence.withName("document_number") // <1>
                .setStore("additional") // <2>
                .setStartValue(10) // <3>
                .setIncrement(10)); // <4>
        documentDc.getItem().setNumber(number);
    }
    //end::sequences-use[]

    private void generateUniqueNumber() {
        //tag::sequences-simple[]
        Long nextNumber = sequences.createNextValue(Sequence.withName("some_seq"));
        //end::sequences-simple[]
    }

}