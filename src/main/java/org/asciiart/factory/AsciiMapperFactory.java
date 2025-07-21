package org.asciiart.factory;

import org.asciiart.analyzer.PixelAnalyzer;

// Factory Pattern to provide analyzers

public class AsciiMapperFactory {
    public PixelAnalyzer getAnalyzer(String type){
        throw new UnsupportedOperationException("Analyzer not implemented yet" + type);
    }
}