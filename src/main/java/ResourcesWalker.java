import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResourcesWalker {
    public List<String> loadFileNames() {
        List<String> fileNames = new ArrayList<>();

        String pathName = ".\\target\\classes";
        File file = new File(pathName);

        FilenameFilter filter = (f, name) -> name.endsWith(".txt");
        String[] names = file.list(filter);

        try {
            if (names == null) {
                throw new IOException("ResourcesWalker( 'loadFileNames' ): Couldn't load files");
            }
            Collections.addAll(fileNames, names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    public String loadFile(String filename) {
        String out = "";
        try {
            URL url = this.getClass().getClassLoader().getResource(filename);
            if (url != null) {
                out = IOUtils.toString(url, StandardCharsets.UTF_8);
            }
            else throw new IOException("ResourcesWalker: Couldn't load files");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}