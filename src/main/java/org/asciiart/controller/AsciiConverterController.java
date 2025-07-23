package org.asciiart.controller;

import org.asciiart.service.AsciiService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AsciiConverterController {
    private final AsciiService asciiService;

    public AsciiConverterController(AsciiService asciiService) {
        this.asciiService = asciiService;
    }

    public void convertAndOutput(String imagePath, String analyzerType, int targetWidth,double aspectRatioFactor, String outputPath) throws Exception {
        char[][] asciiArt = asciiService.convertToAscii(imagePath, analyzerType, targetWidth, aspectRatioFactor);
        if (outputPath == null || outputPath.isEmpty()) {
            // Console output
            for (char[] row : asciiArt) {
                System.out.println(row);
            }
        } else {
            // File output
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                for (char[] row : asciiArt) {
                    writer.write(row);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new Exception("Failed to write ASCII art to file: " + outputPath, e);
            }
        }
    }
}
