import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decoding {
    static final String SIMBOLS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? Fuck";
    static String pathSource = "TextAfterEncryption.txt";
    static String pathDestination = "TextAfterEncryptionAndDecryption.txt";

    public static void decoding(int key) {
        try (RandomAccessFile raf = new RandomAccessFile(pathSource, "rw");
             FileChannel fc = raf.getChannel()) {
            ByteBuffer bb = ByteBuffer.allocate((int) fc.size());

            fc.read(bb);
            bb.flip();

            StringBuilder output = new StringBuilder();
            for (byte b : bb.array()) {
                char c = (char) b;
                if (SIMBOLS.indexOf(c) != -1) {
                    int newIndex = (SIMBOLS.indexOf(c) - key + SIMBOLS.length()) % SIMBOLS.length();
                    output.append(SIMBOLS.charAt(newIndex));
                } else {
                    output.append(c);
                }
            }

            Path outputPath = Paths.get(pathDestination);
            Files.createFile(outputPath);
            Files.write(outputPath, output.toString().getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void brutForce() {

    }
}
