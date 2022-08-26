import java.util.List;

public class ScreenWriter {
    public void printFilePrompt() {
        ResourcesWalker resourcesWalker = new ResourcesWalker();
        StringEditor stringEditor = new StringEditor();

        System.out.println("Hi!");
        System.out.println("Choose file to edit:");

        List<String> fileNames = stringEditor.editFileNames(resourcesWalker.loadFileNames());
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
        System.out.println("\n" + "Enter file name: ");
    }

    public void printFileContent(String content) {
        System.out.println(content);
    }
}
