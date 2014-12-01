package gui;/**
 * Created by djemaa on 01/12/14.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListUserLayout extends Application {

    public VBox addListUserLayout(){
        VBox listUser = new VBox();
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList("Titi", "Toto", "Tata");
        list.setItems(items);
        listUser.getChildren().add(listUser);
        return listUser;

    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
