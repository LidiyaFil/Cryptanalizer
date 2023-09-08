import java.io.IOException;
import java.nio.CharBuffer;

public class Decoding {
    static String pathSource = "TextAfterEncryption.txt";
    static String pathDestination = "TextAfterEncryptionAndDecryption.txt";

    public static void decoding(int key) {
        CharBuffer ch = WorkWithFile.read(pathSource);

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < ch.length(); i++) {
            char c = ch.charAt(i);
            if (Alphabet.SIMBOLS.indexOf(c) != -1) {
                int newIndex = getNewIndex(key, c);
                output.append(Alphabet.SIMBOLS.charAt(newIndex));
            } else {
                output.append(c);
            }
        }

        try {
            WorkWithFile.write(pathDestination, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void brutForce() {
        CharBuffer ch = WorkWithFile.read(pathSource);

        for (int key = 1; key <= 20; key++) {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < ch.length(); i++) {
                char c = ch.charAt(i);
                if (Alphabet.SIMBOLS.indexOf(c) != -1) {
                    int newIndex = getNewIndex(key, c);
                    output.append(Alphabet.SIMBOLS.charAt(newIndex));
                } else {
                    output.append(c);
                }
            }
            if (isDecryptionValid(String.valueOf(output))) {
                try {
                    WorkWithFile.write(pathDestination, output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private static int getNewIndex(int key, char c) {
        return (Alphabet.SIMBOLS.indexOf(c) - key + Alphabet.SIMBOLS.length()) % Alphabet.SIMBOLS.length();
    }

    private static boolean isDecryptionValid(String output) {
        String[] searchWordsAndSigns = {" в ", " на ", " без ", " до ", " для ", " за ", " через ", " над ", " по ", " из ", " у ", " около ",
                " под ", " о ", " про ", " к ", " перед ", " при ", " с ", " между ", ", ", ". ", "! ", "? "};
        int minFoundWords = 20;
        int countFoundWords = 0;
        for (String word : searchWordsAndSigns) {
            int index = 0;
            while (index != -1) {
                index = output.indexOf(word, index);
                if (index == -1) {
                    break;
                }
                countFoundWords++;
                index += word.length();
            }
        }
        return countFoundWords >= minFoundWords;
    }
}
