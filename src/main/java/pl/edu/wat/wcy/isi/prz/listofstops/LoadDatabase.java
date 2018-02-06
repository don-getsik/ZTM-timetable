package pl.edu.wat.wcy.isi.prz.listofstops;

import javafx.concurrent.Task;
import pl.edu.wat.wcy.isi.prz.App;

public class LoadDatabase extends Task {

    private final String type;
    private String file;

    public LoadDatabase (String type) {this.type = type;}

    public LoadDatabase(String type, String file) {
        this.type = type;
        this.file = file;
    }

    @Override
    protected Object call() throws Exception{
            if (type.equals("FILEOFFLINE"))
                App.setListOfStops(new ZTMFileParser(file, true));
            else {
                if (type.equals("FILEONLINE"))
                    App.setListOfStops(new ZTMFileParser(file, false));
                else if (type.equals("DATABASE"))
                    App.setListOfStops(new StopsList());
            }updateMessage("COMPLETE");
        return true;
    }
}
