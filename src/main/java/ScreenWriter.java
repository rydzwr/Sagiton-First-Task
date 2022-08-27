import java.util.List;

public class ScreenWriter {
    private final ResourcesWalker resourcesWalker;
    private final StringEditor stringEditor;
    public ScreenWriter(ResourcesWalker resourcesWalker) {
        this.resourcesWalker = resourcesWalker;
        this.stringEditor = new StringEditor();
    }

    public void printFilePrompt() {
        System.out.println("Hi!");
        System.out.println("Choose file to edit:");

        List<String> fileNames = stringEditor.editFileNames(resourcesWalker.getFileNames());
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
        System.out.println("\n" + "Enter file name: ");
    }

    public void printClosingProgramQuestion() {
        System.out.println("Close Program? ( Y/N )");
    }

    public void printFileContent(String content) {
        System.out.println(stringEditor.removeWhitespaces(content));
    }
}
