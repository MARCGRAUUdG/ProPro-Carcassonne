import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;

public class Gui extends Application{
    //APP
    private int altura=700;
    private int ample=550;
    private VBox root;
    private Scene scene;

    //TOP
    private AnchorPane topRow = new AnchorPane();
    private TextField textField = new TextField ();
    private Button buttonFile = new Button();
    private double buttonSize=100.0;

    //MID
    private AnchorPane midRow = new AnchorPane();
    private Image image;
    private ImageView imageView;

    //BOT
    private static Collection<String> list;
    private static ObservableList<String> details;
    private static TableView<String> table;
    private static TableColumn<String, String> log;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image logo = new Image(new FileInputStream("doc\\images\\Logo.png"));
        primaryStage.getIcons().add(logo);
        root = new VBox(5);
        root.setPadding(new Insets(5));

        //TOP
        textField.setPromptText("Introdueix el nom del fitxer");
        buttonFile.setText("Carregar Fitxer");
        buttonFile.setPrefSize(buttonSize,20.0);
        buttonFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addRow("Fitxer '"+textField.getText()+"' carregant");
            }
        });

        AnchorPane.setLeftAnchor(textField, 0.0);
        AnchorPane.setRightAnchor(buttonFile, 0.0);
        AnchorPane.setLeftAnchor(textField,0.0);
        AnchorPane.setRightAnchor(textField,buttonSize+5.0);

        topRow.getChildren().addAll(textField,buttonFile);

        //MIG
        image = new Image(new FileInputStream("doc\\images\\taulerimg.jpg"));
        imageView = new ImageView(image);
        imageView.setFitHeight(ample);
        imageView.setFitWidth(ample);

        midRow.getChildren().addAll(imageView);

        //BOT
        list = new ArrayList<>();
        details = FXCollections.observableArrayList(list);

        table = new TableView<>();
        log = new TableColumn<>();
        table.getColumns().addAll(log);
        table.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
                Pane header = (Pane) table.lookup("TableHeaderRow");
                if (header.isVisible()){
                    header.setMaxHeight(0);
                    header.setMinHeight(0);
                    header.setPrefHeight(0);
                    header.setVisible(false);
                }
            }
        });
        log.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        table.setItems(details);

        log.setResizable(false);
        log.setSortable(false);
        log.setMinWidth(ample-15);
        //------------------
        root.getChildren().addAll(topRow, midRow, table);

        scene = new Scene(root, ample, altura);
        primaryStage.setTitle("Carcassonne");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void addRow(String in){
        list.add(in);
        details = FXCollections.observableArrayList(list);
        table.setItems(details);
    }
}