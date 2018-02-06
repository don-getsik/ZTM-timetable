package pl.edu.wat.wcy.isi.prz.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.directions.GoogleMapsAPI;
import pl.edu.wat.wcy.isi.prz.exceptions.NoStopSelectedException;
import pl.edu.wat.wcy.isi.prz.exceptions.OriginAndDestinationIsSameException;
import pl.edu.wat.wcy.isi.prz.javafxfactory.VBoxResult;

public class SearchButtonEvent implements EventHandler<ActionEvent>{

    private static String origin = null;
    private static String destination = null;

    public static void setOrigin(String origin) { SearchButtonEvent.origin = origin; }
    public static void setDestination(String destination) { SearchButtonEvent.destination = destination; }


    private void alert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    @Override
    public void handle(ActionEvent event) {
        VBoxResult.getTextArea().setText(App.getText("load.loadtext"));
        GoogleMapsAPI googleMapsAPI = GoogleMapsAPI.get();
        try {
            googleMapsAPI.getDirections(origin, destination, VBoxResult.getTextArea());

        } catch (OriginAndDestinationIsSameException originAndDestinationIsSameException) {
            alert(App.getText("search.button.event.duplicate"),
                    App.getText("search.button.event.duplicate"),
                    App.getText("search.button.event.dubmore"));
            VBoxResult.getTextArea().setText("");

        } catch (NoStopSelectedException e) {
            if (e.type.equals("ORIGIN"))
                alert(App.getText("search.button.event.nostop"),
                        App.getText("search.button.event.nostop"),
                        App.getText("search.button.event.noorigin"));
            else if (e.type.equals("DESTINATION"))
                alert(App.getText("search.button.event.nostop"),
                        App.getText("search.button.event.nostop"),
                        App.getText("search.button.event.nodestination"));
        }
    }
}
