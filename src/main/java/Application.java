import java.util.Locale;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResourcesWalker walker = new ResourcesWalker();
        FileNameValidator validator = new FileNameValidator(walker);
        ScreenWriter writer = new ScreenWriter(walker);

        String answer = "";
        do {
            writer.printFilePrompt();
            String userInput = scanner.nextLine();

            if (validator.validate(userInput)) {
                String fileName = validator.overrideFileName(userInput);
                String fileContent = walker.loadFile(fileName);
                writer.printFileContent(fileContent);
                writer.printClosingProgramQuestion();
                answer = scanner.nextLine();
                answer = answer.toUpperCase(Locale.ROOT);
            } else {
                System.out.println("Invalid Filename");
            }
        } while (!answer.equals("Y"));
    }
}

// To print dependency tree you need to run : mvn dependency:tree
// Maven saves all dependencies in C:\Users\<User_Name>\.m2
