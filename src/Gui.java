/**
\mainpage Documentació del Carcassone
@section S Pràctica final ProPro
El programa tracta del joc de carcassone que tracta de emplenar el tauler amb unes
determinades fitxes que encaixin entre ells, i guanyar dominis per obtenir mes puntuació
que la resta de jugadors.

- @ref Entrada: Fitxer que s'introdueix per teclat amb especificacio de nº jugadors, si es cpu o controlable i les rajoles.
Interaccio per el ratoli fent clicks a la interficio per controla el jugador actual si es humà.
- @ref Sortida: Estat del Joc visual per pantalla, i informacio d'ell, com puntuacio, tauler, nºSeguidors...

@section Practica Informació
@author Javier Muñoz
@author Teng Liu
@author Marc Grau
@version 1.0
@date 21/05/2019
*/
/// @file

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

///@class Gui

///@brief Clase main, gestió de la interficie i control del jugador actual en cas de ser controlable.

public class Gui extends Application{
   //APP
    private static int altura=700;///<Altura total de la interficie
    private static int ample=550;///<Ample total de la interficie
    private VBox root;///<Finestra root de la interficie
    private Scene scene;///<Escena de la interficie

    //TOP
    private AnchorPane topRow = new AnchorPane();///<Panell de la part de dalt (Entrada de fitxer)
    private static TextField textField = new TextField ();///<Barra d'entrada del nom del fitxer d'entrada
    private static Button buttonFile = new Button();///<Botó de carrega del fitxer d'entrada
    private double buttonSize=100.0;///<Tamany del botó anterior

    //MID
    private static AnchorPane midRow = new AnchorPane();///<Panell de la part del mig (tauler)
    private ImageView imageView;///<Fons del tauler de fusta
    private static Text comenca=new Text();///<Informacio de introduïr nom del fitxer d'entrada

    private static int[] pos = { 75, 115, 155, 195, 235,  275, 315, 355, 395, 435};///<Posicions on encaixen les fitxes en el tauler
    private static Text scorej1=new Text (40+20, 50, "Score: 0");///<Puntuacio del jugador 1
    private static Text scorej2=new Text (ample-150, 50, "Score: 0");///<Puntuacio del jugador 2
    private static Text scorej3=new Text (ample-150, ample-15, "Score: 0");///<Puntuacio del jugador 3
    private static Text scorej4=new Text (40+20, ample-15, "Score: 0");///<Puntuacio del jugador 4
    private static int nBlocksVerdsPosats=0;///<Blocks verds posats per despres eliminar el mateixnº d'elements
    private static AnchorPane baralla = new AnchorPane();///<Panell on hi ha la baralla a mostrar
    private static AnchorPane opcioRotacio = new AnchorPane();///<Panell on hi ha les opcions de rotació de la fitxa
    private static Text nseguidors[]=new Text[4];///<Nº de seguidors de cada jugador

    //BOT
    private static Collection<String> list;///<Panell de la part de baix (log)
    private static ObservableList<String> details;///<details de la taula
    private static TableView<String> table;///<Taula log on es registra la informació
    private static TableColumn<String, String> log;///<Files de la taula anterior

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

    ///@pre --
    ///@post inicialitza stage
    private void initConfig(Stage primaryStage) {
        Image logo = getImage("images/Logo.png");
        primaryStage.getIcons().add(logo);
        root = new VBox(5);
        root.setPadding(new Insets(5));
    }

    ///@pre --
    ///@post configura i mostra scene
    private void setupScene(Stage primaryStage) {
        root.getChildren().addAll(topRow, midRow, table);

        scene = new Scene(root, ample, altura);
        primaryStage.setTitle("Carcassonne");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    ///@pre taula, stage i scene inicialitzat
    ///@post Mostra per la taula de la gui el stream
    public static void print(String stream){
        list.add(stream);
        details = FXCollections.observableArrayList(list);
        table.setItems(details);
        table.scrollTo(details.size());
    }

    ///@pre Gui configurada
    ///@post  Comença joc
    private void initApp(){
        Joc j=new Joc();
    }

    ///@pre --
    ///@post Configura part top de la GUI
    private void setupMainTop(){
        textField.setPromptText("Introdueix el nom del fitxer");
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

    ///@pre --
    ///@post Envia el text que hi ha introduit al joc
    private void llegeigNomDelFitxer(){
        buttonFile.setDisable(true);
        textField.setDisable(true);
        Joc.repNomFitxer(textField.getText());
    }

    ///@pre --
    ///@post Configura part mid de la GUI
    private void setupMainMiddle(){
        Image image = getImage("images/taulerimg.jpg");
        imageView = new ImageView(image);
        imageView.setFitHeight(ample);
        imageView.setFitWidth(ample);

        comenca=new Text(180,100,"Carrega el fitxer\n per començar");
        comenca.setFont(Font.font("Arial Black",20));
        comenca.setFill(Color.WHITE);comenca.setTextAlignment(TextAlignment.CENTER);

        midRow.getChildren().addAll(imageView,comenca);
    }

    ///@pre --
    ///@post Configura part bot de la GUI
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

    ///@pre Les fitxes de la llista tenen posicio correcte
    ///@post Treu el seguidor on estan les fitxes del conjunt
    public static void treuSeguidorsDe(List<Fitxa> conjunt) {
        for(int i=0;i<conjunt.size();i++){
            posaFitxa(conjunt.get(i));
        }
    }

    ///@pre Mid inicialitzat
    ///@post Configura jugadors
    public static void setupJugadors(int nJugadors) {
        Image playerImg1 = getImage("images/p1.png");
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

        Image playerImg2 = getImage("images/p2.png");
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
            Image playerImg3 = getImage("images/p3.png");
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
                Image playerImg4 = getImage("images/p4.png");
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

    ///@pre --
    ///@post  Retorna la imatge amb una url si no es correcte mostra error i retorna null
    private static Image getImage(String url){
        Image imgR= null;
        try {
            imgR = new Image(String.valueOf(Gui.class.getResource(url)));
        }catch (Exception e) {
            print("Error amb la lecture de l'imatge "+url);
        }
        return imgR;
    }

    ///@pre Mid inicialitzat, la fitxa f ha de tenir una posicio asignada correcte
    ///@post Posa fitxa en el tauler de la Gui
    public static void posaFitxa(Fitxa f){
        Posicio posicio=f.getPosicio();
        Image fitxaImg = getImage("images/"+f.formatNormal()+".jpg");
        ImageView fitxa=new ImageView(fitxaImg);
        fitxa.setLayoutX(pos[posicio.getPosicioX()]);fitxa.setLayoutY(pos[posicio.getPosicioY()]);
        fitxa.setFitHeight(40);
        fitxa.setFitWidth(40);
        fitxa.setRotate(posicio.getRotacio());
        midRow.getChildren().addAll(fitxa);
    }

    ///@pre Mid i Jugador inicialitzat, jugador>=1 && jugador<=4
    ///@post actualitza els punts del jugador
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

    ///@pre Mid inicialitzat
    ///@post configura el tauler
    public static void iniciaTaulerGui() {
        Image blackimg = getImage("images/black.png");
        ImageView blackView = new ImageView(blackimg);
        blackView.setFitHeight(ample-150);
        blackView.setFitWidth(ample-150);
        blackView.setLayoutX(75);blackView.setLayoutY(75);
        midRow.getChildren().remove(comenca);
        midRow.getChildren().addAll(blackView);
        midRow.getChildren().add(baralla);
        midRow.getChildren().add(opcioRotacio);
    }

    ///@pre njugador>=1 && njugador<=4, Mid i Jugador inicialitzat
    ///@post Actualitzat el nSeguidors del jugador
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

    ///@pre --
    ///@post Infroma per pantalla que el fitxer es incorrecte per el motiou "motiu" i torna a demana fitxer entrada
    public static void informarFitxerEntradaIncorrecte(String motiu){
        print("Fitxer incorrecte! Motiu: "+motiu+". Torna a introduïr el nom del fitxer.");
        buttonFile.setDisable(false);
        textField.setText("");
        textField.setDisable(false);
    }

    ///@pre Mid inicialitzat
    ///@post Mostra el tamany de baralla size ocults i la ultima carta de la baralla f destapada
    public static void MostraBaralla(int size, Fitxa f){
        if(f!=null) {
            Image backFitxaImg = getImage("images/back.jpg");
            Image frontFitxaImg = getImage("images/" + f.formatNormal() + ".jpg");
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

    ///@pre Mid i tauler inicialitzat 9<=x>=0 && 9<=y>=0
    ///@post Posa opcio de posar fitxa en el tauler de la posicio x, y amb rotacio
    private static void posaQuadreVerd(int x, int y, int rot){
        Image fitxaImg = getImage("images/green.png");
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

    ///@pre --
    ///@post Elimina de la gui el ultim element (Funcio perillosa Un gran poder conlleva una gran responsabilidad)
    private static void treuUltimElement() {
        midRow.getChildren().remove(midRow.getChildren().size()-1);
    }

    ///@pre Mid i tauler inicialitzat
    ///@post Posa en les posicions alp quadres verds d'opcions per ficar fitxa
    public static void posaQuadresVerds(ArrayList<Posicio> alp){
        print("Sel·leccina col·locació de la fitxa");
        nBlocksVerdsPosats=alp.size();
        for(int i=0;i<alp.size();i++){
            posaQuadreVerd(alp.get(i).getPosicioX(),alp.get(i).getPosicioY(),alp.get(i).getRotacio());
        }
    }

    ///@pre Mid i tauler inicialitzat i haver cridat correctament posaQuadresVerds() avans
    ///@post Treu les opcions dels quadres verds
    private static void treuQuadresVerds(){
        for(int i=0;i<nBlocksVerdsPosats;i++)
            treuUltimElement();
    }

    ///@pre Mid i tauler inicialitzat, una fitxa posada en la posicio x,y, 9<=x>=0 && 9<=y>=0, posicions.size()>0
    ///@post Posa les opcions per colocar seguidor en la posicio x,y
    public static void posaSeleccioDeSeguidors(int x, int y, ArrayList<Character> posicions) {
        print("Sel·lecciona col·locació del seguidor");
        Image seguidorImg = getImage("images/pb.png");
        ImageView seguidorC=new ImageView(seguidorImg);
        ImageView seguidorN=new ImageView(seguidorImg);
        ImageView seguidorE=new ImageView(seguidorImg);
        ImageView seguidorS=new ImageView(seguidorImg);
        ImageView seguidorO=new ImageView(seguidorImg);

        int possibilitats=posicions.size()+1;
        if(posicions.contains('C'))configuraImgSeguidor(seguidorC, pos[x] + 15, pos[y] + 15, 'C', possibilitats);
        if(posicions.contains('N'))configuraImgSeguidor(seguidorN,pos[x]+15,pos[y],'N',possibilitats);
        if(posicions.contains('E'))configuraImgSeguidor(seguidorE,pos[x]+30,pos[y]+15,'E',possibilitats);
        if(posicions.contains('S'))configuraImgSeguidor(seguidorS,pos[x]+15,pos[y]+30, 'S',possibilitats);
        if(posicions.contains('O'))configuraImgSeguidor(seguidorO,pos[x],pos[y]+15, 'O',possibilitats);

        Image crossImg = getImage("images/cross.png");
        ImageView cross=new ImageView(crossImg);
        cross.setLayoutX(pos[x]+30);cross.setLayoutY(pos[y]+2);
        cross.setFitHeight(8);cross.setFitWidth(8);
        int finalPossibilitats = possibilitats;
        cross.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                int x= (int) ((t.getSceneX()-81)/40);
                int y= (int) ((t.getSceneY()-110)/40);
                treuSeguidors(finalPossibilitats);
                Joc.apretatPerPosarSeguidor(x, y, 'X');
            }
        });
        midRow.getChildren().add(cross);
        if(posicions.contains('C'))midRow.getChildren().add(seguidorC);
        if(posicions.contains('N'))midRow.getChildren().add(seguidorN);
        if(posicions.contains('E'))midRow.getChildren().add(seguidorE);
        if(posicions.contains('S'))midRow.getChildren().add(seguidorS);
        if(posicions.contains('O'))midRow.getChildren().add(seguidorO);
    }

    ///@pre iv esta inicialitzat, 9<=x>=0 && 9<=y>=0
    ///@post configura tamany i direccio dir del seguidor en posicio x i y
    public static void configuraImgSeguidor(ImageView iv, int x, int y, char dir, int possibilitats){
        iv.setLayoutX(x);iv.setLayoutY(y);
        iv.setFitHeight(10);
        iv.setFitWidth(10);
        iv.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                int x= (int) ((t.getSceneX()-81)/40);
                int y= (int) ((t.getSceneY()-110)/40);
                treuSeguidors(possibilitats);
                Joc.apretatPerPosarSeguidor(x, y, dir);
            }
        });
    }

    ///@pre Haver cridat correctament configuraImgSeguidor() avans
    ///@post Treu la seleccio de posicionament dels seguidors, possibilitats vegades
    private static void treuSeguidors(int possibilitats) {
        for(int i=0;i<possibilitats;i++)
            treuUltimElement();
    }

    ///@pre Mid configurat
    ///@post Posa l'opcio de clickar per avançar nou torn
    public static void mostraNextTirada(){
        Image nextImg = getImage("images/next.png");
        ImageView next=new ImageView(nextImg);
        next.setFitHeight(40);
        next.setFitWidth(40);
        next.setLayoutX(ample/2-20);next.setLayoutY(20);
        next.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                treuUltimElement();
                Joc.iniciaNouTorn();
            }
        });
        midRow.getChildren().addAll(next);
    }

    ///@pre 9<=x>=0, 9<=y>=0, 4<=numbJugador>=1
    ///@post Posa el seguidor en la posicio dir del tauler x,y que pertany al jugador numbJugador
    public static void posaSeguidor(int x, int y, char dir, int numbJugador) {
        Image seguidorImg = getImage("images/p"+numbJugador+".png");
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

    ///@pre posDisp.size()>0
    ///@post Posa la seleccio de rotacio 'posDisp' de la fitxaActual
    public static void mostraOpcionsDeRotacioEnFitxa(ArrayList<Posicio> posDisp, Fitxa fitxaActual) {
        print("Sel·lecciona rotació de la fitxa");
        Image FitxaImg = getImage("images/"+fitxaActual.formatNormal()+".jpg");
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
            Image fitxaImg = getImage("images/green.png");
            ImageView quadre=new ImageView(fitxaImg);
            quadre.setLayoutX(pos[posDisp.get(0).getPosicioX()]);
            quadre.setLayoutY(pos[posDisp.get(0).getPosicioY()]);
            quadre.setFitHeight(40);
            quadre.setFitWidth(40);
            midRow.getChildren().addAll(quadre);
            opcioRotacio.getChildren().add(fitxa);
        }
    }

    ///@pre guanyadors.size()>0
    ///@post Mostra per pantalla el guanyador i els punts que ha fet
    public static void mostraGuanyadors(ArrayList<Integer> guanyadors, int punts) {
        Image winnerImg = getImage("images/winsplash.png");
        ImageView winner = new ImageView(winnerImg);
        winner.setFitHeight(ample);winner.setFitWidth(ample);

        int div=15;
        if(punts<10)div=10;

        Text sp=new Text (ample/2-40, ample/2-65,"PUNTS");
        Text p=new Text (ample/2-div, ample/2-40,punts+"");
        Text sw=new Text(ample/2-66, ample/2+90,"JUGADOR/S");
        Text jugadors=new Text();
        sw.setFont(Font.font("Arial Black",20));p.setFont(Font.font("Arial Black",20));sp.setFont(Font.font("Arial Black",20));jugadors.setFont(Font.font("Arial Black",20));
        sw.setFill(Color.WHITE);p.setFill(Color.WHITE);sp.setFill(Color.WHITE);jugadors.setFill(Color.WHITE);

        String j="";
        for(int i=0;i<guanyadors.size();i++)
            j+=guanyadors.get(i)+",";
        j=j.substring(0, j.length()-1);
        jugadors.setText(j);
        jugadors.setLayoutX(ample/2-5-j.length()*5);jugadors.setLayoutY(ample/2+115);
        midRow.getChildren().addAll(winner,p,sw,sp,jugadors);
    }
}
