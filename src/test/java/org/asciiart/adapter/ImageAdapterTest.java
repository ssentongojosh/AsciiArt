package org.asciiart.adapter;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageAdapterTest {
    private final ImageAdapter adapter = new DefaultImageAdapter();
    @Test
    void testLoadImageInvalidPath(){
        assertThrows(IllegalArgumentException.class, ()-> adapter.load("non_existent.png"),"Should throw Exception for invalid path");
    }

    @Test
    void testExtractGrayScaleMatrix(){
        BufferedImage image = new BufferedImage(2,2,BufferedImage.TYPE_INT_RGB);
        image.setRGB(0,0,0xffffff); // white
        image.setRGB(1,0,0xffffff); // Black
        int [][] grayscale = adapter.extractGrayScaleMatrix(image);
        assertEquals(255,grayscale[0][0],"White pixel maps to 255");
        assertEquals(255,grayscale[0][1],"Black pixel maps to 0");
    }
}
