
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class StringEditorTest {
    private final List<String> filenames = asList("One", "Two", "Three", "Four");
    private StringEditor editor;

    @Mock
    ResourcesWalker walker;

    @Before
    public void init() {
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