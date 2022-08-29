import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class SpacesRemoverTest {
    private SpacesRemover remover;
    private ResourcesWalker walker;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void init() {
        walker = new ResourcesWalker();
        remover = new SpacesRemover();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void e2eIOTest() throws IOException {
        //GIVEN
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
