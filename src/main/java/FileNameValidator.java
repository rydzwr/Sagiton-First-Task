import java.util.List;

public class FileNameValidator {
    ResourcesWalker resourcesWalker = new ResourcesWalker();
    private final List<String> validFileNames;

    public FileNameValidator() {
        this.validFileNames = resourcesWalker.loadFileNames();
    }

    public boolean validate(String fileName) {
        return validFileNames.stream().anyMatch(v -> v.matches("(.*)(?i)" + fileName + "(.*)"));
    }

    public void overRideFileName(String in) {
        for (String validInput : validFileNames)
        {
            if (validInput.matches("(.*)(?i)" + in + "(.*)"))
            {
                in = "";
                in += validInput;
            }
        }
    }
}
