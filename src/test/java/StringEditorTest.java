import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        //assertTrue(Arrays.equals(validOutput, toTest));
    }
}
