package crypto.sample.java.cli;

import crypto.sample.java.crypto.PlayfairCipher;
import crypto.sample.java.crypto.VigenereCipher;
import crypto.sample.java.crypto.VigenereCipherExtended;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Crypto {
    public static void main(String[] args) {
        String cipher = "-vcs";
        String inputOption = "-k";
        String outputFormat = "-d";
        String outputFile;
        String decryptedFile = "decrypted.txt";
        String writeOption = "-s";
        if (args.length < 4) {
            System.err.println(
                    "Run with : Crypto [-p/-vcs/-vce] [-k/-f] [-d/-ws/-5w] [output file] [-s/-b] [decryptedFile]");
        } else {
            if (args[0].equalsIgnoreCase("-p") || args[0].equalsIgnoreCase("-vcs")
                    || args[0].equalsIgnoreCase("-vce")) {
                cipher = args[0];
            }
            if (args[1].equalsIgnoreCase("-k") || args[1].equalsIgnoreCase("-f")) {
                inputOption = args[1];
            }
            if (args[2].equalsIgnoreCase("-d") || args[2].equalsIgnoreCase("-ws") || args[2].equalsIgnoreCase("-5w")) {
                outputFormat = args[2];
            }
            if (args.length > 4) {
                if (args[4].equalsIgnoreCase("-s") || args[4].equalsIgnoreCase("-b")) {
                    writeOption = args[5];
                }
                decryptedFile = args[5];
            }
            outputFile = args[3];
            try {
                // Initialization Input
                Scanner sc = new Scanner(System.in);
                if (args.length == 4) {
                    BufferedWriter outStream = new BufferedWriter(new FileWriter(outputFile));
                    String inputText;
                    String key;
                    if (inputOption.equalsIgnoreCase("-k")) {
                        inputText = Crypto.prompt("Enter message:", sc, 1);
                    } else {
                        String filePath = Crypto.prompt("Enter file path:", sc, 1);
                        inputText = readFileAsString(filePath);
                    }
                    key = Crypto.prompt("Enter key:", sc, 1);
                    System.out.printf("\nOriginal String:\n %s\n", inputText);
                    String output;
                    String decryptOutput;
                    if (cipher.equalsIgnoreCase("-vcs")) {
                        // Vigenere Cipher Standard
                        if (outputFormat.equalsIgnoreCase("-d")) {
                            output = VigenereCipher.encryptBrute(inputText, key);
                        } else if (outputFormat.equalsIgnoreCase("-ws")) {
                            output = VigenereCipher.encrypt(inputText, key);
                        } else {
                            output = VigenereCipher.encrypt5(inputText, key);
                        }
                        decryptOutput = VigenereCipher.decrypt(output, key);
                    } else if (cipher.equalsIgnoreCase("-vce")) {
                        // Vigenere Cipher Extended
                        if (outputFormat.equalsIgnoreCase("-d")) {
                            output = VigenereCipherExtended.encryptBrute(inputText, key);
                        } else if (outputFormat.equalsIgnoreCase("-ws")) {
                            output = VigenereCipherExtended.encrypt(inputText, key);
                        } else {
                            output = VigenereCipherExtended.encrypt5(inputText, key);
                        }
                        decryptOutput = VigenereCipherExtended.decrypt(output, key);
                    } else {
                        // Playfair
                        if (outputFormat.equalsIgnoreCase("-d")) {
                            output = PlayfairCipher.encrypt(inputText, key, true);
                        } else if (outputFormat.equalsIgnoreCase("-ws")) {
                            output = PlayfairCipher.encrypt(inputText, key, true);
                        } else {
                            output = PlayfairCipher.encrypt5(inputText, key, true);
                        }
                        decryptOutput = PlayfairCipher.decrypt(output);
                    }
                    outStream.write(output);
                    System.out.printf("\nEncode Text:\n %s\n", output);
                    System.out.printf("\nDecode Text:\n %s\n", decryptOutput);
                    outStream.close();
                } else {
                    String filePath = Crypto.prompt("Enter file path:", sc, 1);
                    String key = Crypto.prompt("Enter key:", sc, 1);
                    VigenereCipherExtended.encryptFile(filePath, outputFile, key);
                    VigenereCipherExtended.decryptFile(outputFile, decryptedFile, key);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String changeToBinary(String input) {
        byte[] bytes = input.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            binary.append(Integer.toBinaryString(b));
            binary.append(' ');
        }
        return binary.toString();
    }

    public static String readFileAsString(String fileName) throws Exception {
        String data;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static String prompt(String promptText, Scanner sc, int minLen) {
        String s;
        do {
            System.out.print(promptText);
            s = sc.nextLine().trim();
        } while (s.length() < minLen);
        return s;
    }

}