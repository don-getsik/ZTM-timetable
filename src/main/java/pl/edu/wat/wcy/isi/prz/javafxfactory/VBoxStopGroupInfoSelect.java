package pl.edu.wat.wcy.isi.prz.javafxfactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.exceptions.NoStopException;

public class VBoxStopGroupInfoSelect extends VBoxStopSelect {

    @Override
    public VBox createVBox() {
        Label label = new Label(App.getText("stopsgrups"));

        try { filterList(""); } catch (NoStopException ignored) {}

        super.listView = new ListView<>();
        listView.getSelectionModel().selectedItemProperty()
                .addListener((o, oV, nV) -> { if (nV != null) VBoxStopInfo.setStop(nV); });

        TextField textField = new TextField();
        textField.setPromptText(App.getText("search.text"));
        textField.textProperty().addListener((o, oV, nV) -> {
            try { filterList(nV);
            } catch (NoStopException e) { new WrongNameAlert(); }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,listView,textField);
        vBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(listView, Priority.ALWAYS);
        vBox.setPadding(new Insets(10));
        return vBox;
    }
}
