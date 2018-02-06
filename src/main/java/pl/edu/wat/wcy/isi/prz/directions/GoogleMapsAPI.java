package pl.edu.wat.wcy.isi.prz.directions;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import javafx.scene.control.TextArea;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.Prop;
import pl.edu.wat.wcy.isi.prz.exceptions.NoStopSelectedException;
import pl.edu.wat.wcy.isi.prz.exceptions.OriginAndDestinationIsSameException;
import pl.edu.wat.wcy.isi.prz.listeners.DirectionResultEvent;

public class GoogleMapsAPI {

    private static final GoogleMapsAPI instance = new GoogleMapsAPI();

    private final GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(Prop.getValue("google.api.key"))
            .build();

    public static GoogleMapsAPI get() {return instance;}

    public void getDirections(String origin, String destination, TextArea textArea)
            throws OriginAndDestinationIsSameException, NoStopSelectedException {

        if (origin == null) throw new NoStopSelectedException("ORIGIN");
        if (destination == null) throw new NoStopSelectedException("DESTINATON");
        if (origin.equals(destination)) throw new OriginAndDestinationIsSameException();

        DirectionsApi.getDirections(context, origin, destination)
                .language(App.getText("google.api.language"))
                .mode(TravelMode.TRANSIT)
                .alternatives(Boolean.valueOf(Prop.getValue("google.alternatives")))
                .setCallback(new DirectionResultEvent(textArea));
    }



}
