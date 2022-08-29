import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class SpacesRemover {
    public void run(Scanner scanner, ResourcesWalker walker, FileNameValidator validator, ScreenWriter writer) {
        String answer = "";
        do {
            writer.printFilePrompt();
            String userInput = scanner.nextLine();

            if (validator.validate(userInput)) {
                String fileName = validator.overrideFileName(userInput);
                String fileContent = "";
                try {
                    fileContent = walker.loadFile(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
