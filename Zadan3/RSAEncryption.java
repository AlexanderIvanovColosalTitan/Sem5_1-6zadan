public class RSAEncryption implements Encryption {
    @Override
    public String encrypt(String data) {
        return "Зашифровано (RSA)";
    }

    @Override
    public String decrypt(String data) {
        return "Расшифровано (RSA)";
    }
}
