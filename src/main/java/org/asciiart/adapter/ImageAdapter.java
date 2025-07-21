package org.asciiart.adapter;

import java.awt.image.BufferedImage;

// Handles image loading and grayscale extraction

public interface ImageAdapter {
    BufferedImage load(String path) throws Exception;
    int [][] extractGrayScaleMatrix(BufferedImage image);
}
