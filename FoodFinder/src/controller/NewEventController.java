/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author haydenLong
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Event;
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
    
    @FXML    
    private Button backBtn;
    
   // private AdminViewController mainAdminController;
    @FXML
    void createEvent(ActionEvent event) throws IOException{//loads the admin view once the user clicks the "save" button and calls create entry function for database
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
        //System.out.println("Dude");
        
        int id = adminController.createID();
        
        //System.out.println("Before issues");
        System.out.println(id);
        
        newEvent = createEntry(id);
               
        adminController.create(newEvent);
        
    }
    
    @FXML
    void backAction(ActionEvent event) {//loads adminView on action
          try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));
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

    Scene previousScene;

    public void setPreviousScene(Scene scene) {//sets the previous scene
        previousScene = scene;

    }
   
    public Event createEntry(int id) throws IOException {//gets information from view text fields and returns the newEvent
        
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

    public TextField getIdField() {//getter for ID text field
        return idField;
    }

    public void setIdField(TextField idField) {//setter for ID text field
        this.idField = idField;
    }

    public TextField getEventNameField() {//getter for event name text field
        return eventNameField;
    }

    public void setEventNameField(TextField eventNameField) {//setter for event name text field
        this.eventNameField = eventNameField;
    }

    public TextField getOrganizationField() {//getter for organization text field
        return organizationField;
    }

    public void setOrganizationField(TextField organizationField) {//setter for organization text field
        this.organizationField = organizationField;
    }

    public TextField getDateField() {//getter for date text field
        return dateField;
    }

    public void setDateField(TextField dateField) {//setter for date text fie
        this.dateField = dateField;
    }

    public TextField getTimeField() {//getter for time text field
        return timeField;
    }

    public void setTimeField(TextField timeField) {//setter for time text field
        this.timeField = timeField;
    }

    public TextArea getDescriptionField() {//getter for description text field
        return descriptionField;
    }

    public void setDescriptionField(TextArea descriptionField) {//setter for description text field
        this.descriptionField = descriptionField;
    }

    public Button getSaveButton() {//getter for save button
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {//setter for save button
        this.saveButton = saveButton;
    }
    

}
