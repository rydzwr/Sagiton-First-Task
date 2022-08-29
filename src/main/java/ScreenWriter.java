public class ScreenWriter {
    private final StringEditor stringEditor;
    public ScreenWriter(ResourcesWalker resourcesWalker) {
        this.stringEditor = new StringEditor(resourcesWalker);
    }

    public void printFilePrompt() {
        System.out.println(concatenateFilePrompt());
    }

    public void printClosingProgramQuestion() {
        System.out.println("Close Program? ( Y/N )");
    }

    public void printFileContent(String content) {
        System.out.println(stringEditor.removeWhitespaces(content));
    }

    public String concatenateFilePrompt() {
        return "Hi!" + "\n" +
                "Choose file to edit:" + "\n" +
                stringEditor.getAllFileNamesAsString() + "\n" +
                "Enter file name: ";
    }
}
