import java.io.IOException;
import java.nio.CharBuffer;

public class Encryption {
    static String pathSource = "TextBeforeEncryption.txt";
    static String pathDestination = "TextAfterEncryption.txt";

    public static void encryption(int key) {
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

    private static int getNewIndex(int key, char c) {
        return (Alphabet.SIMBOLS.indexOf(c) + key) % Alphabet.SIMBOLS.length();
    }
}
