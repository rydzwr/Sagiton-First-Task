import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileNameValidatorTest {
    private List<String> filenames = asList("Test1", "Test2", "Test3", "Test4");
    private ResourcesWalker walker;
    private FileNameValidator validator;

    @BeforeEach
    public void init() {
        walker = mock(ResourcesWalker.class);
        validator = new FileNameValidator(walker);
        when(walker.getFileNames()).thenReturn(filenames);
    }

    @Test
    public void validateTestShouldReturnTrue() {

    }

    @Test
    public void validateTestShouldReturnFalse() {

    }

    @Test
    public void overrideFilenameTest(){

    }
}
