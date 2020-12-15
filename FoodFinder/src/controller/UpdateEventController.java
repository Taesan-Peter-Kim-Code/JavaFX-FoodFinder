/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Event;

/**
 * UpdateEventController
 *
 * @author haydenLong
 */

public class UpdateEventController implements Initializable{
    
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
    private Button backBtn;

    private EntityManager myManager;
    
    

    @FXML
    void updateEvent(ActionEvent event) throws IOException {//loads adminView page upon click and calls the update function
                
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));

        Parent adminView = loader.load();

        Scene tableViewScene = new Scene(adminView);

        AdminViewController adminController = loader.getController();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
        
        Event existingEvent = updateEvent();
        
        update(existingEvent);
   
    }
    
    @FXML
    void backAction(ActionEvent event) {//provides the action for the back button on click (loading the admin view)
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {//intializes the persistence unit as the fields are set in another method

        myManager = (EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager();
        
        /*
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        orgColumn.setCellValueFactory(new PropertyValueFactory<>("organization"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
        eventView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
*/
        

}
    
    Scene previousScene;

    public void setPreviousScene(Scene scene) {//sets previous scene
        previousScene = scene;

    }
    
     Event existingEvent;
    
    public void setFields(Event updatedEvent){//sets fields to allow user to alter the existing event, the information is passed in from the admin view
        //System.out.printn("Set fields");
        String id = Integer.toString(updatedEvent.getId());
        idField.setText(id);
        eventNameField.setText(updatedEvent.getEventname());
        organizationField.setText(updatedEvent.getOrganization());
        dateField.setText(updatedEvent.getDate());
        timeField.setText(updatedEvent.getTime());
        descriptionField.setText(updatedEvent.getDescription());
        existingEvent = updatedEvent;
    }
    
   
    public Event updateEvent(){//gets the updated data from the text fields and returns an event object
        
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
    
    public void setId(Event existingEvent){//setter for ID
        
        int id = existingEvent.getId();
  
    }
    
    public void update(Event model) {//updates the Database
        try {

            Event existingEvent = myManager.find(Event.class, model.getId());

            if (existingEvent != null) {
                // begin transaction
                myManager.getTransaction().begin();
                
                // update all atttributes
                existingEvent.setId(model.getId());
                existingEvent.setEventname(model.getEventname());
                existingEvent.setOrganization(model.getOrganization());
                existingEvent.setDate(model.getDate());
                existingEvent.setTime(model.getTime());
                existingEvent.setDescription(model.getDescription());
                
                // end transaction
                myManager.getTransaction().commit();
                
                System.out.println(existingEvent.toString() + " is updated");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    

    

}

