package pl.edu.wat.wcy.isi.prz.javafx;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.Prop;
import pl.edu.wat.wcy.isi.prz.javafxfactory.VBoxFactory;

class StopDetails {

    private final Stage stage = App.getStage();

    StopDetails() {
        makeInterface();
    }

    private void makeInterface() {
        stage.hide();
        VBoxFactory vBoxFactory = new VBoxFactory();

        VBox groupBox = vBoxFactory.getVBox("STOPGROUPINFO").createVBox();
        VBox stopBox = vBoxFactory.getVBox("STOPINFO").createVBox();

        HBox hBox = new HBox();
        hBox.setFillHeight(true);
        hBox.getChildren().addAll(groupBox,stopBox);
        HBox.setHgrow(stopBox, Priority.ALWAYS);

        Scene scene = new Scene(hBox);
        scene.getStylesheets().add(Prop.getValue("default.css"));

        stage.setScene(scene);
        stage.setTitle(App.getText("details"));
        stage.show();
    }
}
