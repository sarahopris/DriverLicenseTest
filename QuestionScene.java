package ubb;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuestionScene implements Initializable{
    Controller cont;
    Fragen currentQuestion;
    String myAnswer;
    Timer myTimer;
    TimerTask myTimerTask;
    int secondsPassed;
    @FXML
    javafx.scene.control.Button b1;
    @FXML
    javafx.scene.control.Button b2;
    @FXML
    javafx.scene.control.Button b3;
    @FXML
    javafx.scene.control.Button skipB;
    @FXML
    javafx.scene.control.Label lbl;
    @FXML
    javafx.scene.control.Label remainingL;
    @FXML
    javafx.scene.control.Label correctL;
    @FXML
    javafx.scene.control.Label falseL;
    @FXML
    javafx.scene.control.Label answerLbl;
    @FXML
    javafx.scene.control.Button answerButton;
    @FXML
    javafx.scene.control.Label timerL;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cont = new Controller(new FragenFileRepository(new FragenRepository(new ArrayList<>())));
        currentQuestion = cont.getQuestion();
        secondsPassed = 0;
        lbl.setText(currentQuestion.getFrage());
        b1.setText(currentQuestion.getAntwort1());
        b2.setText(currentQuestion.getAntwort2());
        b3.setText(currentQuestion.getAntwort3());
        remainingL.setText("26");
        correctL.setText("0");
        falseL.setText("0");
        myTimerTask = new TimerTask() {
            final int seconds = 1800;
            int i = 0;
            @Override
            public void run()
            {
                i++;
                secondsPassed++;
                if (secondsPassed == 30 * 60)
                {
                    myTimer.cancel();
                    Stage appStage = (Stage) b1.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FinalScene.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert root != null;
                    Scene scene = new Scene(root);
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            appStage.setScene(scene);
                            appStage.show();
                        }});
                }
                int minutes, seconds;
                minutes = (1800 - secondsPassed) / 60;
                seconds = (1800 - secondsPassed) % 60;
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        timerL.setText(minutes + ":" + seconds);
                    }});
            }
        };
        myTimer = new Timer();
        myTimer.schedule(myTimerTask, 0, 1000);
    }

    public EventHandler<ActionEvent> button1()
    {
        Thread thread = new Thread("New Thread") {
            public void run() {
                myAnswer = "A";
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        answerLbl.setText(myAnswer);
                    }
                });
            }
        };
        thread.start();
        return null;
    }

    public EventHandler<ActionEvent> button2()
    {
        Thread thread = new Thread("New Thread") {
            public void run() {
                myAnswer = "B";
                Platform.runLater(() -> { answerLbl.setText(myAnswer); });
            }
        };
        thread.start();
        return null;
    }

    public EventHandler<ActionEvent> button3()
    {
        Thread thread = new Thread("New Thread") {
            public void run() {
                myAnswer = "C";
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        answerLbl.setText(myAnswer);
                    }
                });
            }
        };
        thread.start();
        return null;
    }

    public EventHandler<ActionEvent> answer() throws IOException {
        Thread thread = new Thread("New Thread") {
            public void run(){
                cont.answer(myAnswer);
                if (cont.finish() || cont.falseAnswers > 4)
                {
                    myTimer.cancel();
                    Stage appStage = (Stage) b1.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FinalScene.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert root != null;
                    Scene scene = new Scene(root);
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            appStage.setScene(scene);
                            appStage.show();
                        }});
                }
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        correctL.setText(String.valueOf(cont.getCorrectAnswers()));
                        falseL.setText(String.valueOf(cont.getFalseAnswers()));
                        remainingL.setText(String.valueOf(cont.size()));
                        currentQuestion = cont.getQuestion();
                        lbl.setText(currentQuestion.getFrage());
                        b1.setText(currentQuestion.getAntwort1());
                        b2.setText(currentQuestion.getAntwort2());
                        b3.setText(currentQuestion.getAntwort3());
                        answerLbl.setText(" ");
                    }});
                myAnswer = " ";
            }
        };
        thread.start();
        return null;
    }

    public EventHandler<ActionEvent> skip() throws IOException {
        Thread thread = new Thread("New Thread") {
            public void run() {
                cont.nextQuestion();
                currentQuestion = cont.getQuestion();
                myAnswer = " ";
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lbl.setText(currentQuestion.getFrage());
                        b1.setText(currentQuestion.getAntwort1());
                        b2.setText(currentQuestion.getAntwort2());
                        b3.setText(currentQuestion.getAntwort3());
                    }
                });
            }
        };
        thread.start();
        return null;
    }

}
