import java.util.ArrayList;
import java.util.List;
public class ContactManager {
    private ContactList contactList;
    private List<ContactMeme> mementoList;

    public ContactManager() {
        this.contactList = new ContactList();
        this.mementoList = new ArrayList<>();
    }

    public void addContact(String name, String phoneNumber) {
        contactList.addContact(new Contact(name, phoneNumber));
    }

    public void save() {
        mementoList.add(contactList.save());
    }

    public void restore() {
        if (!mementoList.isEmpty()) {
            contactList.restore(mementoList.remove(mementoList.size() - 1));
        }
    }

    public void clearContacts() {
        contactList.getContacts().clear();
    }

    public List<Contact> getContacts() {
        return contactList.getContacts();
    }
}
