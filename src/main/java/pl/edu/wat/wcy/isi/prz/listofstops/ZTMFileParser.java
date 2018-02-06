package pl.edu.wat.wcy.isi.prz.listofstops;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.edu.wat.wcy.isi.prz.database.DatabaseConnection;
import pl.edu.wat.wcy.isi.prz.database.Stop;
import pl.edu.wat.wcy.isi.prz.database.StopGroup;
import pl.edu.wat.wcy.isi.prz.exceptions.DatabaseLoadingException;
import java.io.File;
import java.util.Scanner;

public class ZTMFileParser implements ListOfStops {

    private final ObservableList<StopGroup> stopGroups;
    private DatabaseConnection dC = null;
    private final boolean isOffline;
    private static final Logger logger = LogManager.getLogger(ZTMFileParser.class.getName());

    ZTMFileParser(String fileName, boolean isOffline) throws DatabaseLoadingException {
        stopGroups = FXCollections.observableArrayList();
        this.isOffline = isOffline;
        if(!isOffline) {
            dC = DatabaseConnection.get();
            dC.deleteAll();
        }
        try {
            Scanner text = new Scanner(new File(fileName));
            readFile(text);
            text.close();}
        catch (Exception e) {
            logger.error(e.getMessage());
            throw new DatabaseLoadingException();
        }
    }

    private void readFile(Scanner text) {
        String tmpS;
        text.nextLine();
        while (text.hasNextLine()) {
            System.out.println(tmpS = text.nextLine());//Grupa przystnaków
            if (tmpS.equals("#ZP")) return; //Koniec pliku
            else readStopGroup(text, tmpS); //Czytaj grupę przystanków
        }
    }

    private void readStopGroup(Scanner text, String tmpS) {
        StopGroup tmpSG;
        if (isOffline) tmpSG = new StopGroup(tmpS);
        else tmpSG = dC.addStopGroup(new StopGroup(tmpS));
        stopGroups.add(tmpSG);
        System.out.println(tmpS = text.nextLine()); //Pczątek listy grupy przystanków
        int endStopLoop = Integer.parseInt(tmpS.substring(10).trim());
        for (int i = 0; i < endStopLoop; i++) readStop(text, tmpSG); //Czytaj przystanki
        System.out.println(text.nextLine()); //Koniec listy grupy Przystanków
    }

    private void readStop(Scanner text, StopGroup tmpSG) {
        String tmpS;
        Stop tmpStop;
        System.out.println(tmpS = text.nextLine()); //Przystanek
        tmpSG.addStop(tmpStop = new Stop(tmpS, tmpSG.getIdStopGroup(), tmpSG.getName()));
        int endLineLoop = Integer.parseInt(tmpS.substring(18, 19));
        for (int j = 0; j < endLineLoop; j++) readLine(text, tmpStop);
        if (!isOffline) dC.addStop(tmpStop);
    }

    private void readLine(Scanner text, Stop tmpStop) {
        String tmpS;
        System.out.println(tmpS = text.nextLine()); //Linia
        tmpStop.addBuses(tmpS);
    }

    public ObservableList<StopGroup> getList() {return stopGroups;}
}
