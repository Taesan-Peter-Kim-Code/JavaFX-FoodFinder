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
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Event;
import model.Savedevent;
import model.Usermodel;
import controller.LoginPageViewController;

/**
 *
 * @author haydenLong
 */
public class FreeFoodMainViewController implements Initializable {
    
    @FXML
    private TableView<Event> eventTable;

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
    private Button loadBtn;

    @FXML
    private Button saveBtn;
    
    private EntityManager myManager;
    
    private ObservableList<Event> eventData;
    
    private List<Event> events;
    
    private Savedevent savedEvent;
    
    private Event selectedEvent;
    
    private Usermodel currentUser;
    
    private Integer savedID;
    
    
    
    
    
   // private LoginPageViewController loginController;
    
    public void setTableData(List<Event> eventList) {//sets the main tableview
        
        eventData = FXCollections.observableArrayList();
        

        eventList.forEach(s -> {
            eventData.add(s);
        });
        
        eventTable.setItems(eventData);
        eventTable.refresh();
       
        
    }
    
    @FXML
    void showEventView(ActionEvent event) {//loads the saveEvents view and passes currentUser
         try
        {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SavedEventView.fxml"));
            Parent mainView = loader.load();
            SavedEventViewController savedController = loader.getController();
            Usermodel currentDude = getCurrentUser();
            savedController.setCurrentUser(currentDude);

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
    

    @FXML
    void loadData(ActionEvent event) {//queries the database for the data needed for the tableView
        
        events = readAll();
        
        setTableData(events);
    }

    @FXML
    void saveEventBtn(ActionEvent event) throws IOException {//handles the saveButton event, calls the method to create a savedEvent, and selects the currently selected event
        
        Usermodel currentPerson = getCurrentUser();
        
        Event currentEvent = eventTable.getSelectionModel().getSelectedItem();
        int currentUserID = currentPerson.getId();
        System.out.println(currentUserID);
        
      
        
        createSavedEvent(currentUserID, currentEvent);   
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {//initializes the main view

        myManager = (EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        orgColumn.setCellValueFactory(new PropertyValueFactory<>("organization"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
        eventTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        

}
    
    @FXML
     public List readAll(){//queries the database to load all the existing data
         
        Query query = myManager.createNamedQuery("Event.findAll");
               List<Event> events = query.getResultList();
        return events;
    }

    Scene previousScene;

    public void setPreviousScene(Scene scene) {//sets the previous scene for the back button
        previousScene = scene;

    }
    
    @FXML
    void showView(ActionEvent event) throws IOException {//commands to load views from all the nav bar buttons but the savedEvent view
        String view = (String) ((Node) event.getSource()).getUserData();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        
        Parent root = loader.load();
        
        Scene saveScene = new Scene(root);
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        
        Stage stage = (Stage) currentScene.getWindow();
        
        stage.setScene(saveScene);
        stage.show();
    }
    
    public void create(Savedevent newEvent) {//creates a new entry in the database table
        //System.out.println("We got this far");
        try {
            myManager.getTransaction().begin();
            System.out.println("Before if");
            if(newEvent.getId() != null){
                System.out.println("after if");
                
                myManager.persist(newEvent);
                
                myManager.getTransaction().commit();
                
                System.out.println(newEvent.toString() + " is created");
                
            }
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
/*
    public Integer createID(){
        
        int id = 0;
        
        List <Savedevent> eventList;
        
        SavedEventViewController savedController = new SavedEventViewController();
        
        eventList = savedController.readAll();
        
        for(Savedevent e: eventList){
            
            id++;
            
            if(id == e.getId()){
                id++;
            }
        }
        return id;
    }
*/
    
    public void createSavedEvent(Integer userID, Event currentEvent){//creates a new saved event and calls the database create function
        
         //Savedevent newEvent = new Savedevent(null, null, null);

        int eventID = currentEvent.getId();
        int savedEventID = eventID + 10;
        
        Savedevent newEvent = new Savedevent(savedEventID, userID, eventID);    
        
        create(newEvent);
    }
    
    

    public void setCurrentUser(Usermodel user){//sets current user from another view
        
            currentUser = user;
    }
    
    public Usermodel getCurrentUser(){//getter for user
        return currentUser;
    }
    
    public void setSavedEventID(Integer savedEventID){//sets current saved event
        savedID = savedEventID;
    }
    
    public Integer getSavedEventID(){//getter for saved event
        return savedID;
    }
    
    



}
