package pl.edu.wat.wcy.isi.prz.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import pl.edu.wat.wcy.isi.prz.App;

public class GoogleMapsException extends Exception {

    public GoogleMapsException(TextArea textField, Throwable error) {
        textField.setText("");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(App.getText("search.button.event.error"));
        alert.setHeaderText(App.getText("google.maps.exception.header"));
        alert.setContentText(error.getMessage());
    }
}
