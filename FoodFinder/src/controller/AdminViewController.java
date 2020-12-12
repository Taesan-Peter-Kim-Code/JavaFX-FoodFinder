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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Event;
import controller.NewEventController;
import java.io.IOException;
import javafx.scene.Node;
import javafx.stage.Stage;


public class AdminViewController implements Initializable {
    
     @FXML
    private TableView<Event> eventView;

    @FXML
    private TableColumn<Event, Integer> idColumn;

    @FXML
    private TableColumn<Event, String> eventNameColumn;

    @FXML
    private TableColumn<Event, String> orgColumn;

    @FXML
    private TableColumn<Event, String> dateColumn;

    @FXML
    private TableColumn<Event, String> timeColumn;

    @FXML
    private TableColumn<Event, String> desColumn;

    @FXML
    private Button createButton;

    @FXML
    private Button readButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;
    
    private EntityManager myManager;
    
    private ObservableList <Event> eventData;
    
    private List<Event> events;
    
    
    public void setTableData(List<Event> eventList) {
        
        eventData = FXCollections.observableArrayList();
        

        eventList.forEach(s -> {
            eventData.add(s);
        });
        
        eventView.setItems(eventData);
        eventView.refresh();
       
        
    }

    @FXML
    void createButtonAction(ActionEvent event) throws IOException {
        
        System.out.println("Creating new event");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewEventView.fxml"));

        Parent newEventView = loader.load();

        Scene tableViewScene = new Scene(newEventView);

        NewEventController newEventController = loader.getController();
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        newEventController.setPreviousScene(currentScene);
        
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(tableViewScene);
        stage.show();
        
        //createButtonLogic();
        
        
        /*
        System.out.println("Creating new event");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewEventView.fxml"));

        Parent newEventView = loader.load();

        Scene tableViewScene = new Scene(newEventView);

        NewEventController newEventController = loader.getController();
        
        Stage stage = new Stage();
        stage.setScene(tableViewScene);
        stage.show();
        
        int id = 0; 
        
        Event newEvent = new Event();
        
        for(Event e: eventData){
            if(id == e.getId()){
                id++;
            }
        }
        newEvent.setId(id);
        
        newEventController.createEvent(event, id, newEvent);
        /*
        newEvent.setEventname(eventName);
        newEvent.setOrganization(organization);
        newEvent.setDate(date);
        newEvent.setTime(time);
        newEvent.setDescription(description);
               
        create(newEvent);
*/
        eventView.refresh();

    }

    @FXML
    void deleteEvent(ActionEvent event) {
        
       Event e = eventView.getSelectionModel().getSelectedItem();
       
       System.out.println("we are deleting this student: "+ e.toString());
       delete(e);

    }

    @FXML
    void readEvents(ActionEvent event) {
        events = readAll();
        
        setTableData(events);
        
    }

    @FXML
    void updateEvent(ActionEvent event) {

    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {

        myManager = (EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        orgColumn.setCellValueFactory(new PropertyValueFactory<>("organization"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
        eventView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        

}
    public void create(Event newEvent) {
        try {
            myManager.getTransaction().begin();
            
            if (newEvent.getId() != null) {
                
                myManager.persist(newEvent);
                
                myManager.getTransaction().commit();
                
                System.out.println(newEvent.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Event> readAll(){
        Query query = myManager.createNamedQuery("Event.findAll");
        List<Event> events = query.getResultList();
        
        return events;
    }
    
    public void delete(Event selectedEvent) {
        try {
           
                myManager.getTransaction().begin();
                
    
                myManager.remove(selectedEvent);
                
      
                myManager.getTransaction().commit();
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
    public void createButtonLogic() throws IOException{//This handles the passing to the new view and setting the ID
        System.out.println("Creating new event");
        
        
        
        
        /*
        
        int id = 0; 
        
        Event newEvent = new Event();
        
        for(Event e: eventData){
            if(id == e.getId()){
                id++;
            }
        }
        newEvent.setId(id);
        
        newEventController.createEntry(id, newEvent);
*/
    /*
    }
*/
    
    public Integer createID(){
        
        int id = 0;
        
        for(Event e: eventData){
            if(id == e.getId()){
                id++;
            }
        }
        return id;
    }
    
  
}

