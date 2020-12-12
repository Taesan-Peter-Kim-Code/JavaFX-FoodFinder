/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Event;

public class UpdateEventController {
    
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
    private Button updateButton;
    
    

    @FXML
    void updateEvent(ActionEvent event) throws IOException {
                
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));

        Parent adminView = loader.load();

        Scene tableViewScene = new Scene(adminView);

        AdminViewController adminController = loader.getController();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
        
        Event existingEvent = updateEvent();
        
        adminController.update(existingEvent);
   
    }
    
    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;

    }
    
    
    public void setFields(Event updatedEvent){
        //System.out.printn("Set fields");
        String id = Integer.toString(updatedEvent.getId());
        idField.setText(id);
        eventNameField.setText(updatedEvent.getEventname());
        organizationField.setText(updatedEvent.getOrganization());
        dateField.setText(updatedEvent.getDate());
        timeField.setText(updatedEvent.getTime());
        descriptionField.setText(updatedEvent.getDescription());

    }
    
    public Event updateEvent(){
        
        Event existingEvent = new Event();
        
        String stringID = idField.getText();
        int id = Integer.parseInt(stringID);
        
        existingEvent.setId(id);
        existingEvent.setEventname(eventNameField.getText());
        existingEvent.setOrganization(organizationField.getText());
        existingEvent.setDate(dateField.getText());
        existingEvent.setTime(timeField.getText());
        existingEvent.setDescription(descriptionField.getText());
        
        System.out.println("Is this working?");
        
              
        return existingEvent;
        
    }
    
    public void setId(Event existingEvent){
        
        int id = existingEvent.getId();
  
    }
    

}

