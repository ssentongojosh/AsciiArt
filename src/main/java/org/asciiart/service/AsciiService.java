package org.asciiart.service;

import org.asciiart.adapter.ImageAdapter;
import org.asciiart.analyzer.PixelAnalyzer;
import org.asciiart.factory.AsciiMapperFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AsciiService {
    private final ImageAdapter imageAdapter;
    private final AsciiMapperFactory factory;

    public AsciiService(ImageAdapter imageAdapter, AsciiMapperFactory factory) {
        this.imageAdapter = imageAdapter;
        this.factory = factory;
    }

    public char[][] convertToAscii(String imagePath, String analyzerType, int targetWidth, double aspectRatioFactor) throws Exception {
        BufferedImage image = imageAdapter.load(imagePath);
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        // Adjust for character aspect ratio (characters are taller than wide)
        int targetHeight = (int) (originalHeight * ((double) targetWidth / originalWidth) * aspectRatioFactor);
        BufferedImage scaledImage = scaleImage(image, targetWidth,targetHeight);
        int [][] grayscale = imageAdapter.extractGrayScaleMatrix(scaledImage);
        PixelAnalyzer analyzer = factory.getAnalyzer(analyzerType);
        return analyzer.analyze(grayscale);
    }

    private BufferedImage scaleImage(BufferedImage original, int targetWidth, int targetHeight){
        BufferedImage scaled = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        scaled.getGraphics().drawImage(original.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_AREA_AVERAGING), 0, 0, targetWidth, targetHeight, null);
        return scaled;
    }
}
