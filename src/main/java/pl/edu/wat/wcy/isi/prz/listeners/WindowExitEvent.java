package pl.edu.wat.wcy.isi.prz.listeners;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import pl.edu.wat.wcy.isi.prz.database.DatabaseConnection;

public class WindowExitEvent implements EventHandler<WindowEvent> {

    private static Thread thread;

    public static void setTask (Thread thread) {WindowExitEvent.thread = thread;}

    @Override
    public void handle(WindowEvent event) {
        if(thread.isAlive()) thread.stop();
        DatabaseConnection.get().getFactory().close();
    }
}
