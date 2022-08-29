import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SpacesRemoverTest {
    private final List<String> filenames = asList("One", "Two", "Three", "Four");
    private SpacesRemover remover;
    private ResourcesWalker walker;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void init() {
        walker = mock(ResourcesWalker.class);
        when(walker.getFileNames()).thenReturn(filenames);
        remover = new SpacesRemover();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
        System.out.println("Hello Baeldung Readers!!");
        assertEquals("Hello Baeldung Readers!!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void spacesRemoverTest() {
        //GIVEN
        Scanner scanner = new Scanner(System.in);
        FileNameValidator validator = new FileNameValidator(walker);
        ScreenWriter writer = new ScreenWriter(walker);
        StringEditor editor = new StringEditor(walker);

        String validOutput = "Hi!" + "\n" +
                "Choose file to edit:" + "\n" +
                editor.getAllFileNamesAsString() + "\n" +
                "Enter file name: ";

        //WHEN
        remover.run(scanner, walker, validator, writer);
        //THEN
        assertEquals(validOutput.trim(), outputStreamCaptor.toString().trim());
    }
}
