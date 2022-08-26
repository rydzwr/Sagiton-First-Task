import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(final String[] args) throws IOException {
        ScreenWriter screenWriter = new ScreenWriter();
        screenWriter.printFilePrompt();
    }
}

// To print dependency tree you need to run : mvn dependency:tree
// Maven saves all dependencies in C:\Users\<User_Name>\.m2
