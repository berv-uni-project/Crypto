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

    public static String encrypt(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % key.length();
            }
        }
        return res;
    }

    public static String encrypt5(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        int word = 0;
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % key.length();
                word = ++word % 5;
                if (word == 0)
                    res += ' ';
            }
        }
        return res;
    }

    public static String encryptBrute(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % key.length();
            } else {
                if (c == ' ')
                    res += c;
            }
        }
        return res;
    }

    public static String decrypt(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
                j = ++j % key.length();
            }
        }
        return res;
    }
}
