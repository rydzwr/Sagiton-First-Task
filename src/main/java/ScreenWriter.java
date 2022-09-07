import java.io.PrintStream;

public class ScreenWriter {
    private final StringEditor stringEditor;
    private final PrintStream out;

    public ScreenWriter(ResourcesWalker resourcesWalker, PrintStream out) {
        this.stringEditor = new StringEditor(resourcesWalker);
        this.out = out;
    }

    public void printFilePrompt() {
        out.println(concatenateFilePrompt());
    }

    public void printClosingProgramQuestion() {
        out.println("Close Program? ( Y/N )");
    }

    public void printFileContent(String content) {
        out.println(stringEditor.removeWhitespaces(content));
    }

    private String concatenateFilePrompt() {
        return "Hi!" + "\n" +
                "Choose file to edit:" + "\n" +
                stringEditor.getAllFileNamesAsString() + "\n" +
                "Enter file name:";
    }

    public void print(String content) {
        out.println(content);
    }
}