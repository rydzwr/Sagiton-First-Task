import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        SpacesRemover remover = new SpacesRemover();
        Scanner scanner = new Scanner(System.in);
        ResourcesWalker walker = new ResourcesWalker();
        FileNameValidator validator = new FileNameValidator(walker);
        ScreenWriter writer = new ScreenWriter(walker);
        remover.run(scanner, walker, validator, writer);
    }
}

// To print dependency tree you need to run : mvn dependency:tree
// Maven saves all dependencies in C:\Users\<User_Name>\.m2