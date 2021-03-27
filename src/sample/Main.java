package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root, 330, 500);
        stage.setMinWidth(345); stage.setMinHeight(400);
        stage.setMaxWidth(345); stage.setMaxHeight(680);

        stage.getIcons().add(new Image("/sample/icon.png"));
        stage.setTitle("SAPHP");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
