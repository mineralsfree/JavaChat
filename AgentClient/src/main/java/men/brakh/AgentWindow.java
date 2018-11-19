package men.brakh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import men.brakh.Controllers.MainController;

import java.util.Optional;

public class AgentWindow extends Application{
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        String fxmlFile = "/fxml/hello.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("");
        Scene lol = new Scene(root);
        lol.getStylesheets().add("/fxml/WelcomePage.css");
        primaryStage.setScene(lol);
        primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        primaryStage.setTitle("Agent Client");
        primaryStage.show();
        primaryStage.getIcons().add(new Image("file:"+"C:\\Users\\User\\Documents\\GitHub\\LAST_TI\\src\\main\\logo.PNG"));

    }
    @Override
    public void stop(){

        // Save file
    }
    private void closeWindowEvent(WindowEvent event) {
        System.out.println("Window close request ...");
        MainController.sendExit();
        }
    }

