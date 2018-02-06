package pl.edu.wat.wcy.isi.prz.database;

import java.io.Serializable;

public class Stop implements Serializable {
    private int idStop;
    private String name;
    private String locale;
    private String direction;
    private double X;
    private double Y;
    private int idStopGroup;
    private String stableLines = null;
    private String requestLines = null;
    private String endLines = null;
    private String forGetOffLines = null;
    private String forGetInLines = null;

    public Stop(String args, int idSG, String name) {
        idStop = Integer.parseInt(args.substring(9,15));
        this.name = name + " " + idStop%100;
        locale = args.substring(34,68).toLowerCase().trim().replaceAll(",","");
        direction = args.substring(75,109).toLowerCase().trim().replaceAll(",","");
        try {
            Y = Double.parseDouble(args.substring(112, 121));
            X = Double.parseDouble(args.substring(129, 138));
        } catch (NumberFormatException e){ X = Y = 0; }
        idStopGroup = idSG;
    }

    public Stop() {}

    public void addBuses(String args) {
        String type = args.substring(20,40);
        String lines = args.substring(40).replaceAll(" {3}", " ");
        switch (type) {
            case "stały:              ": stableLines = lines;break;
            case "na żądanie:         ": requestLines = lines;break;
            case "dla wysiadających:  ": forGetOffLines = lines;break;
            case "dla wsiadających:   ": forGetInLines = lines;break;
            case "krańcowy:           ": endLines = lines;break;
        }
    }

    @Override
    public String toString() {return name;}

    //Getters and Setters
    public int getIdStop() { return idStop;}
    public void setIdStop(int idStop) { this.idStop = idStop;}
    public String getLocale() { return locale;}
    public void setLocale(String locale) { this.locale = locale;}
    public String getDirection() { return direction;}
    public void setDirection(String direction) { this.direction = direction;}
    public double getX() { return X;}
    public void setX(double x) { X = x; }
    public double getY() { return Y; }
    public void setY(double y) { Y = y; }
    public int getIdStopGroup() { return idStopGroup; }
    public void setIdStopGroup(int idStopGroup) { this.idStopGroup = idStopGroup; }
    public String getStableLines() { return stableLines; }
    public void setStableLines(String stableLines) { this.stableLines = stableLines; }
    public String getRequestLines() { return requestLines; }
    public void setRequestLines(String requestLines) { this.requestLines = requestLines; }
    public String getEndLines() { return endLines; }
    public void setEndLines(String endLines) { this.endLines = endLines; }
    public String getForGetOffLines() { return forGetOffLines; }
    public void setForGetOffLines(String forGetOffLines) { this.forGetOffLines = forGetOffLines; }
    public String getForGetInLines() { return forGetInLines; }
    public void setForGetInLines(String forGetInLines) { this.forGetInLines = forGetInLines; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
