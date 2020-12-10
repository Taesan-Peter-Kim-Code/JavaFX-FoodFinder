package controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Event;
import model.Usermodel;

public class FreeFoodMainViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> eventNameColumn;

    @FXML
    private Label dateLabel;

    @FXML
    private Button myEventsButton;

    @FXML
    private Label organizationName;

    @FXML
    private Button logOutButton;

    @FXML
    private Label eventName;

    @FXML
    private Label locationLabel;

    @FXML
    private TableView<?> eventTable;

    @FXML
    private GridPane detailsGrid;

    @FXML
    private Label timeLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button homeButton;
    
    private Usermodel activeUser;

    @FXML
    void scrollDirection(ActionEvent event) {

    }
    public Usermodel getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(Usermodel activeUser)
    {
        this.activeUser = activeUser;
    }
    @FXML
    void showDetails(MouseEvent event, Event model) {
        Event selectedModel = model;
        eventName.setText(model.getId().toString());
        organizationName.setText(model.getOrganizationname());
        dateLabel.setText(model.getDate().toString());
        timeLabel.setText(model.getTime().toString());
        locationLabel.setText(model.getLocation());
        descriptionLabel.setText(model.getDescription());
    }
    
    @FXML
    void d2c7bd(ActionEvent event) {

    }

    @FXML
    void saveEventBtn(ActionEvent event) {

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
    void initialize() {
        assert eventNameColumn != null : "fx:id=\"eventNameColumn\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert myEventsButton != null : "fx:id=\"myEventsButton\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert organizationName != null : "fx:id=\"organizationName\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert logOutButton != null : "fx:id=\"logOutButton\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert eventName != null : "fx:id=\"eventName\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert eventTable != null : "fx:id=\"eventTable\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert detailsGrid != null : "fx:id=\"detailsGrid\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert timeLabel != null : "fx:id=\"timeLabel\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'FreeFoodMainView.fxml'.";
        
        EntityManager myManager = (EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager();
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        eventTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
}
