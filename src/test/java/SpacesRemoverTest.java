import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

public class SpacesRemoverTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void e2eIOTest() throws IOException {
        //GIVEN
        String testUserInput = "Foo\nMoney\nn\nMoNEY\ny\n";
        InputStream inStream = new ByteArrayInputStream(testUserInput.getBytes());
        OutputStream output = new ByteArrayOutputStream();
        Application app = new Application(inStream, output);

        URL url = this.getClass().getClassLoader().getResource("validOutput.txt");
        assertNotNull(url);
        String validOutput = IOUtils.toString(url, StandardCharsets.UTF_8);

        //WHEN

        app.run();

        //THEN
        validOutput = validOutput.trim().replaceAll("\\p{Cntrl}", "");
        String outStr = output.toString().trim().replaceAll("\\p{Cntrl}", "");
        assertEquals(outStr, validOutput);
    }
}
