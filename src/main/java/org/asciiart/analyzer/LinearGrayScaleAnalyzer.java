package org.asciiart.analyzer;

public class LinearGrayScaleAnalyzer implements PixelAnalyzer {
    private static final String ASCII_CHARS = "@#$%*+=-:.";

    @Override
    public char[][] analyze(int[][] grayscale) {
        int height = grayscale.length;
        int width = grayscale[0].length;
        char [][] asciiArt = new char[height][width];

        for  (int y = 0; y < height; y++) {
            for  (int x = 0; x < width; x++) {
                int grayValue = grayscale[y][x];
                int index = (grayValue*(ASCII_CHARS.length()-1))/255;
                asciiArt[y][x] = ASCII_CHARS.charAt(index);
            }
        }
        return asciiArt;
    }
}
