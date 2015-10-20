//Name: Zameer Masjedee
//Student Number: 100888893
//Date: October 19,2015
//Version #: 1

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zameer on 2015-10-19.
 */
public class Database {

    private List<Document> documents;
    private int quantity;

    public Database() {
        documents = new ArrayList<Document>();
        quantity = 0;
    }

    private void addDocument(Document doc) {
        getDocuments().add(doc);
    }

    public List<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
