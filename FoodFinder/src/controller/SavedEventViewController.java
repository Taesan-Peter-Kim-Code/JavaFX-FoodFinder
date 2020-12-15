package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Event;
import model.Savedevent;
import model.Usermodel;

/**
 * SavedEventViewController
 *
 * @author haydenLong
 */

public class SavedEventViewController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private URL location;

    @FXML
    private Button myEventsButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button deleteEventButton;

    @FXML
    private Button homeButton;
    
    @FXML
    private TableView<Event> savedEventView;
    
    @FXML
    private TableColumn<Event, Integer> idColumn;

    @FXML
    private TableColumn<Event, String> eventNameColumn;

    @FXML
    private TableColumn<Event, String> organizationColumn;

    @FXML
    private TableColumn<Event, String> dateColumn;

    @FXML
    private TableColumn<Event, String> timeColumn;

    @FXML
    private TableColumn<Event, String> descriptionColumn;
    
    @FXML
    private Button readButton;
    
    private Usermodel currentUser;

    private EntityManager myManager;
    
    private List<Savedevent> savedEventList;
    
    private ObservableList <Event> eventData;
    
    
    
    public void setTableData(List<Savedevent> savedEventList) {//takes in the saved events for the logged in user, and loads the corresponding entities from the events table to show the user's saved events
        
        eventData = FXCollections.observableArrayList();
        
        for(Savedevent s: savedEventList){
            int eventID = s.getEventid();
            Event addedEvent = readEventbyID(eventID);
            eventData.add(addedEvent); 
        }
        savedEventView.setItems(eventData);
        savedEventView.refresh();
       
        
    }
    
    @FXML
    void readSavedEvents(ActionEvent event) {//similar to the setTableData method, but it works to refresh the page
        Usermodel  currentPerson = getCurrentUser();
        int currentUserID = currentPerson.getId();
        savedEventList = readByUserID(currentUserID);
        setTableData(savedEventList);
    }


    @FXML
    void showView(ActionEvent event) throws IOException {//navBar functions for loading other pages
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
    void deleteEvent(ActionEvent event) {//handling delete event button actiog, recongizing selected event, and calling the delete from database method
        Event e = savedEventView.getSelectionModel().getSelectedItem();
        int eventId = e.getId();
        
        Usermodel user = getCurrentUser();
        int userID = user.getId();
        Savedevent toDelete = readEventIDAndUserID(eventId, userID);
        
        delete(toDelete);
   
    }

    @FXML
    void initialize() {//intializes nav bar
        assert myEventsButton != null : "fx:id=\"myEventsButton\" was not injected: check your FXML file 'SavedEventView.fxml'.";
        assert logOutButton != null : "fx:id=\"logOutButton\" was not injected: check your FXML file 'SavedEventView.fxml'.";
        assert deleteEventButton != null : "fx:id=\"deleteEventButton\" was not injected: check your FXML file 'SavedEventView.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'SavedEventView.fxml'.";

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//intializes savedEvent view

        myManager = (EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        organizationColumn.setCellValueFactory(new PropertyValueFactory<>("organization"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        savedEventView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

}
    public List<Savedevent> readAll(){//reads all the data from the savedEvent table
        savedEventView.refresh();
        Query query = myManager.createNamedQuery("Savedevent.findAll");
        List<Savedevent> events = query.getResultList();
        
        return events;
    }
    
    public List<Savedevent> readByUserID(Integer userid){//find savedEvents based on the userID of the current user
        
        savedEventView.refresh();
        Query query = myManager.createNamedQuery("Savedevent.findByUserid");
        query.setParameter("userid", userid);
        List<Savedevent> savedEvents = query.getResultList();
        
        return savedEvents;
    }
    

    public Savedevent readEventIDAndUserID(Integer eventid, Integer userid) {//finds an entry in the savedEvents table by both the eventID and the userID to avoid unwanted deleting
        
        Query query = myManager.createNamedQuery("Savedevent.findByEventidAndUserid");
        query.setParameter("eventid", eventid);
        query.setParameter("userid", userid);
        Savedevent foundEvent = new Savedevent();
        foundEvent = (Savedevent) query.getSingleResult();
        
        return foundEvent;

    }
    
    public Event readEventbyID(int eventID){//finds events in the event table by ID once it is passed in
        
        Event foundEvent = new Event();
        Query query = myManager.createNamedQuery("Event.findById");
        query.setParameter("id", eventID);
        foundEvent = (Event) query.getSingleResult();
        
        return foundEvent;
    }
  
    public void delete(Savedevent selectedEvent) {//deletes events from the database
        try {
           
                myManager.getTransaction().begin();
                
    
                myManager.remove(selectedEvent);
                
      
                myManager.getTransaction().commit();
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
     public Integer createID(){//creates an ID for an event or savedEvent
        
        int id = 0;
        
        List <Savedevent> eventList;
        
        if(id != 0) {
            eventList = readAll();
        
            if(eventList != null){
            
            for(Savedevent e: eventList) {
            
                id++;
            
                if(id == e.getId()){
                    id++;
                }
            }
        
            } else {
                id++;
            }
        } else {
            id++;
        }   
        return id;     
    }
     
    public void setCurrentUser(Usermodel user){//setter for current logged in user
            currentUser = user;
    }
      
    public Usermodel getCurrentUser(){//getter for current logged in user
        return currentUser;
    }
}
