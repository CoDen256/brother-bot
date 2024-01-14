package aljolen;

import org.apache.commons.io.IOUtils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    private Utils(){}

    public static void saveLocally(String filename, InputStream data){
        try (OutputStream out = Files.newOutputStream(Paths.get(filename))) {
            IOUtils.copy(data, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
