package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.util.DButil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    // PrimaryStage (It contains everything)
    private Stage primaryStage;
    // Border Pane of RootLayout
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // 1) Declare a primary stage (everything will be on this stage)
        this.primaryStage = primaryStage;
        // 2) Set a title for primary stage
        this.primaryStage.setTitle("Java FX App");
        // 3) Initialize rootlayout
        initRootLayout();
        // 4) Display the Employee operations view
        showEmployeeView();
    }

    // Initializes the root layout
    public void initRootLayout() {
        try {
            // 1st load root from Rootlayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // 2nd, show the scene containing the root layout
            Scene scene = new Scene(rootLayout); // we are sending rootlayout to the Scene
            primaryStage.setScene(scene); // set the scene in primary stage

            // 3rd, show the primary stage
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEmployeeView() {
        try {
            // 1st, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/EmployeeView.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // set Employee Operations View in the center of the root layout
            rootLayout.setCenter(employeeOperationsView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }
}
