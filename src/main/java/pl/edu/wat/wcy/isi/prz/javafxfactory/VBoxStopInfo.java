package pl.edu.wat.wcy.isi.prz.javafxfactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.database.PrintStop;
import pl.edu.wat.wcy.isi.prz.database.Stop;
import pl.edu.wat.wcy.isi.prz.database.StopController;
import pl.edu.wat.wcy.isi.prz.database.StopGroup;
import pl.edu.wat.wcy.isi.prz.javafx.MainApp;

public class VBoxStopInfo implements VBoxElement {

    private static ListView<Stop> listView;
    private TextArea textArea;

    public static void setStop (StopGroup stopGroup) {
        listView.setItems(stopGroup.getStops());
    }

    private void setText(Stop stop) {
        if (stop != null) {
            StopController controller = new StopController(stop,new PrintStop());
            textArea.setText(controller.updateView());
        }
    }

    @Override
    public VBox createVBox() {
        Label label = new Label(App.getText("stops"));
        Label label2 = new Label(App.getText("details"));

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefWidth(400);
        textArea.setPrefHeight(200);

        listView = new ListView<>();
        listView.setPrefHeight(300);
        listView.getSelectionModel().selectedItemProperty().addListener((o, oV,nV) -> setText(nV));

        Button button = new Button(App.getText("back"));
        button.setOnAction(e -> new MainApp());
        VBox.setMargin(button,new Insets(3));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, listView, label2, textArea, button);
        vBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        vBox.setPadding(new Insets(10));
        return vBox;
    }
}
