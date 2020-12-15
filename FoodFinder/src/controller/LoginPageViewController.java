/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Usermodel;

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
    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private Label feedbackLabel;
    
    private EntityManager manager;
    
    private Boolean currentUserFlag;
    
    private Usermodel currentUser;
    
    /*
    Getter and setter
    */
    public TextField getEmailField() {
        return emailTextField;
    }
    
    public void setEmailField(TextField emailTextField) {
        this.emailTextField = emailTextField;
    }
    
    public TextField getPWField() {
        return pwTextField;
    }
    
    public void setPWField(PasswordField passwordField) {
        this.pwTextField = pwTextField;
    }
    
    public Button getRegisterButton()
    {
        return registerBtn;
    }

    public void setRegisterButton(Button registerBtn)
    {
        this.registerBtn = registerBtn;
    }

    public Button getSubmitButton()
    {
        return loginBtn;
    }

    public void setSubmitButton(Button loginBtn)
    {
        this.loginBtn = loginBtn;
    }
    
    public EntityManager getManager()
    {
        return manager;
    }

    public void setManager(EntityManager manager)
    {
        this.manager = manager;
    }
    
    public Label getFeedbackLabel()
    {
        return feedbackLabel;
    }

    public void setFeedbackLabel(Label feedbackLabel)
    {
        this.feedbackLabel = feedbackLabel;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setManager((EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager());
    }    
    
    // Load RegisterPageView when the Register button is clicked
    @FXML
    private void toRegister(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RegisterPageView.fxml"));
            Parent registerView = loader.load();

            
            Scene registerPage = new Scene(registerView);
            Scene currentScene = ((Node)event.getSource()).getScene();

            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(registerPage);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    // Get user from searchByEmailAndPassword method using email and password input fields and call login function
    @FXML
    private void toLogin(ActionEvent event) throws IOException
    {
        
        Usermodel user = searchByEmailAndPassword(getEmailField().getText(), getPWField().getText());
        
        if(user != null)
        {
            if (getEmailField().getText().equals("admin") && getPWField().getText().equals("admin")) {
                loginAsAdmin(user, event);
                System.out.println("Logged in as an admin");  
                
            }

            else
            {
                login(user,event);          
            }
        }
    }
    
    // Load AdminView when the returning user from searchByEmailAndPassword is admin account
    private void loginAsAdmin(Usermodel user, ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));
            Parent mainView = loader.load();

            Scene mainScene = new Scene(mainView);
            Scene currentScene = ((Node)event.getSource()).getScene();
                           
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(mainScene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    // Load FreeFoodMainView when the account exists
    private void login(Usermodel user, ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FreeFoodMainView.fxml"));
            Parent mainView = loader.load();
            FreeFoodMainViewController foodController = loader.getController();

            Scene mainScene = new Scene(mainView);
            Scene currentScene = ((Node)event.getSource()).getScene();
            foodController.setCurrentUser(user);
                       
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(mainScene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    // Search email and password from Usermodel table and return the user when the user exists
    private Usermodel searchByEmailAndPassword(String email, String password)
    {
        Query query = getManager().createNamedQuery("Usermodel.findByEmailAndPassword");
        query.setParameter("email", email);
        query.setParameter("password", password);
        
        List<Usermodel> users = query.getResultList();
        
        if(users.size() < 1)
        {
            getFeedbackLabel().setText("User does not exist");
            return null;
        }
        else if (users.size() > 1)
        {
            getFeedbackLabel().setText("More than one user exists");
            return null;
        }
        else
        {
            return users.get(0);
        }    
    }
    
    public void setCurrentUser(Usermodel user){
        
        if(currentUserFlag == true){
            currentUser = user;
        }
    }
    
    public Usermodel getCurrentUser(){
        return currentUser;
    } 
}
