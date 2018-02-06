package pl.edu.wat.wcy.isi.prz.javafxfactory;

import javafx.scene.control.Alert;
import pl.edu.wat.wcy.isi.prz.App;

class WrongNameAlert {

    WrongNameAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(App.getText("wrong.name.alert.title"));
        alert.setHeaderText(App.getText("wrong.name.alert.header"));
        alert.setContentText(App.getText("wrong.name.alert.content"));
        alert.show();
    }
}
