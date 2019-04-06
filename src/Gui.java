import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class Gui extends Application{

    private int altura=700;
    private int ample=550;

    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox(5);
        root.setPadding(new Insets(5));

        //TOP
        AnchorPane topRow = new AnchorPane();

        TextField textField = new TextField ();
        textField.setPromptText("Introdueix el nom del fitxer");

        Button buttonFile = new Button();
        buttonFile.setText("Carregar Fitxer");
        double buttonSize=100.0;
        buttonFile.setPrefSize(buttonSize,20.0);

        AnchorPane.setLeftAnchor(textField, 0.0);
        AnchorPane.setRightAnchor(buttonFile, 0.0);
        AnchorPane.setLeftAnchor(textField,0.0);
        AnchorPane.setRightAnchor(textField,buttonSize+5.0);

        topRow.getChildren().addAll(textField,buttonFile);

        //MIG
        AnchorPane midRow = new AnchorPane();

        Image image = new Image(new FileInputStream("doc\\images\\taulerimg.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(ample);
        imageView.setFitWidth(ample);


        midRow.getChildren().addAll(imageView);

        //BOT
        TableView<String> table = new TableView();
        TableColumn log = new TableColumn("LOG");
        log.setResizable(false);log.setSortable(false);

        table.getColumns().addAll(log);
        table.setPrefSize(ample,altura-ample-20);

        //------------------
        root.getChildren().addAll(topRow, midRow, table);

        Scene scene = new Scene(root, ample, altura);
        primaryStage.setTitle("Carcassonne");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
