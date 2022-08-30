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
                    String fileName = validator.overrideFileName(userInput);
                    String fileContent = "";
                    try {
                        fileContent = walker.loadFile(fileName);
                    } catch (IOException e) {
                        // I implemented custom exception message in ResourceWalker, that's why I left it here
                        e.printStackTrace();
                    }
                    writer.printFileContent(fileContent);
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
