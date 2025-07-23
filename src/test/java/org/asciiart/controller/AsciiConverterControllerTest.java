package org.asciiart.controller;

import org.asciiart.service.AsciiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AsciiConverterControllerTest {
    @Test
    void testConvertAndOutputToConsole(@TempDir Path tempDir) throws Exception {
        AsciiService service = mock(AsciiService.class);
        char[][] asciiArt = {{'@', '+'}, {'-', ' '}};
        when(service.convertToAscii(anyString(), anyString(), anyInt(), anyDouble())).thenReturn(asciiArt);

        AsciiConverterController controller = new AsciiConverterController(service);
        controller.convertAndOutput("test.png", "linear", 80, 0.5, null);

        verify(service).convertToAscii("test.png", "linear", 80, 0.5);
    }

    @Test
    void testConvertAndOutputToFile(@TempDir Path tempDir) throws Exception {
        AsciiService service = mock(AsciiService.class);
        char[][] asciiArt = {{'@', '+'}, {'-', ' '}};
        when(service.convertToAscii(anyString(), anyString(), anyInt(), anyDouble())).thenReturn(asciiArt);

        File outputFile = tempDir.resolve("output.txt").toFile();
        AsciiConverterController controller = new AsciiConverterController(service);
        controller.convertAndOutput("test.png", "linear", 80, 0.5, outputFile.getPath());

        verify(service).convertToAscii("test.png", "linear", 80, 0.5);
        String content = Files.readString(outputFile.toPath()).replaceAll("\\r\\n|\\r|\\n", "\n");
        assertEquals("@+\n- \n", content, "File should contain ASCII art");
    }
}
