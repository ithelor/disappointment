package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root, 600, 500); //               <-- \
        stage.setMinWidth(600); stage.setMinHeight(500);     // doesn't match --> /

        //stage.getIcons().add(new Image("/sample/icon.png"));
        stage.setTitle("Your Life's Biggest Disappointment");

        stage.setScene(scene);
        stage.show();

        String str_n = "19 Mar, 2021";
        String pattern = "^\\d{2} \\p{Lu}\\p{Ll}{2}, \\d{4}$";

        // Создание Pattern объекта
        Pattern r = Pattern.compile(pattern);

        // Создание matcher объекта
        Matcher m = r.matcher(str_n);
        if (m.find( )) {
            System.out.println("Найдено значение: " + m.group(0));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
