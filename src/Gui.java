import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application{

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
        textField.setPromptText("Enter the name of the file");

        Button buttonFile = new Button();
        buttonFile.setText("Click me");

        AnchorPane.setLeftAnchor(textField, 0.0);
        AnchorPane.setRightAnchor(buttonFile, 0.0);
        AnchorPane.setLeftAnchor(textField,0.0);
        AnchorPane.setRightAnchor(textField,70.0);

        topRow.getChildren().addAll(textField,buttonFile);

        //MIG
        TextArea texta = new TextArea();
        texta.setPrefSize(700,700);

        //BOT
        TableView<String> table = new TableView();
        TableColumn log = new TableColumn("LOG");
        log.setResizable(false);log.setSortable(false);

        table.getColumns().addAll(log);
        table.setPrefSize(700,130);


        root.getChildren().addAll(topRow, texta, table);

        Scene scene = new Scene(root, 700, 850);
        primaryStage.setTitle("Carcassonne");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
