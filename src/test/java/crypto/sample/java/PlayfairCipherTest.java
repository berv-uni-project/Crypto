package crypto.sample.java;

import crypto.sample.java.crypto.PlayfairCipher;
import org.junit.Assert;
import org.junit.Test;

public class PlayfairCipherTest {
    @Test
    public void encodeAndDecodeCorrectlyTest() {
        String plainText = "HELLOWORLDWKWKKWKW";
        String strongPassword = "StrongKeyEver";
        String cipherText = PlayfairCipher.encrypt(plainText, strongPassword, false);
        String decodedText = PlayfairCipher.decrypt(cipherText);
        String actualText = decodedText.replace("Z", "");
        Assert.assertEquals(plainText, actualText);
    }
}
