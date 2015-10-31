package io.github.thestigger.converter;

import io.github.thestigger.model.Contact;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by maxim on 10/14/15.
 */
public class ContactConverter {

    /**
     * Convert Contact Object to MongoDB Document.
     * @param c Contact Object
     * @return MongoDB Document
     */
    public static Document toDocument(Contact c) {
        Document doc = new Document()
                .append("name", c.getName())
                .append("surname", c.getSurname())
                .append("jobPosition", c.getJobPosition())
                .append("phoneNumber", c.getPhoneNumber())
                .append("email", c.getEmail())
                .append("organizationId", c.getOrganizationId())
                .append("groupId", c.getGroupId());

        if (c.getId() != null) {
            doc.append("_id", new ObjectId(c.getId()));
        }
        return doc;
    }

    /**
     * Convert MongoDB Document to Contact Object.
     * @param doc MongoDB Document
     * @return Contact Object
     */
    public static Contact toContact(Document doc) {
        Contact contact = new Contact();
        contact.setId(((ObjectId) doc.get("_id")).toString());
        contact.setName((String) doc.get("name"));
        contact.setSurname((String) doc.get("surname"));
        contact.setJobPosition((String) doc.get("jobPosition"));
        contact.setPhoneNumber((String) doc.get("phoneNumber"));
        contact.setEmail((String) doc.get("email"));
        contact.setOrganizationId((String) doc.get("organizationId"));
        contact.setGroupId((String) doc.get("groupId"));

        return contact;
    }
}
