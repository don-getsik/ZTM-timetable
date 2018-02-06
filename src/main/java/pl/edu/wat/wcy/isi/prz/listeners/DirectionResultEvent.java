package pl.edu.wat.wcy.isi.prz.listeners;

import com.google.maps.PendingResult;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import javafx.scene.control.TextArea;
import pl.edu.wat.wcy.isi.prz.directions.DirectionString;
import pl.edu.wat.wcy.isi.prz.exceptions.GoogleMapsException;

import java.io.IOException;

public class DirectionResultEvent implements PendingResult, PendingResult.Callback<DirectionsResult> {

    private final TextArea textArea;

    public DirectionResultEvent(TextArea textArea) { this.textArea = textArea; }

    @Override
    public void onResult(DirectionsResult directionsResult) {
        textArea.setText(String.valueOf(new DirectionString(directionsResult)));
    }

    @Override
    public void onFailure(Throwable throwable) {
        try {
            throw new GoogleMapsException(textArea, throwable);
        } catch (GoogleMapsException e) {
            e.printStackTrace();
        }
    }

    @Override public void setCallback(Callback callback) { }
    @Override public Object await() throws ApiException, InterruptedException, IOException { return null; }
    @Override public Object awaitIgnoreError() { return null; }
    @Override public void cancel() { }
}
