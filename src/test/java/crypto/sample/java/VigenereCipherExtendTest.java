package crypto.sample.java;

import crypto.sample.java.crypto.VigenereCipherExtended;
import org.junit.Assert;
import org.junit.Test;

public class VigenereCipherExtendTest {
    @Test
    public void encodeDecodeCorrectlyTest() {
        String plainText = "HELLOWORLDWKWKKWKW";
        String strongPassword = "STRONGKEYEVER";
        String cipher = VigenereCipherExtended.encrypt(plainText, strongPassword);
        String decoded = VigenereCipherExtended.decrypt(cipher, strongPassword);
        Assert.assertEquals(plainText, decoded);
    }

    @Test
    public void encodeDecodeCorrectlyWhenUse5WordsTest() {
        String plainText = "HELLOWORLDWKWKKWKW";
        String strongPassword = "STRONGKEYEVER";
        String cipher = VigenereCipherExtended.encrypt5(plainText, strongPassword);
        String decoded = VigenereCipherExtended.decrypt(cipher.replace(" ", ""), strongPassword);
        Assert.assertEquals(plainText, decoded);
    }

    @Test
    public void encodeDecodeCorrectlyWhenUseBruteForceTest() {
        String plainText = "HELLOWORLDWKWKKWKW";
        String strongPassword = "STRONGKEYEVER";
        String cipher = VigenereCipherExtended.encryptBrute(plainText, strongPassword);
        String decoded = VigenereCipherExtended.decrypt(cipher, strongPassword);
        Assert.assertEquals(plainText, decoded);
    }
}
