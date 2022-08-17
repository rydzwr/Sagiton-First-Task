import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FirstTask
{
    private List<String> validInputs = new ArrayList<>();

    public FirstTask()
    {
        getDefaultValidInputs();
    }

    public FirstTask(List<String> validInputs)
    {
        this.validInputs = validInputs;
    }

    public void getDefaultValidInputs()
    {
        validInputs.add("Money");
        validInputs.add("AnotherBrickInTheWall");
        validInputs.add("WelcomeToTheMachine");
        validInputs.add("WishYouWereHere");
    }

    public String askForUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi!");
        System.out.println("Choose file to edit:");
        for (int i = 0; i < validInputs.size(); i++)
        {
            String cleanText = validInputs.get(i).replaceAll("(.)([A-Z])", "$1 $2");
            System.out.println(i + 1 + ". " + cleanText);
        }
        System.out.println("\n" + "Enter file name: ");
        return scanner.nextLine();
    }

    public boolean sanitizeFilename(StringBuilder in)
    {

        String message = in.toString();
        /*
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < charArray.length; i++)
        {
            if (Character.isLetter(charArray[i]))
            {
                if (foundSpace)
                {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            }
            else
                foundSpace = true;
        }
        message = String.valueOf(charArray);


         */
        message = message.replaceAll(" ", "");
        String userInput = message;

        for (String validInput : validInputs)
        {
            if (validInput.matches("(.*)(?i)" + userInput + "(.*)"))
            {
                in.setLength(0);
                in.append(validInput).append(".txt");
                return true;
            }
        }
        return false;
    }

    public String getFileContent(String filename) throws IOException
    {
        if (filename.equals("Invalid Input"))
            return filename;
        else
        {
            URL url = this.getClass().getClassLoader().getResource(filename);

            if (url == null)
                throw new IOException("Invalid filename");

            String fileContents = IOUtils.toString(url, StandardCharsets.UTF_8);
            fileContents = StringUtils.replace(fileContents, " ", "");
            return fileContents;
        }
    }
}