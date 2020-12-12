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
import model.SavedEvent;

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

    
    private EntityManager myManager;

    
    private ObservableList <Event> eventData;
    
    public void setTableData(List<SavedEvent> savedEventList) {
        
        eventData = FXCollections.observableArrayList();
        
        for(SavedEvent s: savedEventList){
            Event addedEvent = new Event();
            int eventId = s.getEventid();
            Query query = myManager.createNamedQuery("Event.findById");
            query.setParameter("id", eventId);
            addedEvent = (Event) query.getSingleResult();
            eventData.add(addedEvent);
        
        }
        savedEventView.setItems(eventData);
        savedEventView.refresh();
       
        
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
    void deleteEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert myEventsButton != null : "fx:id=\"myEventsButton\" was not injected: check your FXML file 'SavedEventView.fxml'.";
        assert logOutButton != null : "fx:id=\"logOutButton\" was not injected: check your FXML file 'SavedEventView.fxml'.";
        assert deleteEventButton != null : "fx:id=\"deleteEventButton\" was not injected: check your FXML file 'SavedEventView.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'SavedEventView.fxml'.";

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        myManager = (EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        organizationColumn.setCellValueFactory(new PropertyValueFactory<>("organization"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
        savedEventView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

}
}
