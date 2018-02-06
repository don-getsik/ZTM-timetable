package pl.edu.wat.wcy.isi.prz.javafx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.Prop;

public class LoadScreen {

    private final Stage stage = App.getStage();

    public LoadScreen() {
        makeLoadingScreen();
    }

    private void makeLoadingScreen() {
        if(stage.isShowing()) stage.close();
        stage.setTitle(App.getText("stage.loaddatabasename"));
        VBox vBox = new VBox();

        Label label = new Label(App.getText("label.loadscreen"));
        label.setPadding(new Insets(10));

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setPadding(new Insets(10));
        vBox.getChildren().addAll(label,progressIndicator);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(Prop.getValue("default.css"));

        stage.setScene(scene);
        stage.show();
    }
}
