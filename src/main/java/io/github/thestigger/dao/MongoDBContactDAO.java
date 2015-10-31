package io.github.thestigger.dao;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import io.github.thestigger.converter.ContactConverter;
import io.github.thestigger.model.Contact;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 10/14/15.
 */
public class MongoDBContactDAO {

    private MongoCollection<Document> collection;

    public MongoDBContactDAO(MongoClient mongo) {
        collection = mongo.getDatabase("WorkDiary").getCollection("Contacts");
    }

    public Contact createContact(Contact c) {
        Document doc = ContactConverter.toDocument(c);
        collection.insertOne(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        c.setId(id.toString());

        return c;
    }

    public void updateContact(Contact c) {
        System.out.println(c);
        Document query = new Document()
                .append("_id", new ObjectId(c.getId()));

        collection.replaceOne(query, ContactConverter.toDocument(c));
    }

    public List<Contact> readContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            Contact c = ContactConverter.toContact(doc);
            contacts.add(c);
        }

        return contacts;
    }

    public Contact readContact(Contact c) {
        Document query = new Document()
                .append("_id", new ObjectId(c.getId()));

        Document doc = collection.find(query).first();

        return ContactConverter.toContact(doc);
    }

    public void deleteContact(Contact c) {
        Document query = new Document()
                .append("_id", new ObjectId(c.getId()));

        collection.deleteOne(query);
    }
}
