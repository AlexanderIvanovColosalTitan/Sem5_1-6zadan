import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class AESEncryption implements EncryptionAlgorithm {
    private SecretKey secretKey;

    public AESEncryption() throws Exception {
        // Генерируем ключ только один раз
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        this.secretKey = keyGen.generateKey();
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    @Override
    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
