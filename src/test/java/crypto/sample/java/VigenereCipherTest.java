package crypto.sample.java;

import crypto.sample.java.crypto.VigenereCipher;
import org.junit.Assert;
import org.junit.Test;

public class VigenereCipherTest {
    @Test
    public void encodeDecodeCorrectlyTest() {
        String plainText = "HELLOWORLDWKWKKWKW";
        String strongPassword = "STRONGKEYEVER";
        String cipher = VigenereCipher.encrypt(plainText, strongPassword);
        String decoded = VigenereCipher.decrypt(cipher, strongPassword);
        Assert.assertEquals(plainText, decoded);
    }

    @Test
    public void encodeDecodeCorrectlyWhenUse5WordsTest() {
        String plainText = "HELLOWORLDWKWKKWKW";
        String strongPassword = "STRONGKEYEVER";
        String cipher = VigenereCipher.encrypt5(plainText, strongPassword);
        String decoded = VigenereCipher.decrypt(cipher, strongPassword);
        Assert.assertEquals(plainText, decoded);
    }

    @Test
    public void encodeDecodeCorrectlyWhenUseBruteForceTest() {
        String plainText = "HELLOWORLDWKWKKWKW";
        String strongPassword = "STRONGKEYEVER";
        String cipher = VigenereCipher.encryptBrute(plainText, strongPassword);
        String decoded = VigenereCipher.decrypt(cipher, strongPassword);
        Assert.assertEquals(plainText, decoded);
    }

    @Test
    public void encodeDecodeWrongKeyTest() {
        String plainText = "HELLOWORLDWKWKKWKW";
        String strongPassword = "STRONGKEYEVER";
        String cipher = VigenereCipher.encrypt(plainText, strongPassword);
        String decoded = VigenereCipher.decrypt(cipher, "WHYWRONGKEY");
        Assert.assertNotEquals(plainText, decoded);
    }
}
