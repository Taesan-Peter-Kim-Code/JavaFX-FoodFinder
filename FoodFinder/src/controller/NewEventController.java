/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author hayde
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Event;
import controller.FreeFoodMainViewController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewEventController {

    @FXML
    private TextField idField;

    @FXML
    private TextField eventNameField;

    @FXML
    private TextField organizationField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField timeField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button saveButton;
    
   // private AdminViewController mainAdminController;
    

    @FXML
    void createEvent(ActionEvent event) throws IOException{
        System.out.println("Creating entry");
        
        Event newEvent = new Event();

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));

        Parent adminView = loader.load();

        Scene tableViewScene = new Scene(adminView);

        AdminViewController adminController = loader.getController();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
        System.out.println("Dude");
        
        int id = adminController.createID();
        
        System.out.println("Before issues");
        System.out.println(id);
        
       newEvent = createEntry(id);
        
          
        adminController.create(newEvent);
        
        
        /*
        
        id = this.id;
        newEvent = this.newEvent;
        String eventName = eventNameField.getText();
        String organization = organizationField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String description = descriptionField.getText();
        
        newEvent.setEventname(eventName);
        newEvent.setOrganization(organization);
        newEvent.setDate(date);
        newEvent.setTime(time);
        newEvent.setDescription(description);
               
        mainAdminController.create(newEvent);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));

        Parent adminView = loader.load();

        Scene tableViewScene = new Scene(adminView);

        AdminViewController adminController = loader.getController();
*/
    }

    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;

    }

    
    public Event createEntry(int id) throws IOException {
        

        
        //AdminViewController mainAdminController = this.mainAdminController;
        
        
        Event newEvent = new Event();
      
        
        newEvent.setId(id);
        

        String eventName = eventNameField.getText();
        String organization = organizationField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String description = descriptionField.getText();
        
        newEvent.setId(id);        
        newEvent.setEventname(eventName);
        newEvent.setOrganization(organization);
        newEvent.setDate(date);
        newEvent.setTime(time);
        newEvent.setDescription(description);
               
       
        
        return newEvent;
    }

    public TextField getIdField() {
        return idField;
    }

    public void setIdField(TextField idField) {
        this.idField = idField;
    }

    public TextField getEventNameField() {
        return eventNameField;
    }

    public void setEventNameField(TextField eventNameField) {
        this.eventNameField = eventNameField;
    }

    public TextField getOrganizationField() {
        return organizationField;
    }

    public void setOrganizationField(TextField organizationField) {
        this.organizationField = organizationField;
    }

    public TextField getDateField() {
        return dateField;
    }

    public void setDateField(TextField dateField) {
        this.dateField = dateField;
    }

    public TextField getTimeField() {
        return timeField;
    }

    public void setTimeField(TextField timeField) {
        this.timeField = timeField;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }

    public void setDescriptionField(TextArea descriptionField) {
        this.descriptionField = descriptionField;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }
    

}
