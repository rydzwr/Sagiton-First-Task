import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScreenWriterTest {
    private final List<String> filenames = asList("One", "Two", "Three", "Four");
    private ScreenWriter writer;
    private StringEditor editor;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    ResourcesWalker walker;

    @Before
    public void init() {
        when(walker.getFileNames()).thenReturn(filenames);
        writer = new ScreenWriter(walker, new PrintStream(outputStreamCaptor));
        editor = new StringEditor(walker);
    }

    @Test
    public void printFilePromptTest() {
        //GIVEN
        String validOutput = "Hi!" + "\n" +
                "Choose file to edit:" + "\n" +
                editor.getAllFileNamesAsString() + "\n" +
                "Enter file name: ";
        //WHEN
        writer.printFilePrompt();
        //THEN
        assertEquals(validOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void printClosingProgramQuestionTest() {
        //GIVEN
        String validOutput = "Close Program? ( Y/N )";
        //WHEN
        writer.printClosingProgramQuestion();
        //THEN
        assertEquals(validOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void printFileContentTest() {
        //GIVEN
        String in = "q w e r t y";
        String validOutput = "qwerty";
        //WHEN
        writer.printFileContent(in);
        //THEN
        assertEquals(validOutput.trim(), outputStreamCaptor.toString().trim());
    }
}