package com.example.loreweaverai;

import javafx.event.ActionEvent;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import java.net.URL;


public class HelloController {
    public VBox rootVBox;
    public Rectangle gtrRec;
    public ProgressIndicator loadingIndicator;

    @FXML private TextField userInput;
    @FXML private TextArea outputArea;

    @FXML private Button btn;
    private PythonCaller caller = new PythonCaller();

    @FXML
    public void initialize() {
        btn.setOnAction(e -> {
            String prompt = userInput.getText();
            // Show the loading indicator
            loadingIndicator.setVisible(true);

            Task<String> task = new Task<>() {
                @Override
                protected String call() {
                    return caller.callPythonScript(prompt);
                }
            };

            task.setOnSucceeded(event -> {
                String result = task.getValue();
                outputArea.setText(result);
                // Hide the loading indicator
                loadingIndicator.setVisible(false);
            });

            // You might want to handle potential exceptions too.
            task.setOnFailed(event -> {
                // Handle the error, for example:
                outputArea.setText("An error occurred.");
                // Hide the loading indicator
                loadingIndicator.setVisible(false);
            });

            // Start the task in a separate thread
            new Thread(task).start();
        });
    }

    @FXML
    private void handlePythonCall() {
        // This method is bound to the button's onAction in the FXML,
        // so it'll be called when the button is pressed.
        // However, since we've set the action in initialize(), this is redundant,
        // but kept for clarity.
    }
}
