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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

public class Gui extends Application{
    //APP
    private static int altura=700;
    private static int ample=550;
    private VBox root;
    private Scene scene;

    //TOP
    private AnchorPane topRow = new AnchorPane();
    private TextField textField = new TextField ();
    private Button buttonFile = new Button();
    private double buttonSize=100.0;

    //MID
    private static AnchorPane midRow = new AnchorPane();
    private ImageView imageView;

    private static int[] pos = { 70, 110, 150, 190, 230,  270, 310, 350, 390, 430};
    private static Text scorej1=new Text (40+20, 50, "Score: 0");
    private static Text scorej2=new Text (ample-150, 50, "Score: 0");
    private static Text scorej3=new Text (ample-150, ample-5, "Score: 0");
    private static Text scorej4=new Text (40+20, ample-5, "Score: 0");

    //BOT
    private static Collection<String> list;
    private static ObservableList<String> details;
    private static TableView<String> table;
    private static TableColumn<String, String> log;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        initConfig(primaryStage);

        setupMainTop();
        setupMainMiddle();
        setupMainBot();

        setupScene(primaryStage);
        initApp();
    }

    private void initConfig(Stage primaryStage) {
        Image logo = getImage("doc\\images\\Logo.png");
        primaryStage.getIcons().add(logo);
        root = new VBox(5);
        root.setPadding(new Insets(5));
    }

    private void setupScene(Stage primaryStage) {
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

    private void initApp(){
        Joc j=new Joc();
    }

    private void setupMainTop(){
        textField.setPromptText("Introdueix el nom del fitxer");
        buttonFile.setText("Carregar Fitxer");
        buttonFile.setPrefSize(buttonSize,20.0);
        buttonFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Joc.repNomFitxer(textField.getText());
            }
        });

        AnchorPane.setLeftAnchor(textField, 0.0);
        AnchorPane.setRightAnchor(buttonFile, 0.0);
        AnchorPane.setLeftAnchor(textField,0.0);
        AnchorPane.setRightAnchor(textField,buttonSize+5.0);

        topRow.getChildren().addAll(textField,buttonFile);
    }

    private void setupMainMiddle(){
        Image image = getImage("doc\\images\\taulerimg.jpg");
        imageView = new ImageView(image);
        imageView.setFitHeight(ample);
        imageView.setFitWidth(ample);
        midRow.getChildren().addAll(imageView);

        setupJugadors(4,"Jugador1", "Jugador2", "Jugador3","Jugador4");

        Image blackimg = getImage("src\\images\\black.png");
        ImageView blackView = new ImageView(blackimg);
        blackView.setFitHeight(ample-140);
        blackView.setFitWidth(ample-140);
        blackView.setLayoutX(70);blackView.setLayoutY(70);
        midRow.getChildren().addAll(blackView);
    }

    private void setupMainBot(){
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
    }

    public void setupJugadors(int nJugadors, String n1, String n2, String n3, String n4) {
        Image playerImg1 = getImage("src\\images\\p1.png");
        ImageView jug1=new ImageView();
        jug1 = new ImageView(playerImg1);
        jug1.setFitHeight(40);
        jug1.setFitWidth(40);
        jug1.setLayoutX(10);jug1.setLayoutY(10);
        Text t1 = new Text (40+20, 35, n1);
        t1.setFont(Font.font("Arial Black",15));
        t1.setFill(Color.WHITE);
        scorej1.setFont(Font.font("Arial Black",10));
        scorej1.setFill(Color.WHITE);
        midRow.getChildren().addAll(jug1,t1,scorej1);

        Image playerImg2 = getImage("src\\images\\p2.png");
        ImageView jug2=new ImageView();
        jug2 = new ImageView(playerImg2);
        jug2.setFitHeight(40);
        jug2.setFitWidth(40);
        jug2.setLayoutX(ample-40-10);jug2.setLayoutY(10);
        Text t2 = new Text (ample-150, 35, n2);
        t2.setFont(Font.font("Arial Black",15));
        t2.setFill(Color.WHITE);
        scorej2.setFont(Font.font("Arial Black",10));
        scorej2.setFill(Color.WHITE);
        midRow.getChildren().addAll(jug2,t2,scorej2);

        if(nJugadors>2){
            Image playerImg3 = getImage("src\\images\\p3.png");
            ImageView jug3=new ImageView();
            jug3 = new ImageView(playerImg3);
            jug3.setFitHeight(40);
            jug3.setFitWidth(40);
            jug3.setLayoutX(ample-40-10);jug3.setLayoutY(ample-10-40);
            Text t3 = new Text (ample-150, ample-20, n3);
            t3.setFont(Font.font("Arial Black",15));
            t3.setFill(Color.WHITE);
            scorej3.setFont(Font.font("Arial Black",10));
            scorej3.setFill(Color.WHITE);
            midRow.getChildren().addAll(jug3,t3,scorej3);

            if(nJugadors>3){
                Image playerImg4 = getImage("src\\images\\p4.png");
                ImageView jug4=new ImageView();
                jug4 = new ImageView(playerImg4);
                jug4.setFitHeight(40);
                jug4.setFitWidth(40);
                jug4.setLayoutX(10);jug4.setLayoutY(ample-10-40);
                Text t4 = new Text (40+20, ample-20, n4);
                t4.setFont(Font.font("Arial Black",15));
                t4.setFill(Color.WHITE);
                scorej4.setFont(Font.font("Arial Black",10));
                scorej4.setFill(Color.WHITE);
                midRow.getChildren().addAll(jug4,t4,scorej4);
            }
        }
    }
    private static Image getImage(String url){
        Image imgR= null;
        try {
            imgR = new Image(new FileInputStream(url));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imgR;
    }

    public static void posaFitxa(int x, int y, int id){
        Image fitxaImg = getImage("src\\images\\f"+id+".jpg");
        ImageView fitxa=new ImageView(fitxaImg);
        fitxa.setLayoutX(pos[x]);fitxa.setLayoutY(pos[y]);
        fitxa.setFitHeight(40);
        fitxa.setFitWidth(40);
        midRow.getChildren().addAll(fitxa);
    }

    public static void setScore(int jugador,int punts){
        String s="Score: "+punts;
        if(jugador==1)
            scorej1.setText(s);
        else if(jugador==2)
            scorej2.setText(s);
        else if(jugador==3)
            scorej3.setText(s);
        else
            scorej4.setText(s);
    }
}