import java.util.List;

public class FileNameValidator {
    private final List<String> validFileNames;

    public FileNameValidator(ResourcesWalker resourcesWalker) {
        this.validFileNames = resourcesWalker.getFileNames();
    }

    public boolean validate(String fileName) {
        return validFileNames.stream().anyMatch(v -> v.matches("(.*)(?i)" + fileName + "(.*)"));
    }

    public String overrideFileName(String in) {
        String out = "";
        for (String validInput : validFileNames)
        {
            if (validInput.matches("(.*)(?i)" + in + "(.*)"))
            {
                return out = validInput;
            }
        }
        return out;
    }
}
