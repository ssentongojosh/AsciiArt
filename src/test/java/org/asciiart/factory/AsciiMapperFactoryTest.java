package org.asciiart.factory;

import org.asciiart.analyzer.LinearGrayScaleAnalyzer;
import org.asciiart.analyzer.PixelAnalyzer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AsciiMapperFactoryTest {
    private AsciiMapperFactory factory =  new AsciiMapperFactory();

    @Test
    void testGetAnalyzerLinear(){
        PixelAnalyzer analyzer = factory.getAnalyzer("linear");
        assertTrue(analyzer instanceof LinearGrayScaleAnalyzer, "Should return LinearGrayScaleAnalyzer");
    }

    @Test
    void testGetAnalyzerInvalidType(){
        assertThrows(IllegalArgumentException.class,()->factory.getAnalyzer("invalid"),"should throw exception for invalid analyzer type");
    }
}
