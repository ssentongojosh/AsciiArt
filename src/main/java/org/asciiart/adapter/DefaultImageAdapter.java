package org.asciiart.adapter;

import org.asciiart.analyzer.PixelAnalyzer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DefaultImageAdapter implements ImageAdapter{
    @Override
    public BufferedImage load(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Image file not found: " + path);
        }
        return  ImageIO.read(file);
    }

    @Override
    public int[][] extractGrayScaleMatrix(BufferedImage image) {
        int width = image.getWidth(); // get loaded image width
        int height = image.getHeight(); // get loaded image height
        int [][] grayscale = new int [height][width]; // initialize array that will store grayscale of each pixel

        // iterate through entire image assigning a gray scale to each pixel
        // return grayscale array

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                grayscale[y][x] = (int) (0.299 * r + 0.587 * g + 0.114 * b); // Luminance formula

            }
        }
        return grayscale;
    }
}
