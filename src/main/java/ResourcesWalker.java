import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.Arrays.asList;

public class ResourcesWalker {
    private final List<String> fileNames;

    public ResourcesWalker() {
        this.fileNames = loadFileNames();
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    private List<String> loadFileNames() {
        String pathName = ".\\target\\classes";
        File file = new File(pathName);

        FilenameFilter filter = (f, name) -> name.endsWith(".txt");
        String[] names = file.list(filter);

        if (names != null) {
            return asList(names);
        }
        throw new RuntimeException("Could not find such a path");
    }

    public String loadFile(String filename) throws IOException {
        URL url = this.getClass().getClassLoader().getResource(filename);
        if (url != null) {
            return IOUtils.toString(url, StandardCharsets.UTF_8);
        }
        throw new IOException("ResourcesWalker( 'loadFile' ): Couldn't load file");
    }
}