package pl.edu.wat.wcy.isi.prz;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import pl.edu.wat.wcy.isi.prz.database.StopGroup;
import pl.edu.wat.wcy.isi.prz.listeners.LoadingDatabaseErrorEvent;
import pl.edu.wat.wcy.isi.prz.listeners.WindowExitEvent;
import pl.edu.wat.wcy.isi.prz.javafx.LoadScreen;
import pl.edu.wat.wcy.isi.prz.javafx.MainApp;
import pl.edu.wat.wcy.isi.prz.listofstops.ListOfStops;
import pl.edu.wat.wcy.isi.prz.listofstops.LoadDatabase;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    private static ListOfStops listOfStops;
    private static ResourceBundle resourceBundle;
    private static Stage stage;

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setOnCloseRequest(new WindowExitEvent());
        new LoadScreen();
        loadDatabase();
    }

    private void loadDatabase() {
        Task task = new LoadDatabase("DATABASE");
        Thread thread = new Thread(task);
        thread.start();
        WindowExitEvent.setTask(thread);
        task.setOnFailed(new LoadingDatabaseErrorEvent());
        task.messageProperty().addListener((o, s, n) -> new MainApp());
    }

    public static void setResourceBundle (Locale locale) {
        resourceBundle = ResourceBundle.getBundle("Language", locale);
        new MainApp();
    }

    public static void main (String[] args) {
        resourceBundle = ResourceBundle.getBundle("Language",
                new Locale(Prop.getValue("default.language"),
                        Prop.getValue("default.country")));
        launch(args);
    }

    public static String getText(String s) {return resourceBundle.getString(s);}
    public static ObservableList<StopGroup> getList() {return listOfStops.getList();}
    public static  Stage getStage() {return stage;}
    public static void setListOfStops(ListOfStops listOfStops) { App.listOfStops = listOfStops; }
}
