import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Encryption {
    static final String SIMBOLS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? ";
    static String pathSource = "TextBeforeEncryption.txt";
    static String pathDestination = "TextAfterEncryption.txt";

    public static void encryption(int key) {

        CharBuffer ch = WorkWithFile.read(pathSource);

            StringBuilder output = new StringBuilder();

            for (int i = 0; i < ch.length(); i++) {
                char c = ch.charAt(i);
                if (SIMBOLS.indexOf(c) != -1) {
                    int newIndex = (SIMBOLS.indexOf(c) + key) % SIMBOLS.length();
                    output.append(SIMBOLS.charAt(newIndex));
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
}
