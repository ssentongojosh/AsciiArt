package org.asciiart.analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearGrayScaleAnalyzerTest {
    private final PixelAnalyzer analyzer = new LinearGrayScaleAnalyzer();

    @Test
     void testAnalyze(){
        int [][]  grayscale = {{0,128,255}};
        char [][] ascii = analyzer.analyze(grayscale);
        assertEquals('@',ascii[0][0],"Darkest pixel should map to @");
        assertEquals('+',ascii[0][1],"mid pixel should map to -");
        assertEquals(' ',ascii[0][2],"lightest pixel should map to ' '");
    }
}
