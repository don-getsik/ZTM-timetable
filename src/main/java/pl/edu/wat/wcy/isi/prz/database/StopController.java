package pl.edu.wat.wcy.isi.prz.database;

public class StopController {

    private final Stop stop;
    private final PrintStop printStop;

    public StopController(Stop stop, PrintStop printStop) {
        this.stop = stop;
        this.printStop = printStop;
    }

    public String updateView () {
        return  printStop.print(getName(), getLocale(), getDirection(), getX(), getY(),
                getStableLines(), getRequestLines(), getEndLines(), getForGetOffLines(), getForGetInLines());
    }

    public int getIdStop() { return stop.getIdStop();}
    public void setIdStop(int idStop) { stop.setIdStop(idStop);}
    public String getLocale() { return stop.getLocale();}
    public void setLocale(String locale) { stop.setLocale(locale);}
    public String getDirection() { return stop.getDirection();}
    public void setDirection(String direction) { stop.setDirection(direction);}
    public double getX() { return stop.getX();}
    public void setX(double x) { stop.setX(x); }
    public double getY() { return stop.getY(); }
    public void setY(double y) { stop.setY(y); }
    public int getIdStopGroup() { return stop.getIdStopGroup(); }
    public void setIdStopGroup(int idStopGroup) { stop.setIdStopGroup(idStopGroup); }
    public String getStableLines() { return stop.getStableLines(); }
    public void setStableLines(String stableLines) { stop.setStableLines(stableLines);}
    public String getRequestLines() { return stop.getRequestLines(); }
    public void setRequestLines(String requestLines) { stop.setRequestLines(requestLines); }
    public String getEndLines() { return stop.getEndLines(); }
    public void setEndLines(String endLines) { stop.setEndLines(endLines); }
    public String getForGetOffLines() { return stop.getForGetOffLines(); }
    public void setForGetOffLines(String forGetOffLines) { stop.setForGetOffLines(forGetOffLines); }
    public String getForGetInLines() { return stop.getForGetInLines(); }
    public void setForGetInLines(String forGetInLines) { stop.setForGetInLines(forGetInLines); }
    public String getName() { return stop.getName(); }
    public void setName(String name) { stop.setName(name); }
}
