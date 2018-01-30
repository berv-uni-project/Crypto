public class VigenereCipherExtended {
    public static void main(String[] args) {
        String key = "VIGENERECIPHER";
        String ori = "Beware the Jabberwock, my son! The jaws that bite, the claws that catch!";
        String enc = encrypt(ori, key);
        System.out.println(enc);
        System.out.println(encrypt5(ori,key));
        System.out.println(encryptbrute(ori,key));
        System.out.println(decrypt(enc, key));
    }
 
    static String encrypt(String text, final String key) {
        String res = "";
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                res += (char)((c + key.charAt(j)) % 256);
                j = ++j % key.length();
            }
        }
        return res;
    }
 
    static String encrypt5(String text, final String key) {
        String res = "";
        int word = 0;
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                res += (char)((c + key.charAt(j)) % 256);
                j = ++j % key.length();
                word = ++word % 5;
                if (word == 0) res+= ' ';
            }
        }
        return res;
    }
    
    static String encryptbrute(String text, final String key) {
        String res = "";
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255){
                res += (char)((c + key.charAt(j)) % 256);
                j = ++j % key.length();
            } else {
                if (c == ' ') res+= c;
            }
        }
        return res;
    }
 
    static String decrypt(String text, final String key) {
        String res = "";
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                res += (char)(Math.floorMod(c - key.charAt(j), 256));
                j = ++j % key.length();
            }
        }
        return res;
    }
}
