package pl.edu.wat.wcy.isi.prz.javafxfactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.database.StopGroup;
import pl.edu.wat.wcy.isi.prz.exceptions.NoStopException;
import java.util.stream.Collectors;

abstract class VBoxStopSelect implements VBoxElement {

    ListView<StopGroup> listView;

    void filterList(String value) throws NoStopException {
        ObservableList<StopGroup> stopGroups = FXCollections.observableArrayList(App.getList().stream()
                .filter(stopGroup -> stopGroup.getName().contains(value))
                .collect(Collectors.toList()));
        if (stopGroups.isEmpty()) throw new NoStopException();
        this.listView.setItems(stopGroups);
    }
}
