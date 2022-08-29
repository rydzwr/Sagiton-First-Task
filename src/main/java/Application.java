import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        SpacesRemover remover = new SpacesRemover();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ResourcesWalker walker = new ResourcesWalker();
        FileNameValidator validator = new FileNameValidator(walker);
        ScreenWriter writer = new ScreenWriter(walker);
        try {
            remover.run(reader, walker, validator, writer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// To print dependency tree you need to run : mvn dependency:tree
// Maven saves all dependencies in C:\Users\<User_Name>\.m2