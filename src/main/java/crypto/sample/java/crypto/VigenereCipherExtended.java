package crypto.sample.java.crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class VigenereCipherExtended {
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
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                res.append((char) ((c + key.charAt(j)) % 256));
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
        int word = 0;
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                res.append((char) ((c + key.charAt(j)) % 256));
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
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                res.append((char) ((c + key.charAt(j)) % 256));
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
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                res.append((char) (Math.floorMod(c - key.charAt(j), 256)));
                j = ++j % key.length();
            }
        }
        return res.toString();
    }

    /**
     * Encryption Vigenere for File
     * @param inputFile Plain Text File
     * @param outputFile Encrypted Text File Location
     * @param key Key for encryption
     */
    public static void encryptFile(String inputFile, String outputFile, String key) throws IOException {
        InputStream inputStream = new FileInputStream(inputFile);
        OutputStream outputStream = new FileOutputStream(outputFile);
        int byteRead;
        int j = 0;
        while ((byteRead = inputStream.read()) != -1) {
            byteRead = ((byteRead + key.charAt(j)) % 256);
            j = ++j % key.length();
            outputStream.write(byteRead);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * Encryption Vigenere for File
     * @param inputFile Encrypted Text File
     * @param outputFile Decrypted Text File Location
     * @param key Key for decryption
     */
    public static void decryptFile(String inputFile, String outputFile, String key) throws IOException {
        InputStream inputStream = new FileInputStream(inputFile);
        OutputStream outputStream = new FileOutputStream(outputFile);

        int byteRead;
        int j = 0;
        while ((byteRead = inputStream.read()) != -1) {
            byteRead = Math.floorMod(byteRead - key.charAt(j), 256);
            j = ++j % key.length();
            outputStream.write(byteRead);
        }

        inputStream.close();
        outputStream.close();

    }
}
