/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Event;

/**
 *
 * @author taesankim
 * @author haydenlong
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, String> eventNameColumn;

    @FXML
    private GridPane detailsGrid;

    @FXML
    private Label eventName;

    @FXML
    private Label organizationName;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label descriptionLabel;
    
    private ObservableList<Event> eventData;

    @FXML
    void saveEventBtn(ActionEvent event) {

    }

    @FXML
    void scrollDirection(ScrollEvent event) {

    }

    public void setTableData(List<Event> eventList) {

        eventData = FXCollections.observableArrayList();

        eventList.forEach(s -> {
            eventData.add(s);
        });

        eventTable.setItems(eventData);
        eventTable.refresh();
    }

    public void initialize(URL url, ResourceBundle rb) {

        EntityManager myManager = (EntityManager) Persistence.createEntityManagerFactory("FoodFinderPU").createEntityManager();
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        eventTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    void showDetails(MouseEvent event, Event model) {
        Event selectedModel = model;
        eventName.setText(model.getId().toString());
        organizationName.setText(model.getOrganizationname());
        dateLabel.setText(model.getDate().toString());//Need to change the data type in event
        timeLabel.setText(model.getTime().toString());//Need to change the data type in event
        locationLabel.setText(model.getLocation());
        descriptionLabel.setText(model.getDescription());
    }

}
