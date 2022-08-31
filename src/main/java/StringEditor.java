import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringEditor {
    private final ResourcesWalker walker;

    public StringEditor(ResourcesWalker walker) {
        this.walker = walker;
    }

    public List<String> editFileNames(List<String> in) {
        List<String> out = new ArrayList<>();
        for (int i = 0; i < in.size(); i++)
        {
            String cleanText = in.get(i).replaceAll("(.)([A-Z])", "$1 $2");
            out.add(i + 1 + ". " + cleanText);
        }
        return out;
    }

    public String getAllFileNamesAsString() {
        StringBuilder out = new StringBuilder();
        List<String> fileNames = editFileNames(walker.getFileNames());
        for (String fileName : fileNames) {
            out.append(fileName).append("\n");
        }
        return out.toString();
    }

    public String removeWhitespaces(String in) {
        return in = StringUtils.replace(in, " ", "");
    }
}