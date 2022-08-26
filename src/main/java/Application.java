import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResourcesWalker walker = new ResourcesWalker();
        FileNameValidator validator = new FileNameValidator(walker);
        StringEditor editor = new StringEditor();

       ScreenWriter writer = new ScreenWriter(walker);
       writer.printFilePrompt();
       String userInput = scanner.nextLine();
       if (validator.validate(userInput)) {
           String fileName = validator.overRideFileName(userInput);
           String fileContent = walker.loadFile(fileName);
           writer.printFileContent(editor.removeWhitespaces(fileContent));
       }
    }
}

// To print dependency tree you need to run : mvn dependency:tree
// Maven saves all dependencies in C:\Users\<User_Name>\.m2
