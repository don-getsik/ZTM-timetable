package pl.edu.wat.wcy.isi.prz.javafx;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.wat.wcy.isi.prz.App;
import pl.edu.wat.wcy.isi.prz.Prop;
import pl.edu.wat.wcy.isi.prz.javafxfactory.VBoxFactory;
import pl.edu.wat.wcy.isi.prz.listeners.SearchButtonEvent;

import java.util.Locale;

public class MainApp {

    private final Stage stage = App.getStage();

    public MainApp() {
        makeAppInterface();
    }

    private MenuBar menuBar () {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(getMenuDatabase(), getMenuLanguage(), getMenuGoogle(), getMenuSkin());
        return menuBar;
    }

    private Menu getMenuSkin() {
        Menu menuSkin  = new Menu(App.getText("skin"));
        MenuItem standardSkin = new MenuItem(App.getText("standard"));
        standardSkin.setOnAction(e -> {
            Prop.setValue("default.css" ,"standard.css");
            new MainApp();
        });
        MenuItem colorfulSkin = new MenuItem(App.getText("colorful"));
        colorfulSkin.setOnAction(e -> {
            Prop.setValue("default.css" ,"colorful.css");
            new MainApp();
        });
        MenuItem shadowSkin = new MenuItem(App.getText("shadow"));
        shadowSkin.setOnAction(e -> {
            Prop.setValue("default.css" ,"shadow.css");
            new MainApp();
        });
        menuSkin.getItems().addAll(standardSkin,colorfulSkin, shadowSkin);
        return menuSkin;
    }

    private Menu getMenuGoogle() {
        Menu menuGoogle = new Menu(App.getText("google"));
        MenuItem onAlt = new MenuItem(App.getText("alternatives.on"));
        onAlt.setOnAction(e -> Prop.setValue("google.alternatives", "true"));
        MenuItem offAlt = new MenuItem(App.getText("alternatives.off"));
        offAlt.setOnAction(e -> Prop.setValue("google.alternatives","false"));
        MenuItem search = new MenuItem(App.getText("search.button"));
        search.setOnAction(new SearchButtonEvent());
        menuGoogle.getItems().addAll(onAlt,offAlt,search);
        return menuGoogle;
    }

    private Menu getMenuLanguage() {
        Menu menuLanguage = new Menu(App.getText("menu.language"));
        MenuItem menuPolish = new MenuItem(App.getText("polish"));
        menuPolish.setOnAction(e -> App.setResourceBundle(new Locale("pl", "PL")));
        MenuItem menuEnglish = new MenuItem(App.getText("english"));
        menuEnglish.setOnAction(e -> App.setResourceBundle(new Locale("en","EN")));
        menuLanguage.getItems().addAll(menuPolish,menuEnglish);
        return menuLanguage;
    }

    private Menu getMenuDatabase() {
        Menu menuDatabase = new Menu(App.getText("database"));
        MenuItem menuLoad = new MenuItem(App.getText("load.from.file"));
        menuLoad.setOnAction(e -> new LoadFile());
        MenuItem menuDetails = new MenuItem(App.getText("details"));
        menuDetails.setOnAction(e -> new StopDetails());
        menuDatabase.getItems().addAll(menuLoad, menuDetails);
        return menuDatabase;
    }

    private void makeAppInterface() {
        stage.hide();
        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = menuBar();
        VBoxFactory vBoxFactory = new VBoxFactory();

        VBox originBox = vBoxFactory.getVBox("ORIGIN").createVBox();
        VBox destinationBox = vBoxFactory.getVBox("DESTINATION").createVBox();
        VBox resultBox = vBoxFactory.getVBox("RESULT").createVBox();

        HBox hBox = new HBox();
        hBox.setFillHeight(true);
        hBox.getChildren().addAll(originBox,destinationBox,resultBox);
        HBox.setHgrow(resultBox, Priority.ALWAYS);

        borderPane.setTop(menuBar);
        borderPane.setCenter(hBox);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(Prop.getValue("default.css"));

        stage.setScene(scene);
        stage.setTitle(App.getText("stage.appname"));
        stage.show();
    }
}
