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
import model.Usermodel;

/**
 *
 * @author hayde
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
    
    public void setTableData(List<Event> eventList) {
        
        eventData = FXCollections.observableArrayList();
        

        eventList.forEach(s -> {
            eventData.add(s);
        });
        
        eventTable.setItems(eventData);
        eventTable.refresh();
       
        
    }
    

    @FXML
    void loadData(ActionEvent event) {
        
        events = readAll();
        
        setTableData(events);
    }

    @FXML
    void saveEventBtn(ActionEvent event) {
        System.out.println("Save button clicked");
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
        
        
        eventTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        

}
    
    @FXML
     public List readAll(){
         
        Query query = myManager.createNamedQuery("Event.findAll");
               List<Event> events = query.getResultList();
        return events;
    }

    void setActiveUser(Usermodel user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}