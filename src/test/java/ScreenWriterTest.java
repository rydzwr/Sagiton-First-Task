import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScreenWriterTest {
    private final List<String> filenames = asList("One", "Two", "Three", "Four");
    private ScreenWriter writer;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void init() {
        ResourcesWalker walker = mock(ResourcesWalker.class);
        when(walker.getFileNames()).thenReturn(filenames);
        writer = new ScreenWriter(walker);
    }

    @BeforeEach
    public void setUp() {
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
    public void printFilePromptTest() {
        //GIVEN
        writer.printFilePrompt();
        //WHEN
        //outputStreamCaptor.toString().trim().contains("(.*)(?i)" + "Choose file to edit:" + "(.*)");
        //THEN
        assertTrue(outputStreamCaptor.toString().trim().contains("(.*)(?i)" + "Hi!" + "(.*)"));
    }

    @Test
    public void printClosingProgramQuestionTest() {

    }

    @Test
    public void printFileContentTest() {

    }
}
