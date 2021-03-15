package ubb;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FinalScene {
    @FXML
    Button exitB;

    @FXML
    Button newB;

    public EventHandler<ActionEvent> exit()
    {
        Stage appStage = (Stage) exitB.getScene().getWindow();
        appStage.close();
        return null;
    }

    public EventHandler<ActionEvent> newTest() throws IOException {
        Stage appStage = (Stage) newB.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
        return null;
    }
}
