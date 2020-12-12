/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.media.jfxmediaimpl.platform.PlatformManager.getManager;
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
    
    private EntityManager manager;

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(TextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(TextField lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(TextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public PasswordField getPwTextField() {
        return pwTextField;
    }

    public void setPwTextField(PasswordField pwTextField) {
        this.pwTextField = pwTextField;
    }

    public PasswordField getcPWTextField() {
        return cPWTextField;
    }

    public void setcPWTextField(PasswordField cPWTextField) {
        this.cPWTextField = cPWTextField;
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
    
    public EntityManager getManager()
    {
        return manager;
    }

    public void setManager(EntityManager manager)
    {
        this.manager = manager;
    }
    
    @FXML
    private void backAction(ActionEvent event) 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPageView.fxml"));
            Parent loginView = loader.load();

            Scene loginPage = new Scene(loginView);
            Scene currentScene = ((Node)event.getSource()).getScene();

            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(loginPage);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void toRegister(ActionEvent event) 
    {
        createUser(getFirstNameTextField().getText(),getLastNameTextField().getText(), getEmailTextField().getText(), getPwTextField().getText());
        
    }
    
    private void createUser(String firstname, String lastname, String email, String password)
    {
        try
        {
            Query query = getManager().createNamedQuery("Usermodel.findAll");
            List<Usermodel> users = query.getResultList();
            int id = 1;
            
            if (!users.isEmpty())
            {
                if (getPwTextField().getText().equals(getcPWTextField().getText()))
                {
                    id = users.size() + 1;
                }
            }
            
            Usermodel user = new Usermodel();
            user.setId(id);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPassword(password);
            
            if (!searchUser(email)) 
            {
                getManager().getTransaction().begin();
                
                getManager().persist(user);
                getManager().getTransaction().commit();
            }
            
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
        setManager((EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager());
    }

    private boolean searchUser(String email) {
        
        boolean userFound = false;
        
        try 
        {
            Query query = getManager().createNamedQuery("Usermodel.findByEmail");
            query.setParameter("email", email);
            Usermodel users = (Usermodel) query.getSingleResult();
            userFound = true;
            System.out.println("Registration Failed");
            
            
        } catch (Exception e) {
            userFound = false;
            System.out.println("Registration Succeed");
        }
        return userFound;
    }
     
}
