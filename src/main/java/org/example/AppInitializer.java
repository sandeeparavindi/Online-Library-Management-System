package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/SignUp_form.fxml"));
        Scene scene = new Scene(rootNode);
        primaryStage.setTitle("Sign Up");
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}