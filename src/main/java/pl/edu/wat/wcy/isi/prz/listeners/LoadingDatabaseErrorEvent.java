package pl.edu.wat.wcy.isi.prz.listeners;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.javafx.LoadFile;
import pl.edu.wat.wcy.isi.prz.javafx.LoadScreen;
import pl.edu.wat.wcy.isi.prz.javafx.MainApp;
import pl.edu.wat.wcy.isi.prz.listofstops.LoadDatabase;

import java.util.Optional;

public class LoadingDatabaseErrorEvent implements EventHandler<WorkerStateEvent> {

    @Override
    public void handle(WorkerStateEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(App.getText("loading.database.error.title"));
        alert.setHeaderText(App.getText("loading.database.error.header"));
        alert.setContentText(App.getText("loading.database.error.content"));
        ButtonType file = new ButtonType(App.getText("file"));
        ButtonType database = new ButtonType(App.getText("database"));
        alert.getButtonTypes().addAll(file,database);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == file) new LoadFile();
        else if (result.isPresent() && result.get() == database) {
            new LoadScreen();
            Task task = new LoadDatabase("DATABASE");
            Thread thread = new Thread(task);
            thread.start();
            WindowExitEvent.setTask(thread);
            task.setOnFailed(new LoadingDatabaseErrorEvent());
            task.messageProperty().addListener((o, s, n) -> new MainApp());
            task.setOnFailed(new LoadingDatabaseErrorEvent());
        } else if (result.isPresent() && result.get() == ButtonType.OK) System.exit(0);

    }
}
