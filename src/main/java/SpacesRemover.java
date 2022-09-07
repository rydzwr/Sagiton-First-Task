import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class SpacesRemover {
    private static final String NO = "N";
    private static final String YES = "Y";

    public void run(BufferedReader reader, ResourcesWalker walker, FileNameValidator validator, ScreenWriter writer) {
        String answer = NO;
        try {
            do {
                writer.printFilePrompt();
                String userInput = reader.readLine();

                if (validator.validate(userInput)) {
                    String fileName = validator.findFirstMatchingFileName(userInput);
                    if (fileName == null) {
                        throw new RuntimeException("Couldn't process filename");
                    }
                    try {
                        String fileContent = walker.loadFile(fileName);
                        writer.printFileContent(fileContent);
                    } catch (IOException e) {
                        writer.print("Couldn't load file due to IO issue, please ...... Here's the error log:");
                        e.printStackTrace();
                    }
                    writer.printClosingProgramQuestion();
                    answer = reader.readLine().toUpperCase(Locale.ROOT);
                } else {
                    writer.print("Invalid Filename");
                }
            } while (!answer.equals(YES));
        } catch (IOException e) {
            writer.print("Couldn't read input, Here's the error log:");
            e.printStackTrace();
        }
    }
}
