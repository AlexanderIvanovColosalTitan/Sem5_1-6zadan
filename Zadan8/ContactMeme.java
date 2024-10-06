import java.util.List;
public class ContactMeme {
    private final List<Contact> contacts;

    public ContactMeme(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}