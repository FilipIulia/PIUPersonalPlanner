import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LogInFormGUI {
    private static TextField usernameField;
    private static PasswordField passwordField;

    private static Circle photoCircle;

    public static Scene createLogInScene(Stage primaryStage) {
        primaryStage.setTitle("Logare");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("Utilizator");
        Label passwordLabel = new Label("Parolă");

        passwordField = new PasswordField();
        usernameField = new TextField();
        usernameField.getStyleClass().add("text-field");
        passwordField.getStyleClass().add("text-field");

        Button registerButton = new Button("Înregistrează-te");
        registerButton.getStyleClass().add("buttonRegister");
        Button loginButton = new Button("Autentifică-te");
        loginButton.getStyleClass().add("buttonLogin");
        Separator separator = new Separator();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);
        grid.add(usernameLabel, 0, 2);
        grid.add(usernameField,1,2);
        grid.add(passwordLabel, 0, 4);
        grid.add(passwordField, 1, 4);
        grid.add(loginButton,1, 6 );
        grid.add(separator, 1, 7);
        grid.add(registerButton, 1, 8);

        root.getChildren().add(grid);

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("styles.css");
        return scene;
    }
}
