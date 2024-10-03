public class SHAEncryption implements Encryption {
    @Override
    public String encrypt(String data) {
        return "Зашифровано (SHA)";
    }

    @Override
    public String decrypt(String data) {
        return "Расшифровано (SHA)";
    }
}
