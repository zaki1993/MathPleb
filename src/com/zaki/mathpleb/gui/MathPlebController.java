package com.zaki.mathpleb.gui;

import com.zaki.mathpleb.internal.Parser;
import com.zaki.mathpleb.internal.tree.Node;
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

        Node tree = parser.parse(input);
        // print the tree onto the screen as a tree image
        generateTreeImage(tree);

        // compute the tree value and show it on the screen
        String result = parser.compute(tree);
        outputData.setText(result);
    }
}
