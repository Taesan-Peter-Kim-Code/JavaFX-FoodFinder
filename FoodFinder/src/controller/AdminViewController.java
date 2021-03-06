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
import java.io.IOException;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 * AdminViewController
 *
 * @author haydenLong
 */
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
    
    private Event selectedEvent;
    
    public void setTableData(List<Event> eventList) {
        
        eventData = FXCollections.observableArrayList();
        

        eventList.forEach(s -> {
            eventData.add(s);
        });
        
        eventView.setItems(eventData);
        eventView.refresh();
 
    }
    
    @FXML
    void showView(ActionEvent event) throws IOException {
        String view = (String) ((Node) event.getSource()).getUserData();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        
        Parent root = loader.load();
        
        Scene saveScene = new Scene(root);
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        
        Stage stage = (Stage) currentScene.getWindow();
        
        stage.setScene(saveScene);
        stage.show();
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
        
        eventView.refresh();
    }

    @FXML
    void deleteEvent(ActionEvent event) {//selects event and calls delete method
        
       Event e = eventView.getSelectionModel().getSelectedItem();
       
       System.out.println("we are deleting this student: "+ e.toString());
       delete(e);

    }

    @FXML
    void readEvents(ActionEvent event) {//handles button to refresh data and calls method
       
        events = readAll();
       
        setTableData(events);       
    }

    @FXML
    void updateEvent(ActionEvent event) throws IOException {//pulls up update event view and sets fields
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateEventView.fxml"));

        Parent newEventView = loader.load();

        Scene tableViewScene = new Scene(newEventView);

        UpdateEventController updateController = loader.getController();
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        updateController.setPreviousScene(currentScene);
        
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(tableViewScene);
        stage.show();
        
        Event updateEvent = eventView.getSelectionModel().getSelectedItem();
        
        updateController.setFields(updateEvent);
              
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {//intializes admin view

        myManager = (EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        orgColumn.setCellValueFactory(new PropertyValueFactory<>("organization"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
         
        eventView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
    
    public void create(Event newEvent) {//creates a new event in the database
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
    
    public List<Event> readAll(){//queries the database and loads all data
        Query query = myManager.createNamedQuery("Event.findAll");
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        List<Event> events = query.getResultList();
        
        return events;
    }
    
    public void delete(Event selectedEvent) {//deletes entries from the database
        try {
                myManager.getTransaction().begin();

                myManager.remove(selectedEvent);

                myManager.getTransaction().commit();
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Integer createID(){//creates an ID for an event based on the existing table
        
        int id = 0;
        
        List <Event> eventList;
        
        eventList = readAll();
        
        if(eventList != null){
            for(Event e: eventList){
            
                id++;
            
                if(id == e.getId()){
                    id++;
                }
            }
        }
        else{
        id++;
        }
        return id;
    }   
}

