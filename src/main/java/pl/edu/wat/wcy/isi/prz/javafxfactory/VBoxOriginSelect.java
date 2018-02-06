package pl.edu.wat.wcy.isi.prz.javafxfactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.database.Stop;
import pl.edu.wat.wcy.isi.prz.database.StopGroup;
import pl.edu.wat.wcy.isi.prz.listeners.SearchButtonEvent;
import pl.edu.wat.wcy.isi.prz.exceptions.NoStopException;

public class VBoxOriginSelect extends VBoxStopSelect {

    private void selectOrigin (StopGroup newValue) {
        if (newValue!= null) {
            Stop stop = newValue.getStops().get(0);
            double x = stop.getX();
            double y = stop.getY();
            SearchButtonEvent.setOrigin(y + "," + x);
        }
    }

    @Override
    public VBox createVBox() {
        Label label = new Label(App.getText("origin.station.label"));

        super.listView = new ListView<>();
        try { filterList(""); } catch (NoStopException ignored) {}
        listView.getSelectionModel().selectedItemProperty()
                .addListener((o, oV, nV) -> selectOrigin(nV));

        TextField textField = new TextField();
        textField.setPromptText(App.getText("search.text"));
        textField.textProperty().addListener((o, oV, nV) -> {
            try { filterList(nV);
            } catch (NoStopException e) {
                new WrongNameAlert();
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,listView,textField);
        vBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(listView, Priority.ALWAYS);
        vBox.setPadding(new Insets(10));
        return vBox;
    }
}
