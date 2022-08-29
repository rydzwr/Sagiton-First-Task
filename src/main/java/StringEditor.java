import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringEditor {
    private ResourcesWalker walker;

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
        String out = "";
        List<String> fileNames = editFileNames(walker.getFileNames());
        for (String fileName : fileNames) {
            out += fileName + "\n";
        }
        return out;
    }

    public String removeWhitespaces(String in) {
        return in = StringUtils.replace(in, " ", "");
    }
}
