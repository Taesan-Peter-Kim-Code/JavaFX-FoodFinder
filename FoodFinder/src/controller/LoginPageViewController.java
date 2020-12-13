/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    
    private EntityManager manager;
    
    
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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setManager((EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager());
    }    
    
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
    
    @FXML
    private void toLogin(ActionEvent event)
    {
        
        Usermodel user = searchByEmailAndPassword(getEmailField().getText(), getPWField().getText());
        
        if(user != null)
        {
            if (getEmailField().getText().equals("admin") && getPWField().getText().equals("admin")) {
                loginAsAdmin(user, event);
            }
            else {
                login(user, event);
            }
        }
    }
    
    private void loginAsAdmin(Usermodel user, ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));
            Parent mainView = loader.load();

            Scene mainScene = new Scene(mainView);
            Scene currentScene = ((Node)event.getSource()).getScene();
            
//            AdminViewController adminController = loader.getController();
//            adminController.setPreviousScene(currentScene);
               
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(mainScene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void login(Usermodel user, ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FreeFoodMainView.fxml"));
            Parent mainView = loader.load();

            Scene mainScene = new Scene(mainView);
            Scene currentScene = ((Node)event.getSource()).getScene();
            
//            FreeFoodMainViewController mainController = loader.getController();
//            mainController.setPreviousScene(currentScene);
               
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(mainScene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    private Usermodel searchByEmailAndPassword(String email, String password)
    {
        Query query = getManager().createNamedQuery("Usermodel.findByEmailAndPassword");
        query.setParameter("email", email);
        query.setParameter("password", password);
        
        List<Usermodel> users = query.getResultList();
        
        if(users.size() < 1)
        {
            System.out.println("No user exists");
            return null;
        }
        else if(users.size() > 1)
        {
            System.out.println("fsda");
            return null;
        }
        else
        {
            return users.get(0);
        }
        
    }
/*
    public Integer setCurrentUserID(){
        Usermodel user = searchByEmailAndPassword(getEmailField().getText(), getPWField().getText());
        int userId = user.getId();
        
        return userId;
    }
*/
    
}
