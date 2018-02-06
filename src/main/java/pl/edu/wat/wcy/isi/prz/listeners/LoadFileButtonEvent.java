package pl.edu.wat.wcy.isi.prz.listeners;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import pl.edu.wat.wcy.isi.prz.javafx.LoadScreen;
import pl.edu.wat.wcy.isi.prz.javafx.MainApp;
import pl.edu.wat.wcy.isi.prz.listofstops.LoadDatabase;

public class LoadFileButtonEvent implements EventHandler<ActionEvent> {

    private final TextField textField;
    private final CheckBox checkBox;

    public LoadFileButtonEvent(TextField textField, CheckBox checkBox) {
        this.checkBox = checkBox;
        this.textField = textField;
    }

    @Override
    public void handle(ActionEvent event) {
        new LoadScreen();
        Task task;
        if (checkBox.isSelected()) task = new LoadDatabase("FILEONLINE", textField.getText());
        else task = new LoadDatabase("FILEOFFLINE", textField.getText());
        Thread thread = new Thread(task);
        WindowExitEvent.setTask(thread);
        thread.start();
        WindowExitEvent.setTask(thread);
        task.messageProperty().addListener((o, s, n) -> new MainApp());
        task.setOnFailed(new LoadingDatabaseErrorEvent());
    }
}
