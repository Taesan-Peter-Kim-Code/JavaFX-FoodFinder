/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author taesankim
 */
public class RegisterPageViewController implements Initializable {

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label pwLabel;
    @FXML
    private Label cPWLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField pwTextField;
    @FXML
    private PasswordField cPWTextField;
    @FXML
    private Button registerBtn;
    @FXML
    private Button backBtn;

    public TextField getFirstNameField()
    {
        return firstNameTextField;
    }

    public void setFirstNameField(TextField firstNameField)
    {
        this.firstNameTextField = firstNameTextField;
    }

    public TextField getLastNameField()
    {
        return lastNameTextField;
    }

    public void setLastNameField(TextField lastNameField)
    {
        this.lastNameTextField = lastNameTextField;
    }
    
    public Button getBackBtn()
    {
        return backBtn;
    }

    public void setBackBtn(Button backBtn)
    {
        this.backBtn = backBtn;
    }
    
    public Button getRegisterBtn()
    {
        return registerBtn;
    }

    public void setRegisterBtn(Button registerBtn)
    {
        this.registerBtn = registerBtn;
    }
    
    @FXML
    private void backAction(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginPageView.fxml"));
            Parent loginView = loader.load();

            Scene loginScene = new Scene(loginView);
            Scene currentScene = ((Node)event.getSource()).getScene();

            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(loginScene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
}
