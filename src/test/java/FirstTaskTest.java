import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.assertj.core.api.Assertions.*;

class FirstTaskTest
{
    private final List<String> mockInputs = Arrays.asList("AnotherBrickInTheWall", "Money");

    @Test
    @DisplayName("Should return false")
    void invalidInputTest()
    {
        //GIVEN
        FirstTask firstTask = new FirstTask(mockInputs);
        FirstTask spy = Mockito.spy(firstTask);
        //WHEN
        boolean toTest = spy.sanitizeFilename(new StringBuilder("foo"));
        //THEN
        assertFalse(toTest);
    }

    @Test
    @DisplayName("Whitespaces count should equals 0")
    void verifiesIfFileOutputHasAnyWhiteSpaces() throws IOException
    {
        //GIVEN
        FirstTask firstTask = new FirstTask(mockInputs);
        //WHEN
        String fileContent = firstTask.getFileContent("src/main/res/Money.txt");
        
        //THEN
        assertEquals(-1, fileContent.indexOf(' '));
    }

    @Test
    @DisplayName("Should return Invalid Input")
    void invalidInputReturned() throws IOException
    {
        //GIVEN
        FirstTask firstTask = new FirstTask(mockInputs);
        //WHEN
        String toTest = firstTask.getFileContent("Invalid Input");
        //THEN
        assertEquals("Invalid Input", toTest);
    }

    @Test
    @DisplayName("Should throw IOException")
    void ioExceptionIsThrown()
    {
        //GIVEN
        FirstTask firstTask = new FirstTask(mockInputs);
        //WHEN + THEN
        assertThatIOException().isThrownBy(() -> firstTask.getFileContent("foo"));
    }
}