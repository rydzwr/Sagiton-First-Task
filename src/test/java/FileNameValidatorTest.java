import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileNameValidatorTest {
    private final List<String> filenames = asList("TestOne", "TestTwo", "TestThree", "TestFour");
    private FileNameValidator validator;

    @BeforeEach
    public void init() {
        ResourcesWalker walker = mock(ResourcesWalker.class);
        when(walker.getFileNames()).thenReturn(filenames);
        validator = new FileNameValidator(walker);
    }

    @Test
    public void validateTestShouldReturnTrue() {
        //GIVEN
        boolean toTest = validator.validate("TestOne");
        //WHEN+THEN
        assertTrue(toTest);
    }

    @Test
    public void validateTestShouldReturnFalse() {
        //GIVEN
        boolean toTest = validator.validate("Foo");
        //WHEN+THEN
        assertFalse(toTest);
    }

    @Test
    public void overrideFilenameTest(){
        //GIVEN
        String toOverride = "TestThr";
        //WHEN
        String toTest = validator.findFirstMatchingFileName(toOverride);
        //THEN
        assertEquals("TestThree", toTest);
    }

    @Test
    public void overrideFilenameShouldReturnNull(){
        //GIVEN
        String toOverride = "Foo";
        //WHEN
        String toTest = validator.findFirstMatchingFileName(toOverride);
        //THEN
        assertNull(toTest);
    }
}