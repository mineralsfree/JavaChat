package men.brakh.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
import men.brakh.AgentClient;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static AgentClient agentClient;
    private MediaPlayer mediaPlayer;
    private static String msg;
    @FXML
    public Text welcomeText;
    @FXML
    public  TextArea messageBox;
    @FXML
    public TextField messageInput;
    @FXML
    public Button sendButton;
    @FXML
    public TextField welcomeInput;
    @FXML
    public void exitApplication(ActionEvent event) {

        Platform.exit();
    }

    public static void sendExit(){
        agentClient.StringHandler("/exit");
    }
    private void setSendClickListener(Button button){
        button.setOnMouseClicked(e ->{
            MainController.agentClient.StringHandler(messageInput.getText());
            messageBox.appendText("[YOU]" + messageInput.getText()+"\n");
            messageInput.setText("");
        });
    }

    private void setRegEnterListener(TextField textField){
        textField.setOnKeyPressed(ke -> {
            if ((ke.getCode().equals(KeyCode.ENTER)) && (textField.getText().length()>3))
            {
                textField.visibleProperty().setValue(false);
                welcomeText.visibleProperty().setValue(false);
                MainController.agentClient.StringHandler("/register " + textField.getText());
                textField.setText("");
                messageBox.visibleProperty().setValue(true);
                sendButton.visibleProperty().setValue(true);
                messageInput.visibleProperty().setValue(true);
            }
        });
    }
    private void setSendEnterListener(TextField textField){
        textField.setOnKeyPressed(ke -> {
            if ((ke.getCode().equals(KeyCode.ENTER)) && (textField.getText().length()>3))
            {
                MainController.agentClient.StringHandler(textField.getText());
                messageBox.appendText("[YOU]" + textField.getText()+"\n");
                textField.setText("");
            }
        });
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        agentClient = new AgentClient(messageBox);


        Text t = new Text();
        String musicFile = "C:\\Users\\User\\Documents\\GitHub\\Java Chat\\JavaChat\\AgentClient\\src\\main\\resources\\gotmail.wav";
        Media sound = new Media(new File(musicFile).toURI().toString());
        //mediaPlayer = new MediaPlayer(sound);
        messageBox.visibleProperty().setValue(false);
        messageInput.visibleProperty().setValue(false);
        sendButton.visibleProperty().setValue(false);
        t.setText("This is a text sample");
        t.setFont(Font.font("Verdana", 20));
        Scene scene = new Scene(new Group(), 500, 400);
        scene.getStylesheets().add("/fxml/WelcomePage.css");
        setRegEnterListener(welcomeInput);
        setSendClickListener(sendButton);
        setSendEnterListener(messageInput);
        messageBox.textProperty().addListener((observable, oldValue, newValue) -> {
            String newStr = newValue.substring(oldValue.length());
            if (!newStr.startsWith("[YOU]")){
                mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();

            }
        });
    }
}
