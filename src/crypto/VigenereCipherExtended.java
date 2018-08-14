package crypto;
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
		System.out.println(encryptbrute(ori, key));
		System.out.println(decrypt(enc, key));
	}

	public static String encrypt(String text, final String key) {
		String res = "";
		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				res += (char) ((c + key.charAt(j)) % 256);
				j = ++j % key.length();
			}
		}
		return res;
	}

	public static String encrypt5(String text, final String key) {
		String res = "";
		int word = 0;
		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				res += (char) ((c + key.charAt(j)) % 256);
				j = ++j % key.length();
				word = ++word % 5;
				if (word == 0)
					res += ' ';
			}
		}
		return res;
	}

	public static String encryptbrute(String text, final String key) {
		String res = "";
		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				res += (char) ((c + key.charAt(j)) % 256);
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
		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				res += (char) (Math.floorMod(c - key.charAt(j), 256));
				j = ++j % key.length();
			}
		}
		return res;
	}

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
