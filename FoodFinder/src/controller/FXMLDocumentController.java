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

    @FXML
    void saveEventBtn(ActionEvent event) {

    }

    @FXML
    void scrollDirection(ScrollEvent event) {

    }
    
    public void setTableData(List<Voter> voterList) {

        voterData = FXCollections.observableArrayList();

        voterList.forEach(s -> {
            voterData.add(s);
        });

        tableView.setItems(voterData);
        tableView.refresh();
    }

    
    public void initialize(URL url, ResourceBundle rb) {
        
        EntityManager myManager = (EntityManager) Persistence.createEntityManagerFactory("HaydenLongFXML_3QuizPU").createEntityManager();
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        
        
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        selectedModel = model;
        eventName.setText(model.getId().toString());
        organizationName.setText(model.getFirstname());
        dateLabel.setText(model.getLastname());
        timeLabel.setText(model.getPoliticalparty());
        locationLabel.setText(model.getLastname());
        descriptionLabel.setText(model.getPoliticalparty());
        
        
    }    
    
}
