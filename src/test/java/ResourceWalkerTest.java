import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ResourceWalkerTest {
    private List<String> validOutput;
    private ResourcesWalker walker;

    @BeforeEach
    public void init() {
        walker = new ResourcesWalker();
        validOutput = List.of("AnotherBrickInTheWall.txt", "Money.txt", "WelcomeToTheMachine.txt", "WishYouWereHere.txt");
    }

    @Test
    public void loadFileNamesTest() {
        //GIVEN + WHEN
        List<String> toTest = walker.getFileNames();
        //THEN
        assertEquals(validOutput, toTest);
    }

    @Test
    public void loadFileTest() throws IOException {
        //GIVEN
        String validFilePart = "Get away";
        //WHEN
        String toTest = walker.loadFile("Money.txt");

        //THEN
        assertThat(toTest).contains(validFilePart);
    }

    @Test
    public void loadFileShouldTrowException() {
        var exception = catchThrowable(() -> walker.loadFile("Foo"));
        assertThat(exception).isInstanceOf(IOException.class).hasMessageContaining("ResourcesWalker( 'loadFile' ): Couldn't load file");
    }
}
