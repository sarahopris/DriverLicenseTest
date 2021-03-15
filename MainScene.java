package ubb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainScene{
    @FXML Button bStart;

    public EventHandler<ActionEvent> myFunction() throws IOException {
        Stage appStage = (Stage) bStart.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
        return null;
    }
}
