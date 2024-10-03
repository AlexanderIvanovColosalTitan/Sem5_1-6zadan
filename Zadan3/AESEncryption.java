public class AESEncryption implements Encryption {
    @Override
    public String encrypt(String data) {
        return "Зашифровано (AES)";
    }

    @Override
    public String decrypt(String data) {
        return "Расшифровано (AES)";
    }
}
