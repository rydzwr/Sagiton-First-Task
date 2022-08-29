import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResourcesWalker {
    private final List<String> fileNames;

    public ResourcesWalker() {
        this.fileNames = loadFileNames();
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    private List<String> loadFileNames() {
        List<String> out = new ArrayList<>();

        String pathName = ".\\target\\classes";
        File file = new File(pathName);

        FilenameFilter filter = (f, name) -> name.endsWith(".txt");
        String[] names = file.list(filter);

        try {
            if (names == null) {
                throw new IOException("ResourcesWalker( 'loadFileNames' ): Couldn't load files");
            }
            Collections.addAll(out, names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public String loadFile(String filename) throws IOException {

        String out;

            URL url = this.getClass().getClassLoader().getResource(filename);
            if (url != null) {
                out = IOUtils.toString(url, StandardCharsets.UTF_8);
            }
            else throw new IOException("ResourcesWalker( 'loadFile' ): Couldn't load file");

        return out;
    }
}