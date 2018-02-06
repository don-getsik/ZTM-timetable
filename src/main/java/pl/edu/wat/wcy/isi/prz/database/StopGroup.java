package pl.edu.wat.wcy.isi.prz.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;

public class StopGroup implements Serializable {

    private int idStopGroup;
    private String name;
    private String cityName;
    private final ObservableList<Stop> stops;

    public StopGroup (String args) {
        idStopGroup = Integer.parseInt(args.substring(3,7));
        name = args.substring(10,43).toLowerCase().trim().replaceAll(",","");
        cityName = args.substring(47).toLowerCase().trim();
        stops = FXCollections.observableArrayList();
    }

    public StopGroup() {
        stops = FXCollections.observableArrayList();
    }

    public void addStop(Stop s) {stops.add(s);}

    //Getters and setters
    public int getIdStopGroup() {return idStopGroup;}
    public void setIdStopGroup(int idStopGroup) { this.idStopGroup = idStopGroup; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public ObservableList<Stop> getStops() { return stops; }

    @Override
    public String toString() {
        if (cityName.equals("warszawa")) return name;
        else return name + " [" + cityName + "]";
    }
}
