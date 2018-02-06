package pl.edu.wat.wcy.isi.prz.javafxfactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.listeners.SearchButtonEvent;

public class VBoxResult implements VBoxElement {

    private static TextArea textArea;
    public static TextArea getTextArea() {return textArea;}

    public VBox createVBox() {
        Label label = new Label(App.getText("result"));

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefWidth(400);
        textArea.setPrefHeight(400);

        Button button = new Button(App.getText("search.button"));
        VBox.setMargin(button,new Insets(3));
        button.setOnAction(new SearchButtonEvent());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, textArea,button);
        vBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        vBox.setPadding(new Insets(10));
        return vBox;
    }
}
