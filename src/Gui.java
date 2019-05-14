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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Gui{
   //APP
    private static int altura=700;
    private static int ample=550;
    private VBox root;
    private Scene scene;

    //TOP
    private AnchorPane topRow = new AnchorPane();
    private static TextField textField = new TextField ();
    private static Button buttonFile = new Button();
    private double buttonSize=100.0;

    //MID
    private static AnchorPane midRow = new AnchorPane();
    private ImageView imageView;
    private static Text comenca=new Text();

    private static int[] pos = { 75, 115, 155, 195, 235,  275, 315, 355, 395, 435};
    private static Text scorej1=new Text (40+20, 50, "Score: 0");
    private static Text scorej2=new Text (ample-150, 50, "Score: 0");
    private static Text scorej3=new Text (ample-150, ample-15, "Score: 0");
    private static Text scorej4=new Text (40+20, ample-15, "Score: 0");
    private static int nBlocksVerdsPosats=0;
    private static AnchorPane baralla = new AnchorPane();
    private static AnchorPane opcioRotacio = new AnchorPane();
    private static Text nseguidors[]=new Text[4];

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

    //Pre:--
    //Post:inicialitza stage
    private void initConfig(Stage primaryStage) {
        Image logo = getImage("src\\images\\Logo.png");
        primaryStage.getIcons().add(logo);
        root = new VBox(5);
        root.setPadding(new Insets(5));
    }

    //Pre:--
    //Post:configura i mostra scene
    private void setupScene(Stage primaryStage) {
        root.getChildren().addAll(topRow, midRow, table);

        scene = new Scene(root, ample, altura);
        primaryStage.setTitle("Carcassonne");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Pre:taula, stage i scene inicialitzat
    //Post:Mostra per la taula de la gui el stream
    public static void print(String stream){
        list.add(stream);
        details = FXCollections.observableArrayList(list);
        table.setItems(details);
        table.scrollTo(details.size());
    }

    //Pre:Gui configurada
    //Post: Comença joc
    private void initApp(){
        Joc j=new Joc();
    }

    //Pre:--
    //Post:Configura part top de la GUI
    private void setupMainTop(){
        textField.setPromptText("Introdueix el nom del fitxer");
        textField.setText("p1.txt");
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                llegeigNomDelFitxer();
            }
        });
        buttonFile.setText("Carregar Fitxer");
        buttonFile.setPrefSize(buttonSize,20.0);
        buttonFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                llegeigNomDelFitxer();
            }
        });

        AnchorPane.setLeftAnchor(textField, 0.0);
        AnchorPane.setRightAnchor(buttonFile, 0.0);
        AnchorPane.setLeftAnchor(textField,0.0);
        AnchorPane.setRightAnchor(textField,buttonSize+5.0);

        topRow.getChildren().addAll(textField,buttonFile);
    }

    private void llegeigNomDelFitxer(){
        buttonFile.setDisable(true);
        textField.setDisable(true);
        Joc.repNomFitxer(textField.getText());
    }

    //Pre:--
    //Post:Configura part mid de la GUI
    private void setupMainMiddle(){
        Image image = getImage("src\\images\\taulerimg.jpg");
        imageView = new ImageView(image);
        imageView.setFitHeight(ample);
        imageView.setFitWidth(ample);

        comenca=new Text(180,100,"Carrega el fitxer\n per començar");
        comenca.setFont(Font.font("Arial Black",20));
        comenca.setFill(Color.WHITE);comenca.setTextAlignment(TextAlignment.CENTER);

        midRow.getChildren().addAll(imageView,comenca);
    }

    //Pre:--
    //Post:Configura part bot de la GUI
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

    //Pre:Mid inicialitzat
    //Post:Configura jugadors
    public static void setupJugadors(int nJugadors) {
        Image playerImg1 = getImage("src\\images\\p1.png");
        ImageView jug1=new ImageView();
        jug1 = new ImageView(playerImg1);
        jug1.setFitHeight(40);
        jug1.setFitWidth(40);
        jug1.setLayoutX(10);jug1.setLayoutY(10);
        Text t1 = new Text (40+20, 35, "Jugador1");
        t1.setFont(Font.font("Arial Black",15));
        t1.setFill(Color.WHITE);
        scorej1.setFont(Font.font("Arial Black",10));
        scorej1.setFill(Color.WHITE);
        nseguidors[0] = new Text (18, 35, "10");
        nseguidors[0].setFont(Font.font("Arial Black",15));
        nseguidors[0].setFill(Color.WHITE);
        midRow.getChildren().addAll(jug1,t1,scorej1,nseguidors[0]);

        Image playerImg2 = getImage("src\\images\\p2.png");
        ImageView jug2=new ImageView();
        jug2 = new ImageView(playerImg2);
        jug2.setFitHeight(40);
        jug2.setFitWidth(40);
        jug2.setLayoutX(ample-40-10);jug2.setLayoutY(10);
        Text t2 = new Text (ample-150, 35, "Jugador2");
        t2.setFont(Font.font("Arial Black",15));
        t2.setFill(Color.WHITE);
        scorej2.setFont(Font.font("Arial Black",10));
        scorej2.setFill(Color.WHITE);
        nseguidors[1] = new Text (ample-42, 35, "10");
        nseguidors[1].setFont(Font.font("Arial Black",15));
        midRow.getChildren().addAll(jug2,t2,scorej2,nseguidors[1]);

        if(nJugadors>2){
            Image playerImg3 = getImage("src\\images\\p3.png");
            ImageView jug3=new ImageView();
            jug3 = new ImageView(playerImg3);
            jug3.setFitHeight(40);
            jug3.setFitWidth(40);
            jug3.setLayoutX(ample-40-10);jug3.setLayoutY(ample-10-40);
            Text t3 = new Text (ample-150, ample-30, "Jugador3");
            t3.setFont(Font.font("Arial Black",15));
            t3.setFill(Color.WHITE);
            scorej3.setFont(Font.font("Arial Black",10));
            scorej3.setFill(Color.WHITE);
            nseguidors[2] = new Text (ample-42, ample-25, "10");
            nseguidors[2].setFont(Font.font("Arial Black",15));
            nseguidors[2].setFill(Color.WHITE);
            midRow.getChildren().addAll(jug3,t3,scorej3,nseguidors[2]);

            if(nJugadors>3){
                Image playerImg4 = getImage("src\\images\\p4.png");
                ImageView jug4=new ImageView();
                jug4 = new ImageView(playerImg4);
                jug4.setFitHeight(40);
                jug4.setFitWidth(40);
                jug4.setLayoutX(10);jug4.setLayoutY(ample-10-40);
                Text t4 = new Text (40+20, ample-30, "Jugador4");
                t4.setFont(Font.font("Arial Black",15));
                t4.setFill(Color.WHITE);
                scorej4.setFont(Font.font("Arial Black",10));
                scorej4.setFill(Color.WHITE);
                nseguidors[3] = new Text (18, ample-25, "10");
                nseguidors[3].setFont(Font.font("Arial Black",15));
                midRow.getChildren().addAll(jug4,t4,scorej4,nseguidors[3]);
            }
        }
    }

    //Pre:--
    //Post:retorna la imatge amb una url si no es correcte mostra error i retorna null
    private static Image getImage(String url){
        Image imgR= null;
        try {
            imgR = new Image(new FileInputStream(url));
        } catch (FileNotFoundException e) {
            print("Error amb la lecture de l'imatge "+url);
        }
        return imgR;
    }

    //Pre:Mid inicialitzat, la fitxa f ha de tenir una posicio asignada correcte
    //Post:Posa fitxa en el tauler de la Gui
    public static void posaFitxa(Fitxa f){
        Posicio posicio=f.getPosicio();
        Image fitxaImg = getImage("src\\images\\"+f.formatNormal()+".jpg");
        ImageView fitxa=new ImageView(fitxaImg);
        fitxa.setLayoutX(pos[posicio.getPosicioX()]);fitxa.setLayoutY(pos[posicio.getPosicioY()]);
        fitxa.setFitHeight(40);
        fitxa.setFitWidth(40);
        fitxa.setRotate(posicio.getRotacio());
        midRow.getChildren().addAll(fitxa);
    }

    //Pre:Mid i Jugador inicialitzat
    //Post:actualitza els punts del jugador
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

    //Pre:Mid inicialitzat
    //Post:configura el tauler
    public static void iniciaTaulerGui() {
        Image blackimg = getImage("src\\images\\black.png");
        ImageView blackView = new ImageView(blackimg);
        blackView.setFitHeight(ample-150);
        blackView.setFitWidth(ample-150);
        blackView.setLayoutX(75);blackView.setLayoutY(75);
        midRow.getChildren().remove(comenca);
        midRow.getChildren().addAll(blackView);
        midRow.getChildren().add(baralla);
        midRow.getChildren().add(opcioRotacio);
    }

    //Pre:njugador>=1 && njugador<=4, Mid i Jugador inicialitzat
    //Post:Actualitzat el nSeguidors del jugador
    public static void setSeguidors(int nSeguidors,int jugador) {
        if(nSeguidors<10) {
            if (jugador == 1 || jugador == 4)
                nseguidors[jugador - 1].setX(21);
            else
                nseguidors[jugador - 1].setX(ample - 38);
        }else{
            if (jugador == 1 || jugador == 4)
                nseguidors[jugador - 1].setX(18);
            else
                nseguidors[jugador - 1].setX(ample - 42);
        }
        nseguidors[jugador-1].setText(""+nSeguidors);
    }

    //Pre:--
    //Post:Infroma per pantalla que el fitxer es incorrecte per el motiou "motiu" i torna a demana fitxer entrada
    public static void informarFitxerEntradaIncorrecte(String motiu){
        print("Fitxer incorrecte! Motiu: "+motiu+". Torna a introduïr el nom del fitxer.");
        buttonFile.setDisable(false);
        textField.setText("");
        textField.setDisable(false);
    }

    //Pre:Mid inicialitzat
    //Post:Mostra la baralla amb un maxim de 20 cartes ocultes
    public static void MostraBaralla(int size, Fitxa f){
        if(f!=null) {
            Image backFitxaImg = getImage("src\\images\\back.jpg");
            Image frontFitxaImg = getImage("src\\images\\" + f.formatNormal() + ".jpg");
            ImageView frontFitxa = new ImageView(frontFitxaImg);
            baralla.getChildren().clear();
            int maxsize= 130;
            if(size<20) maxsize= (int) (size*6.5);
            int x = 0;
            for (int i = 0; i < size && i < 20; i++) {
                ImageView backFitxa = new ImageView(backFitxaImg);
                backFitxa.setLayoutX(ample / 2 - maxsize + x);
                backFitxa.setLayoutY(ample - 50);
                backFitxa.setFitHeight(40);
                backFitxa.setFitWidth(40);
                baralla.getChildren().add(backFitxa);
                x += 10;
            }

            frontFitxa.setFitHeight(40);
            frontFitxa.setFitWidth(40);
            frontFitxa.setLayoutX(ample / 2 - maxsize + x);
            frontFitxa.setLayoutY(ample - 50);
            baralla.getChildren().addAll(frontFitxa);
        }else
            baralla.getChildren().clear();
    }

    //Pre:Mid i tauler inicialitzat
    //Post:Posa opcio de posar fitxa en el tauler de la posicio x, y amb rotacio
    private static void posaQuadreVerd(int x, int y, int rot){
        Image fitxaImg = getImage("src\\images\\green.png");
        ImageView quadre=new ImageView(fitxaImg);
        quadre.setLayoutX(pos[x]);quadre.setLayoutY(pos[y]);
        quadre.setFitHeight(40);
        quadre.setFitWidth(40);
        quadre.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                int x= (int) ((t.getSceneX()-81)/40);
                int y= (int) ((t.getSceneY()-110)/40);
                Posicio p=new Posicio(x,y,0);
                treuQuadresVerds();

                Joc.apretatPerPosarFitxa(x,y, rot);
            }
        });
        midRow.getChildren().addAll(quadre);
    }

    //Pre:--
    //Post:Elimina de la gui el ultim element (Funcio perillosa Un gran poder conlleva una gran responsabilidad)
    private static void treuUltimElement() {
        midRow.getChildren().remove(midRow.getChildren().size()-1);
    }

    //Pre:Mid i tauler inicialitzat
    //Post:Posa en les posicions alp quadres verds d'opcions per ficar fitxa
    public static void posaQuadresVerds(ArrayList<Posicio> alp){
        print("Sel·leccina col·locació de la fitxa");
        nBlocksVerdsPosats=alp.size();
        for(int i=0;i<alp.size();i++){
            posaQuadreVerd(alp.get(i).getPosicioX(),alp.get(i).getPosicioY(),alp.get(i).getRotacio());
        }
    }

    //Pre:Mid i tauler inicialitzat i haver cridat correctament posaQuadresVerds() avans
    //Post:Treu les opcions dels quadres verds
    private static void treuQuadresVerds(){
        for(int i=0;i<nBlocksVerdsPosats;i++)
            treuUltimElement();
    }

    //Pre:Mid i tauler inicialitzat, una fitxa posada en la posicio x,y
    //Post:Posa les opcions per colocar seguidor en la posicio x,y
    public static void posaSeleccioDeSeguidors(int x, int y) {
        print("Sel·lecciona col·locació del seguidor");
        Image seguidorImg = getImage("src\\images\\pb.png");
        ImageView seguidorC=new ImageView(seguidorImg);
        ImageView seguidorN=new ImageView(seguidorImg);
        ImageView seguidorE=new ImageView(seguidorImg);
        ImageView seguidorS=new ImageView(seguidorImg);
        ImageView seguidorO=new ImageView(seguidorImg);

        configuraImgSeguidor(seguidorC,pos[x]+15,pos[y]+15,'C');
        configuraImgSeguidor(seguidorN,pos[x]+15,pos[y],'N');
        configuraImgSeguidor(seguidorE,pos[x]+30,pos[y]+15,'E');

        configuraImgSeguidor(seguidorS,pos[x]+15,pos[y]+30, 'S');
        configuraImgSeguidor(seguidorO,pos[x],pos[y]+15, 'O');

        Image crossImg = getImage("src\\images\\cross.png");
        ImageView cross=new ImageView(crossImg);
        cross.setLayoutX(pos[x]+30);cross.setLayoutY(pos[y]+2);
        cross.setFitHeight(8);cross.setFitWidth(8);
        cross.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                int x= (int) ((t.getSceneX()-81)/40);
                int y= (int) ((t.getSceneY()-110)/40);
                treuSeguidors();
                Joc.apretatPerPosarSeguidor(x, y, 'X');
            }
        });

        midRow.getChildren().addAll(seguidorC,seguidorN,seguidorE,seguidorS,seguidorO,cross);
    }

    //Pre:iv esta inicialitzat
    //Post:configura tamany i direccio dir del seguidor en posicio x i y
    public static void configuraImgSeguidor(ImageView iv, int x, int y, char dir){
        iv.setLayoutX(x);iv.setLayoutY(y);
        iv.setFitHeight(10);
        iv.setFitWidth(10);
        iv.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                int x= (int) ((t.getSceneX()-81)/40);
                int y= (int) ((t.getSceneY()-110)/40);
                treuSeguidors();
                Joc.apretatPerPosarSeguidor(x, y, dir);
            }
        });
    }

    //Pre:Haver cridat correctament configuraImgSeguidor() avans
    //Post:Treu la seleccio de posicionament dels seguidors
    private static void treuSeguidors() {
        for(int i=0;i<6;i++)
            treuUltimElement();
    }

    //Pre:--
    //Post:Posa el seguidor en la posicio dir del tauler x,y que pertany al jugador numbJugador
    public static void posaSeguidor(int x, int y, char dir, int numbJugador) {
        Image seguidorImg = getImage("src\\images\\p"+numbJugador+".png");
        ImageView seguidor=new ImageView(seguidorImg);
        seguidor.setFitHeight(10);
        seguidor.setFitWidth(10);

        if(dir=='C') {
            seguidor.setLayoutX(pos[x]+15);seguidor.setLayoutY(pos[y]+15);
        }else if(dir=='N'){
            seguidor.setLayoutX(pos[x]+15);seguidor.setLayoutY(pos[y]);
        }else if(dir=='E'){
            seguidor.setLayoutX(pos[x]+30);seguidor.setLayoutY(pos[y]+15);
        }else if(dir=='S'){
            seguidor.setLayoutX(pos[x]+15);seguidor.setLayoutY(pos[y]+30);
        }else if(dir=='O'){
            seguidor.setLayoutX(pos[x]);seguidor.setLayoutY(pos[y]+15);
        }
        midRow.getChildren().addAll(seguidor);
    }

    public static void mostraOpcionsDeRotacioEnFitxa(ArrayList<Posicio> posDisp, Fitxa fitxaActual) {
        print("Sel·lecciona rotació de la fitxa");
        Image FitxaImg = getImage("src\\images\\"+fitxaActual.formatNormal()+".jpg");
        for(int i=0;i<posDisp.size();i++){
            Posicio posActual=posDisp.get(i);
            ImageView fitxa = new ImageView(FitxaImg);
            fitxa.setLayoutX(15);
            fitxa.setLayoutY(ample/2-posDisp.size()*20+50*i);
            fitxa.setFitHeight(40);
            fitxa.setFitWidth(40);
            fitxa.setRotate(posDisp.get(i).getRotacio());
            fitxa.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    treuUltimElement();
                    opcioRotacio.getChildren().clear();
                    Joc.apretatAngleFitxa(posActual);
                }
            });
            Image fitxaImg = getImage("src\\images\\green.png");
            ImageView quadre=new ImageView(fitxaImg);
            quadre.setLayoutX(pos[posDisp.get(0).getPosicioX()]);
            quadre.setLayoutY(pos[posDisp.get(0).getPosicioY()]);
            quadre.setFitHeight(40);
            quadre.setFitWidth(40);
            midRow.getChildren().addAll(quadre);
            opcioRotacio.getChildren().add(fitxa);
        }
    }
}