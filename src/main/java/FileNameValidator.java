import java.util.List;

public class FileNameValidator {
    private final List<String> validFileNames;

    public FileNameValidator(ResourcesWalker resourcesWalker) {
        this.validFileNames = resourcesWalker.getFileNames();
    }

    public boolean validate(String fileName) {
        if (fileName.trim().length() > 3) {
            return validFileNames.stream().anyMatch(v -> v.matches("(.*)(?i)" + fileName + "(.*)"));
        }
        return false;
    }

    public String findFirstMatchingFileName(String fileName) {
        for (String validInput : validFileNames) {
            if (validInput.matches("(.*)(?i)" + fileName + "(.*)"))  {
                return validInput;
            }
        }
        return null;
    }
}
