package crypto.sample.java.crypto;

public class VigenereCipher {
    public static void main(String[] args) {
        String key = "VIGENERECIPHER";
        String ori = "Beware the Jabberwock, my son! The jaws that bite, the claws that catch!";
        String enc = encrypt(ori, key);
        System.out.println(enc);
        System.out.println(encrypt5(ori, key));
        System.out.println(encryptBrute(ori, key));
        System.out.println(decrypt(enc, key));
    }

    /**
     * Encryption Vigenere
     * @param text Plain Text
     * @param key Key for encryption
     * @return Encrypted Text
     */
    public static String encrypt(String text, final String key) {
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
                j = ++j % key.length();
            }
        }
        return res.toString();
    }

    /**
     * Encryption Vigenere
     * @param text Plain Text
     * @param key Key for encryption
     * @return Encrypted Text
     */
    public static String encrypt5(String text, final String key) {
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        int word = 0;
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
                j = ++j % key.length();
                word = ++word % 5;
                if (word == 0)
                    res.append(' ');
            }
        }
        return res.toString();
    }

    /**
     * Encryption Vigenere
     * @param text Plain Text
     * @param key Key for encryption
     * @return Encrypted Text
     */
    public static String encryptBrute(String text, final String key) {
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
                j = ++j % key.length();
            } else {
                if (c == ' ')
                    res.append(c);
            }
        }
        return res.toString();
    }

    /**
     * Decryption
     * @param text encrypted text
     * @param key decrypt key
     * @return Decrypted text
     */
    public static String decrypt(String text, final String key) {
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res.append((char) ((c - key.charAt(j) + 26) % 26 + 'A'));
                j = ++j % key.length();
            }
        }
        return res.toString();
    }
}
