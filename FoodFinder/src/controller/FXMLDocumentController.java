/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author taesankim
 */
public class FXMLDocumentController implements Initializable {
    
@FXML
    private TableView<?> eventTable;

    @FXML
    private TableColumn<?, ?> eventNameColumn;

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
    void saveEvent(ActionEvent event) {

    }

    @FXML
    void scrollDirection(ScrollEvent event) {

    }

    @FXML
    void showDetails(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
