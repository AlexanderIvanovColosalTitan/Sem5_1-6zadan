import java.security.MessageDigest;
import java.util.Base64;
public class SHAEncryption implements EncryptionAlgorithm {
    @Override
    public String encrypt(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decrypt(String data) {
        throw new UnsupportedOperationException("SHA is a one-way hash function and cannot be decrypted.");
    }
}