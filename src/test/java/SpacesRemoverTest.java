import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SpacesRemoverTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldPrintMessageToUser() throws IOException {
        //GIVEN
        SpacesRemover remover = new SpacesRemover();
        ResourcesWalker walker = new ResourcesWalker();
        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenThrow(new IOException());

        FileNameValidator validator = new FileNameValidator(walker);
        ScreenWriter writer = new ScreenWriter(walker);

        String validOutput = """
                Hi!
                Choose file to edit:
                1. Another Brick In The Wall.txt
                2. Money.txt
                3. Welcome To The Machine.txt
                4. Wish You Were Here.txt

                Enter file name:\s
                Couldn't read input""";

        //WHEN
        remover.run(reader, walker, validator, writer);
        String toTest = outputStreamCaptor.toString();

        toTest = toTest.trim().replaceAll("\\p{Cntrl}", "");
        validOutput = validOutput.trim().replaceAll("\\p{Cntrl}", "");
        //THEN
        assertEquals(validOutput, toTest);
    }

    @Test
    public void e2eIOTest() throws IOException {
        //GIVEN
        SpacesRemover remover = new SpacesRemover();
        ResourcesWalker walker = new ResourcesWalker();
        String testUserInput = "Foo\nMoney\nn\nMoNEY\ny\n";
        Reader inputString = new StringReader(testUserInput);
        BufferedReader reader = new BufferedReader(inputString);

        FileNameValidator validator = new FileNameValidator(walker);
        ScreenWriter writer = new ScreenWriter(walker);
        URL url = this.getClass().getClassLoader().getResource("validOutput.txt");

        assertNotNull(url);
        String validOutput = IOUtils.toString(url, StandardCharsets.UTF_8);

        //WHEN
        remover.run(reader, walker, validator, writer);
        validOutput = validOutput.trim().replaceAll("\\p{Cntrl}", "");
        String toTest = outputStreamCaptor.toString().trim().replaceAll("\\p{Cntrl}", "");

        //THEN
        assertEquals(validOutput, toTest);
    }
}
