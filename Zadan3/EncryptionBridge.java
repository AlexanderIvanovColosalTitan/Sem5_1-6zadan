public class EncryptionBridge {
    private Encryption encryption;

    public EncryptionBridge(Encryption encryption) {
        this.encryption = encryption;
    }

    public String performEncryption(String data) {
        return encryption.encrypt(data);
    }

    public String performDecryption(String data) {
        return encryption.decrypt(data);
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }
}
