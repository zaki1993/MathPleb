package com.zaki.mathpleb.gui;

import com.zaki.mathpleb.internal.Parser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MathPlebController implements Initializable {

    @FXML
    private TextField inputData;

    @FXML
    private TextField outputData;

    @FXML
    private TextField interpretedInput;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onSubmitInput(ActionEvent ae) {
        String input = inputData.getText();
        Parser parser = new Parser();

        // return something like syntactic tree on parsing it
        String result = parser.parse(input);

        outputData.setText(result);

        // TODO visualize the interpreted input

        // TODO compute the input

        // TODO visualize the solution
    }
}
