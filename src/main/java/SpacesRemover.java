import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class SpacesRemover {
    public void run(BufferedReader reader, ResourcesWalker walker, FileNameValidator validator, ScreenWriter writer) {
        String answer = "N";
        try {
            do {
                writer.printFilePrompt();
                String userInput = reader.readLine();

                if (validator.validate(userInput)) {
                    String fileName = validator.findFirstMatchingFileName(userInput);
                    String fileContent = "";
                    try {
                        fileContent = walker.loadFile(fileName);
                        writer.printFileContent(fileContent);
                    } catch (IOException e) {
                        System.out.println("Couldn't load file due to IO issue, please ...... Here's the error log:");
                        e.printStackTrace();
                    }
                    writer.printClosingProgramQuestion();
                    answer = reader.readLine();
                    answer = answer.toUpperCase(Locale.ROOT);
                } else {
                    System.out.println("Invalid Filename");
                }
            } while (!answer.equals("Y"));
        } catch (IOException e) {
            System.out.println("Couldn't read input");
        }
    }
}
