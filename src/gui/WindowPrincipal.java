package gui;/**
 * Created by djemaa on 01/12/14.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WindowPrincipal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane principal = new GridPane();
        /*principal.setHgap(10);
        principal.setVgap(10);*/
        principal.setPadding(new Insets(0, 10, 0, 10));


        principal.add(addSendLayout(), 0, 0, 8, 7);
        principal.add(addSendLayout(),0, 7, 8,10 );
        principal.add(addListUserLayout(), 8, 0, 10, 10);

        //playerGrid.setGridLinesVisible(true);
        Scene scene = new Scene(principal, 500, 500);

        primaryStage.setTitle("Goal Scorers!");
        primaryStage.setScene(scene);
        primaryStage.show();
        /*BorderPane principal = new BorderPane();
        principal.setBottom(addSendLayout());
        //principal.setRight(addListUserLayout());
        BorderPane border = new BorderPane();
        border.setBottom(addSendLayout());
        border.setRight(addListUserLayout());
        primaryStage.setScene(border.getScene());
        primaryStage.show();*/






    }

    public VBox addListUserLayout(){
        VBox listUser = new VBox();
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList("Titi", "Toto", "Tata");
        list.setItems(items);
        listUser.getChildren().add(list);
        return listUser;

    }
    public HBox addSendLayout(){
        HBox sendLayout = new HBox(2);
        TextField messageInput = new TextField("Entrez votre message");
        Button send = new Button("Send");
        sendLayout.getChildren().addAll(messageInput, send);
        return sendLayout;
    }
}
