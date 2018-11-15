package men.brakh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AgentWindow extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/fxml/hello.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("");
        Scene lol = new Scene(root);
        lol.getStylesheets().add("/fxml/WelcomePage.css");
        primaryStage.setScene(lol);
        primaryStage.setTitle("Agent Client");
        primaryStage.show();
        primaryStage.getIcons().add(new Image("file:"+"C:\\Users\\User\\Documents\\GitHub\\LAST_TI\\src\\main\\logo.PNG"));
    }
}
