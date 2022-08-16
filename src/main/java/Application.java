import java.io.IOException;
import java.util.Scanner;

public class Application
{
    public static void main(final String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        FirstTask firstTask = new FirstTask();
        String answer = "N";
        do
        {
            StringBuilder filename = new StringBuilder(firstTask.askForUserInput());
            if (!firstTask.sanitizeFilename(filename))
            {
                System.out.println("Invalid input filename!");
                continue;
            }

            try
            {
                System.out.println(firstTask.getFileContent(filename.toString()));
            }
            catch (IOException e)
            {
                System.out.println("Unable to open resource file!");
            }

            System.out.println("\r\n" + "Close Program? Y/N");
            answer = scanner.nextLine().toUpperCase();
        }
        while (!answer.equals("Y"));
    }
}

// To print dependency tree you need to run : mvn dependency:tree
// Maven saves all dependencies in C:\Users\<User_Name>\.m2
