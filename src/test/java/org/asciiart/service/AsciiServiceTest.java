package org.asciiart.service;

import org.asciiart.adapter.ImageAdapter;
import org.asciiart.analyzer.PixelAnalyzer;
import org.asciiart.factory.AsciiMapperFactory;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AsciiServiceTest {
    @Test
    void testConvertToAscii() throws Exception {
        ImageAdapter adapter = mock(ImageAdapter.class);
        AsciiMapperFactory factory = mock(AsciiMapperFactory.class);
        PixelAnalyzer analyzer = mock(PixelAnalyzer.class);
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        when(adapter.load(anyString())).thenReturn(image);
        when(adapter.extractGrayScaleMatrix(any())).thenReturn(new int[50][80]);
        when(factory.getAnalyzer("linear")).thenReturn(analyzer);
        when(analyzer.analyze(any())).thenReturn(new char[50][80]);

        AsciiService service = new AsciiService(adapter, factory);
        char[][] result = service.convertToAscii("test.png", "linear", 80, 0.5);
        verify(adapter).load("test.png");
        verify(factory).getAnalyzer("linear");
        verify(analyzer).analyze(any());
        assertEquals(50, result.length, "Height should be 50 (adjusted for aspect ratio)");
        assertEquals(80, result[0].length, "Width should be 80");
    }
}
