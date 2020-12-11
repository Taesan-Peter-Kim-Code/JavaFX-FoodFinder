package controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SavedEventViewController {

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

}
