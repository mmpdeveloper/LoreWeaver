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
                loadingIndicator.setVisible(false);
            });

            task.setOnFailed(event -> {
                outputArea.setText("An error occurred.");
                loadingIndicator.setVisible(false);
            });

            new Thread(task).start();
        });
    }

    @FXML
    private void handlePythonCall() {
        
    }
}
