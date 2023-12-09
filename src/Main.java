import java.util.Scanner;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter a word or phrase:");

        String engPhrase = sc.nextLine();

        String alienPhrase = alienTranslation(engPhrase);
        System.out.println("Alien translation: " + alienPhrase);

        try {
            String hash = calculateSHA256(alienPhrase);
            System.out.println("The SHA-256 hash for the alien phrase is: " + hash);
        }catch (NoSuchAlgorithmException e) {
            System.out.println("The SHA-256 algorithm is not available on this device.");
        }


        String encryptedText = encrypt(engPhrase);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted text: " + decryptedText);

        bruteForceDecrypt(encryptedText);

    }

    //Encrypt and decrypt with a shift of 5
    public static String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char encryptedChar = (char) (base + (character - base + 5) % 26);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(character);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char encryptedChar = (char) (base + (character - base - 5) % 26);
                decryptedText.append(encryptedChar);
            } else {
                decryptedText.append(character);
            }
        }

        return decryptedText.toString();
    }


    //Brute force attempts to decrypt a string
    public static void bruteForceDecrypt(String text) {
        for (int shift = 0; shift < 26; shift++) {
            StringBuilder decryptedText = new StringBuilder();

            for (char character : text.toCharArray()) {
                if (Character.isLetter(character)) {
                    char base = Character.isLowerCase(character) ? 'a' : 'A';
                    char encryptedChar = (char) (base + (character - base - shift) % 26);
                    decryptedText.append(encryptedChar);
                } else {
                    decryptedText.append(character);
                }

           }

            System.out.println(shift + ": " + decryptedText);
        }
    }


    public static String alienTranslation(String text) {
        HashMap<Character, Character> alphabetMap = new HashMap<>();
        // Custom alphabet mapping
        alphabetMap.put('A', '~');
        alphabetMap.put('B', '!');
        alphabetMap.put('C', '@');
        alphabetMap.put('D', '#');
        alphabetMap.put('E', '$');
        alphabetMap.put('F', '%');
        alphabetMap.put('G', '^');
        alphabetMap.put('H', '&');
        alphabetMap.put('I', '*');
        alphabetMap.put('J', '#');
        alphabetMap.put('K', '~');
        alphabetMap.put('L', '!');
        alphabetMap.put('M', '@');
        alphabetMap.put('N', '#');
        alphabetMap.put('O', '$');
        alphabetMap.put('P', '%');
        alphabetMap.put('Q', '^');
        alphabetMap.put('R', '&');
        alphabetMap.put('S', '*');
        alphabetMap.put('T', '#');
        alphabetMap.put('U', '~');
        alphabetMap.put('V', '!');
        alphabetMap.put('W', '@');
        alphabetMap.put('X', '#');
        alphabetMap.put('Y', '$');
        alphabetMap.put('z', '%');

        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            // Check if the character is in the translation map
            if (Character.isLetter(ch)) {
                char translatedChar = alphabetMap.getOrDefault(Character.toUpperCase(ch), ch);
                result.append(Character.isLowerCase(ch) ? Character.toLowerCase(translatedChar) : translatedChar);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    public static String calculateSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());

        // Convert the byte array to a hexadecimal representation
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}