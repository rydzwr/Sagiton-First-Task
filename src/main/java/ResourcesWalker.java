import java.io.*;
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
                throw new IOException("ResourcesWalker: Couldn't load files");
            }
            Collections.addAll(fileNames, names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }
}