package pl.edu.wat.wcy.isi.prz.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.Prop;
import pl.edu.wat.wcy.isi.prz.listeners.LoadFileButtonEvent;

public class LoadFile {

    private final Stage stage = App.getStage();

    public LoadFile () {
        makeLoadFile();
    }

    private void makeLoadFile() {
        stage.close();
        stage.setTitle(App.getText("load.file.title"));

        Label label = new Label(App.getText("load.file.info"));
        VBox.setMargin(label,new Insets(5));

        TextField textField = new TextField();
        VBox.setMargin(textField,new Insets(5));
        textField.setPromptText(App.getText("load.file.dir"));

        CheckBox checkBox = new CheckBox(App.getText("load.file.sql"));
        VBox.setMargin(checkBox,new Insets(5));

        Button button = new Button(App.getText("load.file"));
        Button button1 = new Button(App.getText("back"));
        VBox.setMargin(button, new Insets(5));
        VBox.setMargin(button1,new Insets(5));
        button1.setOnAction(e -> new MainApp());
        button.setOnAction(new LoadFileButtonEvent(textField, checkBox));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(label,textField,checkBox, button, button1);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(Prop.getValue("default.css"));

        stage.setScene(scene);
        stage.show();
    }
}
