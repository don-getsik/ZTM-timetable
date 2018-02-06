package pl.edu.wat.wcy.isi.prz.javafxfactory;

public class VBoxFactory {

    public VBoxElement getVBox(String type) {
        if (type == null) return null;
        else if (type.equals("RESULT")) return new VBoxResult();
        else if (type.equals("ORIGIN")) return new VBoxOriginSelect();
        else if (type.equals("DESTINATION")) return new VBoxDestinationSelect();
        else if (type.equals("STOPGROUPINFO")) return new VBoxStopGroupInfoSelect();
        else if (type.equals("STOPINFO")) return new VBoxStopInfo();
        else return null;
    }
}
