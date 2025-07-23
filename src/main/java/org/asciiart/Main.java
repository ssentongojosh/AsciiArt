package org.asciiart;

import org.asciiart.adapter.DefaultImageAdapter;
import org.asciiart.controller.AsciiConverterController;
import org.asciiart.factory.AsciiMapperFactory;
import org.asciiart.service.AsciiService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java -jar ascii-converter.jar <imagePath> <analyzerType> <targetWidth> [aspectRatioFactor] [outputPath]");
            return;
        }
        try {
            String imagePath = args[0];
            String analyzerType = args[1];
            int targetWidth = Integer.parseInt(args[2]);
            double aspectRatioFactor = args.length > 3 ? Double.parseDouble(args[3]) : 0.5;
            String outputPath = args.length > 4 ? args[4] : null;

            DefaultImageAdapter adapter = new DefaultImageAdapter();
            AsciiMapperFactory factory = new AsciiMapperFactory();
            AsciiService service = new AsciiService(adapter, factory);
            AsciiConverterController controller = new AsciiConverterController(service);
            controller.convertAndOutput(imagePath, analyzerType, targetWidth, aspectRatioFactor, outputPath);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}