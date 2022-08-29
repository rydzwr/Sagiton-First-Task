import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StringEditorTest {
    private final List<String> filenames = asList("One", "Two", "Three", "Four");
    private StringEditor editor;

    @BeforeEach
    public void init() {
        ResourcesWalker walker = mock(ResourcesWalker.class);
        when(walker.getFileNames()).thenReturn(filenames);
        editor = new StringEditor(walker);
    }

    @Test
    public void editFileNamesTest() {
        //GIVEN
        List<String> validOutput = asList("1. One", "2. Two", "3. Three", "4. Four");
        //WHEN
        List<String> toTest = editor.editFileNames(filenames);
        //THEN
        assertEquals(validOutput.toString(), toTest.toString());
    }

    @Test
    public void getAllFileNamesAsStringTest() {
        //GIVEN
        String validOutput =
                """
                        1. One
                        2. Two
                        3. Three
                        4. Four""";
        //WHEN
        String toTest = editor.getAllFileNamesAsString();
        //THEN
        assertEquals(validOutput.trim(), toTest.trim());
    }

    @Test
    public void removeWhitespacesTest() {
        //GIVEN
        String in = "q w e r t y";
        String validOutput = "qwerty";
        //WHEN
        String toTest = editor.removeWhitespaces(in);
        //THEN
        assertEquals(toTest, validOutput);
    }
}