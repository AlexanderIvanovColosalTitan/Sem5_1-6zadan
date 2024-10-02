public abstract class DataEncryptor {
    protected EncryptionAlgorithm algorithm;

    protected DataEncryptor(EncryptionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public abstract String encryptData(String data);

    public abstract String decryptData(String data);
}
