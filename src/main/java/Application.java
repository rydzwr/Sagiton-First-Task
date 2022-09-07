import java.io.*;

public class Application {

    final SpacesRemover remover;
    final BufferedReader reader;
    final ResourcesWalker walker;
    final FileNameValidator validator;
    final ScreenWriter writer;
    final PrintStream outputStream;

    public Application(InputStream in, OutputStream out) {
        remover = new SpacesRemover();
        reader = new BufferedReader(new InputStreamReader(in));
        walker = new ResourcesWalker();
        validator = new FileNameValidator(walker);
        outputStream = new PrintStream(out);
        writer = new ScreenWriter(walker, outputStream);
    }

    public void run() {
        remover.run(reader, walker, validator, writer);
    }

    public static void main(String[] args) {
        new Application(System.in, System.out).run();
    }
}

// To print dependency tree you need to run : mvn dependency:tree
// Maven saves all dependencies in C:\Users\<User_Name>\.m2