package org.asciiart.factory;

import org.asciiart.analyzer.LinearGrayScaleAnalyzer;
import org.asciiart.analyzer.PixelAnalyzer;

// Factory Pattern to provide analyzers

public class AsciiMapperFactory {
    public PixelAnalyzer getAnalyzer(String type){
        if ("linear".equalsIgnoreCase(type)){
            return new LinearGrayScaleAnalyzer();
        }
        throw new IllegalArgumentException("Unknown analyzer type: " + type);
    }
}