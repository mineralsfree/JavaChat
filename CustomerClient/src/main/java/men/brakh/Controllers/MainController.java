package men.brakh.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import men.brakh.CustomerClient;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static CustomerClient customerClient;
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





    private void setRegEnterListener(TextField textField){
        textField.setOnKeyPressed(ke -> {
            if ((ke.getCode().equals(KeyCode.ENTER)) && (textField.getText().length()>3))
            {
                textField.visibleProperty().setValue(false);
                welcomeText.visibleProperty().setValue(false);
                MainController.customerClient.StringHandler("/register " + textField.getText());
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
                MainController.customerClient.StringHandler(textField.getText());
                messageBox.appendText("[YOU]" + textField.getText()+"\n");
                textField.setText("");
            }
        });
    }
    private void setSendClickListener(Button button){
        button.setOnMouseClicked(e ->{
            MainController.customerClient.StringHandler(messageInput.getText());
            messageInput.setText("");
        });
    }
 public static void getmessage(String message){
        msg = (message + "\n");

 }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
            customerClient = new CustomerClient(messageBox);
        Text t = new Text();
        String musicFile = "C:\\Users\\User\\Documents\\GitHub\\Java Chat\\JavaChat\\CustomerClient\\src\\main\\resources\\gotmail.wav";
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
