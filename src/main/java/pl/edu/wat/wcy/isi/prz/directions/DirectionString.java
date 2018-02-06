package pl.edu.wat.wcy.isi.prz.directions;

import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import pl.edu.wat.wcy.isi.prz.App;

public class DirectionString {

    private final StringBuilder s;

    public DirectionString(DirectionsResult directionsResult) {
        s = new StringBuilder();
        for (DirectionsRoute dr: directionsResult.routes) printRoute(dr);
    }

    @Override public String toString() {
        return s.toString();
    }

    private void printRoute(DirectionsRoute dr) {
        for(DirectionsLeg dl: dr.legs) printLeg(dl);
    }

    private void printLeg(DirectionsLeg dl) {
        for(DirectionsStep ds: dl.steps) printStep(ds);
        s.append("------------------\n");
    }

    private void printStep(DirectionsStep ds) {
        s.append(ds.htmlInstructions).append("\n");
        if(ds.transitDetails != null) printTransit(ds);
        else s.append("\n");
    }

    private void printTransit(DirectionsStep ds) {
        s.append(App.getText("direction.number"))
                .append(ds.transitDetails.line.shortName)
                .append(App.getText("which.arrive"))
                .append(ds.transitDetails.arrivalTime.getHourOfDay())
                .append(":")
                .append(String.format("%02d", ds.transitDetails.arrivalTime.getMinuteOfHour()))
                .append(" ")
                .append(App.getText("and.end"))
                .append(ds.transitDetails.arrivalStop.name)
                .append("\n\n");
    }
}
