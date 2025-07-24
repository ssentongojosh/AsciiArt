package org.asciiart.view;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.asciiart.adapter.DefaultImageAdapter;
import org.asciiart.controller.AsciiConverterController;
import org.asciiart.factory.AsciiMapperFactory;
import org.asciiart.service.AsciiService;

public class AsciiConverterView extends Application {
    private static String imagePath;
    private static String analyzerType;
    private static int targetWidth;
    private static double aspectRatioFactor;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java -jar ascii-converter.jar <imagePath> <analyzerType> <targetWidth> [aspectRatioFactor]");
            System.err.println("Example: sample.png linear 150 0.6");
            return;
        }
        imagePath = args[0];
        analyzerType = args[1];
        targetWidth = Integer.parseInt(args[2]);
        aspectRatioFactor = args.length > 3 ? Double.parseDouble(args[3]) : 0.6;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize controller and dependencies
            DefaultImageAdapter adapter = new DefaultImageAdapter();
            AsciiMapperFactory factory = new AsciiMapperFactory();
            AsciiService service = new AsciiService(adapter, factory);
            AsciiConverterController controller = new AsciiConverterController(service);

            // Generate ASCII art
            char[][] asciiArt = controller.convertAndOutput(imagePath, analyzerType, targetWidth, aspectRatioFactor, null);

            // Create JavaFX UI
            TextArea textArea = new TextArea();
            textArea.setFont(javafx.scene.text.Font.font("Consolas", 8));
            textArea.setEditable(false);
            textArea.setWrapText(false);

            // Convert char[][] to String
            StringBuilder sb = new StringBuilder();
            for (char[] row : asciiArt) {
                sb.append(row).append("\n");
            }
            textArea.setText(sb.toString());

            // Configure TextArea to expand properly
            textArea.setPrefRowCount(50);
            textArea.setPrefColumnCount(200);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);

            // Add ScrollPane for large outputs
            ScrollPane scrollPane = new ScrollPane(textArea);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            scrollPane.setMaxWidth(Double.MAX_VALUE);
            scrollPane.setMaxHeight(Double.MAX_VALUE);

            // Set up layout with proper expansion
            VBox root = new VBox(scrollPane);
            root.setFillWidth(true);
            root.setMaxWidth(Double.MAX_VALUE);
            root.setMaxHeight(Double.MAX_VALUE);
            VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);
            
            // Get screen bounds for full screen size
            javafx.geometry.Rectangle2D screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
            Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
            primaryStage.setTitle("ASCII Art Converter");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(400); // Minimum width to ensure usability
            primaryStage.setMinHeight(300); // Minimum height to ensure usability
            
            // Add full screen support
            primaryStage.setMaximized(true); // Start maximized
            
            // Add keyboard shortcut for toggling full screen (F11)
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == javafx.scene.input.KeyCode.F11) {
                    primaryStage.setFullScreen(!primaryStage.isFullScreen());
                }
            });
            
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
