package org.asciiart.analyzer;

// Strategy for mapping grayscale to ASCII

public interface PixelAnalyzer {
    char [][] analyze(int [][] grayscale);
}
