package Network;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "AppName");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("NetWork Tool");
        primaryStage.setScene(new Scene(root, 756, 420));
        primaryStage.show();
        primaryStage.setResizable(false);


    }
    public static void main(String[] args) {
        launch(args);


    }

}
