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

public class WorkWithFile {
    public static CharBuffer read(String pathSource) {
        CharBuffer ch = null;
        try (RandomAccessFile raf = new RandomAccessFile(pathSource, "rw");
             FileChannel fc = raf.getChannel()) {
            ByteBuffer bb = ByteBuffer.allocate((int) fc.size());

            fc.read(bb);
            bb.flip();

            Charset charset = StandardCharsets.UTF_8;
            ch = charset.decode(bb);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ch;
    }

    public static void write(String pathDestination, StringBuilder st) throws IOException {

        Path outputPath = Paths.get(pathDestination);
        Files.createFile(outputPath);
        Files.writeString(outputPath, st);
    }
}
