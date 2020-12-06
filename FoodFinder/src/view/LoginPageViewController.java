/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author taesankim
 */
public class LoginPageViewController implements Initializable {

    @FXML
    private Label loginPageLabel;
    @FXML
    private Label userEmailLabel;
    @FXML
    private Label pwLabel;
    @FXML
    private PasswordField pwTextField;
    @FXML
    private TextField emailTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
